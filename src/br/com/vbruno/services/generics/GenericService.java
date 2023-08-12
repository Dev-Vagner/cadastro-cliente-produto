package br.com.vbruno.services.generics;

import br.com.vbruno.dao.Persistente;
import br.com.vbruno.dao.generics.IGenericDAO;
import br.com.vbruno.exceptions.TipoChaveNaoEncontrado;

import java.util.Collection;

public class GenericService<E, T extends Persistente> implements IGenericService <E, T>{

    private IGenericDAO genericDAO;

    public GenericService(IGenericDAO genericDAO) {
        this.genericDAO = genericDAO;
    }

    @Override
    public Boolean cadastrar(T entity) throws TipoChaveNaoEncontrado {
        return this.genericDAO.cadastrar(entity);
    }

    @Override
    public T buscar(E value) {
        return (T) this.genericDAO.buscar(value);
    }

    @Override
    public Boolean excluir(E value) {
        return this.genericDAO.excluir(value);
    }

    @Override
    public Boolean alterar(T entity) throws TipoChaveNaoEncontrado {
        return this.genericDAO.alterar(entity);
    }

    @Override
    public Collection<T> buscarTodos() {
        return this.genericDAO.buscarTodos();
    }
}
