package ispwproject.gymwizard.model;

public class EsercizioScheda {

    private int id;
    private int id_scheda;
    private int id_esercizio;
    private String giorno_settimana;
    private int serie;
    private int ripetizioni;
    private String note;

    // Getter e Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_scheda() {
        return id_scheda;
    }

    public void setId_scheda(int id_scheda) {
        this.id_scheda = id_scheda;
    }

    public int getId_esercizio() {
        return id_esercizio;
    }

    public void setId_esercizio(int id_esercizio) {
        this.id_esercizio = id_esercizio;
    }

    public String getGiorno_settimana() {
        return giorno_settimana;
    }

    public void setGiorno_settimana(String giorno_settimana) {
        this.giorno_settimana = giorno_settimana;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public int getRipetizioni() {
        return ripetizioni;
    }

    public void setRipetizioni(int ripetizioni) {
        this.ripetizioni = ripetizioni;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
