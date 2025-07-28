package ispwproject.gymwizard.model;

import java.sql.Timestamp;

public class Scheda {
    private int id;
    private int idCliente;
    private String nomeScheda;
    private String tipo;
    private Timestamp dataCreazione;

    // Costruttore vuoto richiesto da Gson
    public Scheda() {}

    public Scheda(int id, int idCliente, String nome, String tipo, Timestamp dataCreazione) {
        this.id = id;
        this.idCliente = idCliente;
        this.nomeScheda = nome;
        this.tipo = tipo;
        this.dataCreazione = dataCreazione;
    }

    public Scheda(int idCliente, String nome, String tipo) {
        this.idCliente = idCliente;
        this.nomeScheda = nome;
        this.tipo = tipo;
    }

    public Scheda(int i, String s) {
    }

    // Getter e Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setTipo(String tipo){this.tipo = tipo;}

    public String getTipo() {
        return tipo;
    }

    public String getNomeScheda() {
        return nomeScheda;
    }

    public void setNomeScheda(String nomeScheda) {
        this.nomeScheda = nomeScheda;
    }

    public Timestamp getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(Timestamp dataCreazione) {
        this.dataCreazione = dataCreazione;
    }
}
