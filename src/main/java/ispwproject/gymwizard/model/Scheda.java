package ispwproject.gymwizard.model;

import java.sql.Timestamp;

public class Scheda {
    private int id;
    private int idCliente;
    private String nomeScheda;
    private Timestamp dataCreazione;

    public Scheda(int id, int idCliente, String nome, Timestamp dataCreazione) {
        this.id = id;
        this.idCliente = idCliente;
        this.nomeScheda = nome;
        this.dataCreazione = dataCreazione;
    }

    public Scheda(int idCliente, String nome) {
        this.idCliente = idCliente;
        this.nomeScheda = nome;
    }

    public int getId() {
        return id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNomeScheda() {
        return nomeScheda;
    }

    public Timestamp getDataCreazione() {
        return dataCreazione;
    }

    public void setId(int id) {
        this.id = id;
    }

}
