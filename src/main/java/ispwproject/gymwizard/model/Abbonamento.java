package ispwproject.gymwizard.model;

import java.util.Date;

public class Abbonamento {

    private int idAbbonamento;
    private Date dataInizio;
    private Date dataFine;
    private String tipo;
    private String stato;

    // Costruttori
    public Abbonamento() {}

    public Abbonamento(int idAbbonamento, Date dataInizio, Date dataFine, String tipo, String stato) {
        this.idAbbonamento = idAbbonamento;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.tipo = tipo;
        this.stato = stato;
    }

    // Getter e Setter
    public int getIdAbbonamento() {
        return idAbbonamento;
    }

    public void setIdAbbonamento(int idAbbonamento) {
        this.idAbbonamento = idAbbonamento;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public void setDataFine(Date dataFine) {
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

    // Metodo utile di dominio: verifica se l’abbonamento è attivo
    public boolean isAttivo() {
        Date oggi = new Date();
        return stato.equalsIgnoreCase("Attivo") && dataFine.after(oggi);
    }

    // Metodo utile di dominio: rinnova la scadenza di un mese (esempio)
    public void rinnova() {
        long MILLIS_IN_MESE = 30L * 24 * 60 * 60 * 1000;
        this.dataFine = new Date(this.dataFine.getTime() + MILLIS_IN_MESE);
        this.stato = "Attivo";
    }
}
