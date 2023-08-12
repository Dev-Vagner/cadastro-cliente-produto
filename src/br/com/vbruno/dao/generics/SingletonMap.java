package br.com.vbruno.dao.generics;

import br.com.vbruno.dao.Persistente;

import java.util.HashMap;
import java.util.Map;

public class SingletonMap {
    private static SingletonMap singletonMap;
    private Map<Class, Map<?, ?>> mapExterno;

    private SingletonMap() {
        this.mapExterno = new HashMap<>();
    }

    public static SingletonMap getInstance(){
        if(singletonMap == null) {
            singletonMap = new SingletonMap();
        }

        return singletonMap;
    }

    public Map<Class, Map<?, ?>> getMap() {
        return this.mapExterno;
    }
}
