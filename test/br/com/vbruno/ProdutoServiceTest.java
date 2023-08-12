package br.com.vbruno;

import br.com.vbruno.dao.IProdutoDAO;
import br.com.vbruno.dao.ProdutoDAO;
import br.com.vbruno.domain.Produto;
import br.com.vbruno.exceptions.TipoChaveNaoEncontrado;
import br.com.vbruno.mock.ProdutoDAOMock;
import br.com.vbruno.services.IProdutoService;
import br.com.vbruno.services.ProdutoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;

public class ProdutoServiceTest {
    private Produto produto;
    private IProdutoDAO produtoDAO;
    private IProdutoService produtoService;

    public ProdutoServiceTest() {
        produto = new Produto();
        produtoDAO = new ProdutoDAOMock();
        produtoService = new ProdutoService(produtoDAO);
    }

    @Before
    public void init() throws TipoChaveNaoEncontrado {
        produto.setCodigo(32143L);
        produto.setDescricao("Descrição teste!");
        produto.setValor(new BigDecimal("25.4"));
        produtoService.cadastrar(produto);
    }

    @Test
    public void cadastrarProdutoTest() throws TipoChaveNaoEncontrado {
        Produto produto2 = new Produto();
        produto2.setCodigo(21L);

        Boolean produtoCadastrado = produtoService.cadastrar(produto2);
        Assert.assertTrue(produtoCadastrado);
    }

    @Test
    public void buscarProdutoTest() {
        Produto produtoConsultado = produtoService.buscar(produto.getCodigo());
        Assert.assertEquals(produto, produtoConsultado);
    }

    @Test
    public void buscarTodosProdutosTest() {
        Collection<Produto> listaTodosProdutos = produtoService.buscarTodos();
        Assert.assertNotNull(listaTodosProdutos);
    }

    @Test
    public void excluirProdutoTest() {
        Boolean produtoExcluido = produtoService.excluir(produto.getCodigo());
        Assert.assertTrue(produtoExcluido);
    }

    @Test
    public void alterarProdutoTest() throws TipoChaveNaoEncontrado {
        produto.setDescricao("Descrição de teste alterada");
        produto.setValor(new BigDecimal("13.0"));

        Boolean produtoAlterado = produtoService.alterar(produto);

        Assert.assertEquals("Descrição de teste alterada", produto.getDescricao());
        Assert.assertEquals(new BigDecimal("13.0"), produto.getValor());
        Assert.assertTrue(produtoAlterado);
    }
}
