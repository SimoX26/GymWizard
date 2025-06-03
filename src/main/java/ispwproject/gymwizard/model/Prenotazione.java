package ispwproject.gymwizard.model;

import java.time.LocalDateTime;

public class Prenotazione {
    private int id;
    private int idAttivita;
    private int idCliente;
    private LocalDateTime dataCreazione;

    // Costruttori
    public Prenotazione() {}

    public Prenotazione(int id, int idAttivita, int idCliente, LocalDateTime dataCreazione) {
        this.id = id;
        this.idAttivita = idAttivita;
        this.idCliente = idCliente;
        this.dataCreazione = dataCreazione;
    }

    // Getter e Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAttivita() {
        return idAttivita;
    }

    public void setIdAttivita(int idAttivita) {
        this.idAttivita = idAttivita;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDateTime getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(LocalDateTime dataCreazione) {
        this.dataCreazione = dataCreazione;
    }
}
