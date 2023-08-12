package br.com.vbruno.mock;

import br.com.vbruno.dao.IProdutoDAO;
import br.com.vbruno.domain.Cliente;
import br.com.vbruno.domain.Produto;
import br.com.vbruno.exceptions.TipoChaveNaoEncontrado;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ProdutoDAOMock implements IProdutoDAO {

    private Map<Long, Produto> map = new HashMap<>();
    @Override
    public Boolean cadastrar(Produto entity) throws TipoChaveNaoEncontrado {
        map.put(entity.getCodigo(), entity);
        return true;
    }

    @Override
    public Produto buscar(Long value) {
        Produto produto = map.get(value);
        return produto;
    }

    @Override
    public Collection<Produto> buscarTodos() {
        return map.values();
    }

    @Override
    public Boolean excluir(Long value) {
        map.remove(value);
        return true;
    }

    @Override
    public Boolean alterar(Produto entity) throws TipoChaveNaoEncontrado {
        Produto produtoCadastrado = map.get(entity.getCodigo());
        if(produtoCadastrado != null) {
            produtoCadastrado.setDescricao(entity.getDescricao());
            produtoCadastrado.setValor(entity.getValor());
            return true;
        }
        return false;
    }
}
