package br.com.vbruno.services.generics;

import br.com.vbruno.dao.Persistente;
import br.com.vbruno.exceptions.TipoChaveNaoEncontrado;

import java.util.Collection;

public interface IGenericService <E, T extends Persistente> {
    Boolean cadastrar(T entity) throws TipoChaveNaoEncontrado;
    T buscar(E value);
    Boolean excluir(E value);
    Boolean alterar(T entity) throws TipoChaveNaoEncontrado;
    Collection<T> buscarTodos();
}
