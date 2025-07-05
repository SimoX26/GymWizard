package ispwproject.gymwizard.model;

public class EsercizioScheda {
    private int id;
    private int idScheda;
    private String nomeEsercizio;
    private int serie;
    private int ripetizioni;
    private String note;

    // Costruttore vuoto richiesto da Gson
    public EsercizioScheda() {}

    public EsercizioScheda(int idScheda, String nomeEsercizio, int serie, int ripetizioni, String note) {
        this.idScheda = idScheda;
        this.nomeEsercizio = nomeEsercizio;
        this.serie = serie;
        this.ripetizioni = ripetizioni;
        this.note = note;
    }

    // Getter e Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdScheda() {
        return idScheda;
    }

    public void setIdScheda(int idScheda) {
        this.idScheda = idScheda;
    }

    public String getNomeEsercizio() {
        return nomeEsercizio;
    }

    public void setNomeEsercizio(String nomeEsercizio) {
        this.nomeEsercizio = nomeEsercizio;
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
