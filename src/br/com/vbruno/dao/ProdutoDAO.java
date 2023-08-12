package br.com.vbruno.dao;

import br.com.vbruno.dao.generics.GenericDAO;
import br.com.vbruno.domain.Produto;

public class ProdutoDAO extends GenericDAO <Long, Produto> implements IProdutoDAO {
    @Override
    public Class<Produto> getTipoClasse() {
        return Produto.class;
    }

    @Override
    public Boolean editar(Produto entityRegistered, Produto entityUpdated) {
        entityRegistered.setDescricao(entityUpdated.getDescricao());
        entityRegistered.setValor(entityUpdated.getValor());
        return true;
    }
}
