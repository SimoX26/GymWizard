package ispwproject.gymwizard.model;

import java.sql.Timestamp;

public class Scheda {
    private int id;
    private int idCliente;
    private String nomeScheda;
    private Timestamp dataCreazione;

    // Costruttore vuoto richiesto da Gson
    public Scheda() {}

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
