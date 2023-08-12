package br.com.vbruno.dao.generics;

import br.com.vbruno.anotacao.TipoChave;
import br.com.vbruno.dao.Persistente;
import br.com.vbruno.exceptions.TipoChaveNaoEncontrado;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class GenericDAO<E, T extends Persistente> implements IGenericDAO<E, T> {
    private SingletonMap singletonMap;
    public abstract Class<T> getTipoClasse();
    public abstract Boolean editar(T entityRegistered, T entityUpdated);

    public GenericDAO() {
        this.singletonMap = SingletonMap.getInstance();
    }

    @Override
    public Boolean cadastrar(T entity) throws TipoChaveNaoEncontrado {
        E key = getKey(entity);
        Map<E, T> map = getMapInterno();

        if(map.containsKey(key)) {
            return false;
        }

        map.put(key, entity);
        return true;
    }

    @Override
    public T buscar(E value) {
        Map<E, T> map = getMapInterno();
        if(map.containsKey(value)) {
            T entity = map.get(value);
            return entity;
        }

        return null;
    }

    @Override
    public Collection<T> buscarTodos() {
        Map<E, T> map = getMapInterno();
        return map.values();
    }

    @Override
    public Boolean excluir(E value) {
        Map<E, T> map = getMapInterno();
        if(map.containsKey(value)) {
            map.remove(value);
            return true;
        }

        return false;
    }

    @Override
    public Boolean alterar(T entity) throws TipoChaveNaoEncontrado {
        Map<E, T> map = getMapInterno();
        E key = getKey(entity);

        T entityRegistered = map.get(key);

        if(entityRegistered != null) {
            editar(entityRegistered, entity);
            return true;
        }
        return false;
    }

    private Map<E, T> getMapInterno() {
        Map<E, T> mapInterno = (Map<E, T>) this.singletonMap.getMap().get(getTipoClasse());

        if (mapInterno == null) {
            mapInterno = new HashMap<>();
            this.singletonMap.getMap().put(getTipoClasse(), mapInterno);
        }

        return mapInterno;
    }

    private E getKey(T entity) throws TipoChaveNaoEncontrado {
        Boolean hasKey = false;
        E key = null;

        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            if(field.isAnnotationPresent(TipoChave.class)) {
                hasKey = true;
                TipoChave annotation = field.getAnnotation(TipoChave.class);
                String nameMethod = annotation.value();
                try{
                    Method method = entity.getClass().getDeclaredMethod(nameMethod);
                    key = (E) method.invoke(entity);
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                    throw new TipoChaveNaoEncontrado("A chave principal do objeto " + entity.getClass() + " não foi encontrada!");
                }
            }
        }

        if(hasKey.equals(false)) {
            throw new TipoChaveNaoEncontrado("A chave principal do objeto " + entity.getClass() + " não foi encontrada!");
        }

        return key;
    }
}
