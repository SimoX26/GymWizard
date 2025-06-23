package ispwproject.gymwizard.util.bean;

public class PrenotazioneBean {
    private final String data;
    private final String attivita;
    private final String stato;

    public PrenotazioneBean(String data, String attivita, String stato) {
        this.data = data;
        this.attivita = attivita;
        this.stato = stato;
    }

    public String getData() {
        return data;
    }

    public String getAttivita() {
        return attivita;
    }

    public String getStato() {
        return stato;
    }
}
