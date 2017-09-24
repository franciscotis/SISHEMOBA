package br.uefs.ecomp.hemoba.main.model;
/**
 * 
 * @author Francisco Pereira (16111203)
 */
public class Posto {
	
    private int numeroposto;
    private String endereco;
    private String telefone;
    private String responsavel;

    public Posto(String endereco, String telefone, String responsavel, int numeroposto) {
        this.endereco = endereco;
        this.telefone = telefone;
        this.responsavel = responsavel;
        this.numeroposto = numeroposto;
    }
    
    public int getNumeroposto() {
        return numeroposto;
    }

    public void setNumeroposto(int numeroposto) {
        this.numeroposto = numeroposto;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
    
    

}
