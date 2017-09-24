package br.uefs.ecomp.hemoba.main.controller;

import br.uefs.ecomp.hemoba.main.model.*;
import br.uefs.ecomp.hemoba.main.util.Iterador;
import br.uefs.ecomp.hemoba.main.util.Lista;
import java.util.Date;

/**
 * 
 * @author Francisco Pereira (16111203)
 */

public class Controller {

    private Lista<Doador> doador; // declaro uma lista de doador
    private Lista<Doacao> doacao; //declaro uma lista de doação
    private Lista<Posto> posto; //declaro uma lista de posto

    public Controller() { //Controller 
        this.doador = new Lista<Doador>(); 
        this.doacao = new Lista<Doacao>();
        this.posto = new Lista<Posto>();

    }

    public Doador cadastrarDoador(String nome, String endereco, String telefone, Date datanasc, int peso, String responsavelDoador,int matricula) {
        //Ele vai cadastrar o doador na lista
        Doador donator = new Doador(nome, endereco, telefone, datanasc, peso, responsavelDoador,matricula);
        //Crio um atributo do tipo doador que vai receber os parâmetros do método
        this.doador.inserirInicio(donator);
        //Insiro na lista
        return donator; 
    }

    public Doador obterDoador(Integer matricula) { //procurar o doador na lista e ai retorna ele
        Iterador iterador = doador.iterador(); //Crio um iterador que aponta para o primeiro nó da lista
        while (iterador.temProximo()) { //Enquanto tem próximo
            Doador temp = (Doador) iterador.obterProximo(); //Crio um atributo do tipo Doador que recebe o conteudo de quem o iterador aponta
            if (temp.getMatricula() == matricula) { //Se a matricula do parâmetro for igual a matricula do doador presente na lista
                return temp; //Retorno o nó da lista que contém a matrícula desejada
            }
        }
        return null; //Caso contrário retorno nulo
    }
    
   

    public boolean horaEDataDoacao(int hora, Date dat, Posto p) { //Vai verificar se uma hora já está cadastrada no sistema em tal dia e em tal posto
        Iterador iterador = doacao.iterador(); //Crio um iterador que vai apontar para o primeiro nó da lista
        while (iterador.temProximo()) { //Enquanto tem próximo
            Doacao temp = (Doacao) iterador.obterProximo(); //Crio um atributo do tipo Doação que recebe o conteudo de quem o iterador aponta
            if (temp.getHora() == hora && temp.getData().equals(dat)  
                    && temp.getNumeroposto().getNumeroposto()==p.getNumeroposto()) {
                return true;
            }
            //Caso a hora ja esteja cadastrada no sistema retorno true
        }
        return false; //Caso contrário, retorno false
    }

    public Posto cadastrarPosto(String endereco, String telefone, String responsavelPosto, int numeroposto) {
        //Cadastro posto na lista
        Posto post = new Posto(endereco, telefone, responsavelPosto,numeroposto);
         //Crio um atributo do tipo posto que vai receber os parâmetros do método
        this.posto.inserirInicio(post);
        //Insiro na lista
        return post;
    }

    public Posto obterPosto(Integer numeroPosto) {
        //Método que retorna o posto que contém o número do parâmetro
        Iterador iterador = posto.iterador(); //Crio um iterador que aponta para o primeiro nó da lista
        while (iterador.temProximo()) { //Enquanto tem próximo
            Posto temp = (Posto) iterador.obterProximo(); //Crio um atributo do tipo Posto que recebe o conteudo de quem o iterador aponta
            if (temp.getNumeroposto() == numeroPosto) { //Se o númer do parâmetro for igual ao número do posto presente na lista
                return temp; //Retorna o nó da lista de posto que contém o numero do parâmetro
            } 
        }
        return null; //Caso contrário retorna nulo.
    }

    public Doacao cadastrarDoacao(Date dataDoacao, int hora, Posto numeroPosto, Doador matricula, boolean status) {
       //Cadastra a doação na lista 
        Doacao donation = new Doacao(dataDoacao, hora, numeroPosto, matricula, status);
       //Crio um atributo do tipo doação que vai receber os parâmetros do método
        this.doacao.inserirInicio(donation);
        //Insiro a doação na lista
        return donation;
    } //cadastra a doação

    public Doacao obterDoacao(Doador matricula) {
        //Método que obtem a doação a partir da matricula do doador
        Iterador iterador = doacao.iterador(); 
        //Crio um iterador que aponta para o primeiro nó da lista 
        while (iterador.temProximo()) { //Enquanto tem próximo
            Doacao temp = (Doacao) iterador.obterProximo();
            //Crio um atributo de nome temp que recebe o conteudo de quem o iterador está apontando
            Doador a = (Doador) temp.getMatriculadodoador();
            //Crio um atributo de nome 'a' que recebe temp.getMatriculadoador();
            int b = matricula.getMatricula();  //Digo que b é igual a matricula do doador
            if (a.getMatricula()==b) { //Caso as matriculas sejam iguais
                return temp; //Retorno o nó da lista que contém a matricula desejada
            }
        }
        return null; //Caso contrário retorna nulo.
    }

    public Iterador listarPostos() {
        return posto.iterador(); //Retorna um iterador que aponta para o inicio da lista posto
    }

    
    public Iterador listarDoadores() {
        return doador.iterador(); //Retorna um iterador que aponta para o inicio da lista doadores
    }

    public Iterador listarDoacoesDia() {
        return doacao.iterador(); //Retorna um iterador que aponta para o inicio da lista de doações
    }

    public Iterador listarDoacoesRealizadas() {
        return doacao.iterador(); //Retorna um iterador que aponta para o inicio da lista de doações
    }

    public Iterador listarDoacoesNaoRealizadas() {
        return doacao.iterador(); //Retorna um iterador que aponta para o inicio da lista de doações
    }

    public void excluirDoacao(int matricula) { //Método que exclui a doação
       Doador dod = obterDoador(matricula); //Obtenho o doador que contém a matricula desejada
       Doacao doac = obterDoacao(dod); //Obtenho a doação que contém o doador obtido acima
       doacao.removerMeio(doac); //Removo da lista
    }
    
 

}
