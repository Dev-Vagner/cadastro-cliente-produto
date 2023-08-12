package br.com.vbruno;

import br.com.vbruno.dao.ClienteDAO;
import br.com.vbruno.dao.IClienteDAO;
import br.com.vbruno.domain.Cliente;

import br.com.vbruno.exceptions.TipoChaveNaoEncontrado;
import br.com.vbruno.mock.ClienteDAOMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class ClienteDAOTest {

    private Cliente cliente;
    private IClienteDAO clienteDAO;

    public ClienteDAOTest() {
        cliente = new Cliente();
        clienteDAO = new ClienteDAO();
    }


    @Before
    public void init() throws TipoChaveNaoEncontrado {
        cliente.setCpf(1231231231L);
        cliente.setNome("Vagner");
        cliente.setTel(312218768765L);
        cliente.setEnd("Rua dos Bobos");
        cliente.setNumero(0);
        cliente.setCidade("Palmas");
        cliente.setEstado("Tocantins");
        clienteDAO.cadastrar(cliente);
    }

    @Test
    public void cadastrarClienteTest() throws TipoChaveNaoEncontrado {
        Cliente cliente2 = new Cliente();
        cliente2.setCpf(321L);

        Boolean clienteCadastrado = clienteDAO.cadastrar(cliente2);
        Assert.assertTrue(clienteCadastrado);
    }

    @Test
    public void buscarClienteTest() {
        Cliente clienteConsultado = clienteDAO.buscar(cliente.getCpf());
        Assert.assertEquals(cliente, clienteConsultado);
    }

    @Test
    public void buscarTodosClientesTest() {
        Collection<Cliente> listaTodosClientes = clienteDAO.buscarTodos();
        Assert.assertNotNull(listaTodosClientes);
    }

    @Test
    public void excluirClienteTest() {
        Boolean clienteExcluido = clienteDAO.excluir(cliente.getCpf());
        Assert.assertTrue(clienteExcluido);
    }

    @Test
    public void alterarClienteTest() throws TipoChaveNaoEncontrado {
        cliente.setNome("Nome alterado");
        cliente.setTel(432143L);
        cliente.setEnd("Rua Alterada");
        cliente.setNumero(10);
        cliente.setCidade("Campina Grande");
        cliente.setEstado("Paraíba");

        Boolean clienteAlterado = clienteDAO.alterar(cliente);

        Assert.assertEquals("Nome alterado", cliente.getNome());
        Assert.assertEquals(432143L, cliente.getTel().longValue());
        Assert.assertEquals("Rua Alterada", cliente.getEnd());
        Assert.assertEquals(10, cliente.getNumero().intValue());
        Assert.assertEquals("Campina Grande", cliente.getCidade());
        Assert.assertEquals("Paraíba", cliente.getEstado());
        Assert.assertTrue(clienteAlterado);
    }

}
