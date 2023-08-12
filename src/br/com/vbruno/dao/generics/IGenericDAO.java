package br.com.vbruno.dao.generics;

import br.com.vbruno.dao.Persistente;
import br.com.vbruno.domain.Cliente;
import br.com.vbruno.exceptions.TipoChaveNaoEncontrado;

import java.util.Collection;

public interface IGenericDAO <E, T extends Persistente> {
    Boolean cadastrar(T entity) throws TipoChaveNaoEncontrado;
    T buscar(E value);
    Collection<T> buscarTodos();
    Boolean excluir(E value);
    Boolean alterar(T entity) throws TipoChaveNaoEncontrado;
}
