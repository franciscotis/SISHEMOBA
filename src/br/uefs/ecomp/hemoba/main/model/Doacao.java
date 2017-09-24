package br.uefs.ecomp.hemoba.main.model;

import java.util.Date;
/**
 * 
 * @author Francisco Pereira (16111203)
 */
public class Doacao {
    private Date data;
    private int hora;
    private Posto numeroposto;
    private Doador matriculadodoador;
    private boolean status;

    public Doacao(Date data, int hora, Posto numeroposto, Doador matriculadodoador, boolean status) {
        this.data = data;
        this.hora = hora;
        this.numeroposto = numeroposto;
        this.matriculadodoador = matriculadodoador;
        this.status = status;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public Posto getNumeroposto() {
        return numeroposto;
    }

    public void setNumeroposto(Posto numeroposto) {
        this.numeroposto = numeroposto;
    }

    public Doador getMatriculadodoador() {
        return matriculadodoador;
    }

    public void setMatriculadodoador(Doador matriculadodoador) {
        this.matriculadodoador = matriculadodoador;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    
}
