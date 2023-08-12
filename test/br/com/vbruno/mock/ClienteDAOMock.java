package br.com.vbruno.mock;

import br.com.vbruno.dao.IClienteDAO;
import br.com.vbruno.domain.Cliente;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ClienteDAOMock implements IClienteDAO {

    private Map<Long, Cliente> map = new HashMap<>();

    @Override
    public Boolean cadastrar(Cliente cliente) {
        map.put(cliente.getCpf(), cliente);
        return true;
    }

    @Override
    public Cliente buscar(Long cpf) {
        Cliente clienteConsultado = map.get(cpf);
        return clienteConsultado;
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        return map.values();
    }

    @Override
    public Boolean excluir(Long cpf) {
        map.remove(cpf);
        return true;
    }

    @Override
    public Boolean alterar(Cliente cliente) {
        Cliente clienteCadastrado = this.map.get(cliente.getCpf());
        if(clienteCadastrado != null) {
            clienteCadastrado.setNome(cliente.getNome());
            clienteCadastrado.setTel(cliente.getTel());
            clienteCadastrado.setEnd(cliente.getEnd());
            clienteCadastrado.setNumero(cliente.getNumero());
            clienteCadastrado.setCidade(cliente.getCidade());
            clienteCadastrado.setEstado(cliente.getEstado());
            return true;
        }
        return false;
    }
}
