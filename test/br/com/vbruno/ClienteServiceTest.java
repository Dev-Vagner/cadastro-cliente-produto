package br.com.vbruno;

import br.com.vbruno.dao.ClienteDAO;
import br.com.vbruno.dao.IClienteDAO;
import br.com.vbruno.domain.Cliente;
import br.com.vbruno.exceptions.TipoChaveNaoEncontrado;
import br.com.vbruno.mock.ClienteDAOMock;
import br.com.vbruno.services.ClienteService;
import br.com.vbruno.services.IClienteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Optional;

public class ClienteServiceTest {

    private Cliente cliente;
    private IClienteDAO clienteDAO;
    private IClienteService clienteService;

    public ClienteServiceTest() {
        cliente = new Cliente();
        clienteDAO = new ClienteDAOMock();
        clienteService = new ClienteService(clienteDAO);
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
        clienteService.cadastrar(cliente);
    }

    @Test
    public void cadastrarClienteTest() throws TipoChaveNaoEncontrado {
        Cliente cliente2 = new Cliente();
        Assert.assertTrue(clienteService.cadastrar(cliente2));
    }

    @Test
    public void buscarClienteTest() {
        Assert.assertEquals(clienteService.buscar(cliente.getCpf()), cliente);
    }

    @Test
    public void buscarTodosClientesTest() {
        Collection<Cliente> listaTodosClientes = clienteService.buscarTodos();
        Assert.assertNotNull(listaTodosClientes);
    }

    @Test
    public void excluirClienteTest() {
        Assert.assertTrue(clienteService.excluir(cliente.getCpf()));
    }

    @Test
    public void alterarClienteTest() throws TipoChaveNaoEncontrado {
        cliente.setNome("Nome alterado");
        cliente.setTel(432143L);
        cliente.setEnd("Rua Alterada");
        cliente.setNumero(10);
        cliente.setCidade("Campina Grande");
        cliente.setEstado("Paraíba");

        Boolean clienteAlterado = clienteService.alterar(cliente);

        Assert.assertEquals("Nome alterado", cliente.getNome());
        Assert.assertEquals(432143L, cliente.getTel().longValue());
        Assert.assertEquals("Rua Alterada", cliente.getEnd());
        Assert.assertEquals(10, cliente.getNumero().intValue());
        Assert.assertEquals("Campina Grande", cliente.getCidade());
        Assert.assertEquals("Paraíba", cliente.getEstado());
        Assert.assertTrue(clienteAlterado);
    }

}
