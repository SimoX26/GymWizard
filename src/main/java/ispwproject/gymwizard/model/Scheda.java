package ispwproject.gymwizard.model;

import java.sql.Timestamp;

public class Scheda {
    private int id;
    private int idCliente;
    private String nomeEsercizio;
    private Timestamp dataCreazione;

    public Scheda(int id, int idCliente, String nomeEsercizio, Timestamp dataCreazione) {
        this.id = id;
        this.idCliente = idCliente;
        this.nomeEsercizio = nomeEsercizio;
        this.dataCreazione = dataCreazione;
    }

    public Scheda(int idCliente, String nomeEsercizio) {
        this.idCliente = idCliente;
        this.nomeEsercizio = nomeEsercizio;
    }

    public int getId() {
        return id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNomeEsercizio() {
        return nomeEsercizio;
    }

    public Timestamp getDataCreazione() {
        return dataCreazione;
    }

    public void setId(int id) {
        this.id = id;
    }
}
