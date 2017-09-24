package br.uefs.ecomp.hemoba.main.util;

public interface IPilha {
    

    public int obtertTamanho();

    public boolean estaVazia();
    
    public Object removerTopo();
    
    public void inserirTopo(Object obj);
    
    public Object recuperarTopo();
}

