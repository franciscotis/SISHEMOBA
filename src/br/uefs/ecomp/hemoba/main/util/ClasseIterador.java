package br.uefs.ecomp.hemoba.main.util;
/**
 * 
 * @author Francisco Pereira (16111203)
 */

public class ClasseIterador<T> implements Iterador {
	private No<T> current; //crio um nó chamado current
    
    
    public ClasseIterador(No<T> first){ //construtor
   	 current = first;
    }
    
    @Override
    public boolean temProximo() { //vê se contém proximo, se sim retorna true, e false caso contrário
        return (current != null) ;
    } 

    @Override
    public Object obterProximo() { //Obtem o elemento que o iterador está apontando
        Object temporario = current.getElemento();
        current = current.getNext() ; 
        return temporario;
    }
        
}