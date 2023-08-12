package br.com.vbruno.services;

import br.com.vbruno.dao.generics.IGenericDAO;
import br.com.vbruno.domain.Cliente;
import br.com.vbruno.exceptions.TipoChaveNaoEncontrado;
import br.com.vbruno.services.generics.IGenericService;

public interface IClienteService extends IGenericService<Long, Cliente> {

}
