package ispwproject.gymwizard.model;

import java.time.LocalDate;

public class Abbonamento {

    private int id;
    private int idUtente;
    private String tipo;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private String stato;
    private String riferimentoPagamento;

    // Costruttori
    public Abbonamento() {
    }

    public Abbonamento(int idAbbonamento, LocalDate dataInizio, LocalDate dataFine, String tipo, String stato) {
        this.id = idAbbonamento;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.tipo = tipo;
        this.stato = stato;
    }

    // Getter e Setter
    public int getId() {
        return id;
    }

    public void setId(int idAbbonamento) {
        this.id = idAbbonamento;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public String getRiferimentoPagamento() {
        return riferimentoPagamento;
    }

    public void setRiferimentoPagamento(String riferimentoPagamento) {
        this.riferimentoPagamento = riferimentoPagamento;
    }
}
