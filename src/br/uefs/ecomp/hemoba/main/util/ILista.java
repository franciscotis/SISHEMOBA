package br.uefs.ecomp.hemoba.main.util;


public interface ILista {

    public boolean estaVazia();

    public int obterTamanho();

    public void inserirInicio(Object o);

    public void inserirFinal(Object o);

    public Object removerInicio();

    public Object removerFinal();

    public Object recuperar(int index);

    public Iterador iterador();
}
