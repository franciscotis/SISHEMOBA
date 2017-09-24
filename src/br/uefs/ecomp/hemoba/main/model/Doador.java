package br.uefs.ecomp.hemoba.main.model;
import java.util.Date;

/**
 * 
 * @author Francisco Pereira (16111203)
 */
public class Doador {
    private String nome;
    private String endereco;
    private String telefone;
    private Date datanasc;
    private int peso;
    private String responsavel;
    private  int matricula;

    public Doador(String nome, String endereco, String telefone, Date datanasc, int peso, String responsavel, int matricula) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.datanasc = datanasc;
        this.peso = peso;
        this.responsavel = responsavel;
        this.matricula = matricula;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Date getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(Date datanasc) {
        this.datanasc = datanasc;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Doador other = (Doador) obj;
        if (this.matricula != other.matricula) {
            return false;
        }
        return true;
    }
    

}
