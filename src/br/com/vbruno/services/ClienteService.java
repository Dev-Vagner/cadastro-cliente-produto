package br.com.vbruno.services;

import br.com.vbruno.dao.IClienteDAO;
import br.com.vbruno.domain.Cliente;
import br.com.vbruno.exceptions.TipoChaveNaoEncontrado;
import br.com.vbruno.services.generics.GenericService;

public class ClienteService extends GenericService<Long, Cliente> implements IClienteService {
    public ClienteService(IClienteDAO clienteDAO) {
        super(clienteDAO);
    }
}
