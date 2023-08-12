package br.com.vbruno.dao;

import br.com.vbruno.dao.generics.GenericDAO;
import br.com.vbruno.domain.Cliente;

public class ClienteDAO extends GenericDAO<Long, Cliente> implements IClienteDAO {
    @Override
    public Class<Cliente> getTipoClasse() {
        return Cliente.class;
    }

    @Override
    public Boolean editar(Cliente entityRegistered, Cliente entityUpdated) {
        entityRegistered.setNome(entityUpdated.getNome());
        entityRegistered.setTel(entityUpdated.getTel());
        entityRegistered.setEnd(entityUpdated.getEnd());
        entityRegistered.setNumero(entityUpdated.getNumero());
        entityRegistered.setCidade(entityUpdated.getCidade());
        entityRegistered.setEstado(entityUpdated.getEstado());
        return true;
    }
}
