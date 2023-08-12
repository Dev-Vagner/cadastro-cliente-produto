package br.com.vbruno;

import br.com.vbruno.dao.IProdutoDAO;
import br.com.vbruno.dao.ProdutoDAO;
import br.com.vbruno.domain.Produto;
import br.com.vbruno.exceptions.TipoChaveNaoEncontrado;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;

public class ProdutoDAOTest {

    private Produto produto;
    private IProdutoDAO produtoDAO;

    public ProdutoDAOTest() {
        produto = new Produto();
        produtoDAO = new ProdutoDAO();
    }

    @Before
    public void init() throws TipoChaveNaoEncontrado {
        produto.setCodigo(31243L);
        produto.setDescricao("Descrição teste!");
        produto.setValor(new BigDecimal("25.4"));
        produtoDAO.cadastrar(produto);
    }

    @Test
    public void cadastrarProdutoTest() throws TipoChaveNaoEncontrado {
        Produto produto2 = new Produto();
        produto2.setCodigo(21L);

        Boolean produtoCadastrado = produtoDAO.cadastrar(produto2);
        Assert.assertTrue(produtoCadastrado);
    }

    @Test
    public void buscarProdutoTest() {
        Produto produtoConsultado = produtoDAO.buscar(produto.getCodigo());
        Assert.assertEquals(produto, produtoConsultado);
    }

    @Test
    public void buscarTodosProdutosTest() {
        Collection<Produto> listaTodosProdutos = produtoDAO.buscarTodos();
        Assert.assertNotNull(listaTodosProdutos);
    }

    @Test
    public void excluirProdutoTest() {
        Boolean produtoExcluido = produtoDAO.excluir(produto.getCodigo());
        Assert.assertTrue(produtoExcluido);
    }

    @Test
    public void alterarProdutoTest() throws TipoChaveNaoEncontrado {
        produto.setDescricao("Descrição de teste alterada");
        produto.setValor(new BigDecimal("13.0"));

        Boolean produtoAlterado = produtoDAO.alterar(produto);

        Assert.assertEquals("Descrição de teste alterada", produto.getDescricao());
        Assert.assertEquals(new BigDecimal("13.0"), produto.getValor());
        Assert.assertTrue(produtoAlterado);
    }
}
