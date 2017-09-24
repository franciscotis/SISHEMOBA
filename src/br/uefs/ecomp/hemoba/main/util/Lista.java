package br.uefs.ecomp.hemoba.main.util;
/**
 * 
 * @author Francisco Pereira (16111203)
 */

import br.uefs.ecomp.hemoba.main.model.Doacao;
import br.uefs.ecomp.hemoba.main.model.Doador;
import java.util.Date;


public class Lista <T> implements ILista {
    
	private No <T> first; //no que aponta para o primeiro elemento 
	
	public Lista(){ //construtor
	    first = null;
	}
	
	public Lista (T o){ //construtor
        if(o != null){
          first = new No<T>(o);
          first.setNext(null);
        }
	}
        
    @Override
    public boolean estaVazia() { //verifica se a lista está vazia
        return (this.first == null);
    }

    @Override
    public int obterTamanho() { //obtem o tamanho da lista
        No<T> current = first;
        int i; //contador
        for(i=0;current!=null;i++){ //percorre a lista
            current = current.getNext();
        }
        return i; //retorna i
    }

	@Override
	public void inserirInicio(Object o) { //insere no inicio
        @SuppressWarnings("unchecked")
		No<T> novo = new No<T>((T) o); //crio novo nó
        novo.setNext(this.first); //diz que seu proximo aponta para o inicio
        this.first = novo;  // o inicio da lista é o novo nó
        
    }

    @Override
    public void inserirFinal(Object o) { //insere no final
        @SuppressWarnings("unchecked")
		No<T> novo = new No<T>((T) o); //crio o novo nó
        novo.setNext(null); //Diz que ele aponta para null
        No<T> current = first; //Current aponta para o primeiro nó da lista
        if(first!=null){ //se ele tiver elemento na lista
        while(current.getNext()!=null){ //enquanto ele tem proximo 
            current = current.getNext(); //percorre a lista
        }
        current.setNext(novo);} //insiro no final
        else{
            inserirInicio(o); //se ele não tiver elemento na lista, insere no inicio
        }
    }

    @Override
    public Object removerInicio() { //remove do inicio
        No<T> current = first; //current aponta para fist
        first = first.getNext(); //o primeiro nó da lista é o seu próximo 
        return current.getElemento(); //retorno o objeto removido
    }

    @Override
    public Object removerFinal() { //remove do final
        No<T> current = first;
        No<T> previous = first;
        
        if(first.getNext()==null){ ///se ele só tem um objeto na lista
            first=null; //ele aponta para nulo
            return current.getElemento(); //retorna o objeto excluido
        }
        while(current.getNext()!=null){  //enquanto ele tem próximo
            previous = current; 
            current = current.getNext(); //current percorre a lista até o seu próximo for nulo, ou seja,ele for o ultimo da lista
        }
        previous.setNext(null); //digo que o previous aponta para nulo
        return current.getElemento(); //retorna o objeto excluido
    }
    
    public void removerMeio(Object obj){ //remove de qualquer lugar da lista
        No<T> current = first;
        No<T> previous = current;
        if(first == null) //caso o primeiro nó for nulo ele para
            return;
        if(first.getElemento().equals(obj)){ //se o objeto estiver no incio da lista
            this.removerInicio(); //exclui do inicio
            return; 
        }
        while (current != null){ //enquanto tiver proximo
            if(current.getElemento().equals(obj)){ //caso o objeto que current aponta seja igual ao objeto que o usuario queira excluir
                previous.setNext(current.getNext()); //o seu anterior aponta para o proximo
                current=previous; // current é igual a previous
            }
            //Caso contrário, ele percorre a lista
            previous = current;
            current = current.getNext();   
        }
    }

    @Override
    public Object recuperar(int index) { //recupera o objeto com o index informado
        No<T> aux;
        aux = first;
        int cont = -1;
        while(aux!=null){
            cont++;
            if(cont == index){
                return aux.getElemento();
            }
            aux = aux.getNext();
        }
        return null;
    }

    @Override
    public Iterador iterador() { //iterador 
       return new ClasseIterador<T>(this.first); //retorna um iterador que aponta para o inicio da lista
    }
    
}