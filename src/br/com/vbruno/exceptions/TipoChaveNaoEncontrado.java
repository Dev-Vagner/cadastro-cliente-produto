package br.com.vbruno.exceptions;

public class TipoChaveNaoEncontrado extends Exception{
    public TipoChaveNaoEncontrado(Throwable ex) {
        super(ex);
    }

    public TipoChaveNaoEncontrado(String msg, Throwable ex) {
        super(msg, ex);
    }

    public TipoChaveNaoEncontrado(String msg) {
        super(msg);
    }

}
