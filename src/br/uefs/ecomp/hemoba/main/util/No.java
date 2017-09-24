package br.uefs.ecomp.hemoba.main.util;
/**
 * 
 * @author Francisco Pereira (16111203)
 */

public class No <T> {
	private T elemento; //elemento do no
	private No <T> next; //referencia para o proximo no
   
	public No(T a) { //construtor
		this.elemento = a;
	}
   
	public No(){} //construtor

    public Object getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public No<T> getNext() {
        return next;
    }

    public void setNext(No<T> next) {
        this.next = next;
    }
    
    
    
}
