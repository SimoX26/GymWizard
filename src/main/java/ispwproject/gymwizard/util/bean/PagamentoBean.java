package ispwproject.gymwizard.util.bean;
public class PagamentoBean {
    private final String tipo;
    private final String data_inizio;
    private final String data_fine;
    private final String stato;
    private final String riferimento;

    public PagamentoBean(String tipo, String data_inizio, String data_fine, String stato, String riferimento) {
        this.tipo = tipo;
        this.data_inizio = data_inizio;
        this.data_fine = data_fine;
        this.stato = stato;
        this.riferimento = riferimento;
    }

    public String getTipo() {
        return tipo;
    }

    public String getData_inizio() {
        return data_inizio;
    }

    public String getData_fine() {
        return data_fine;
    }

    public String getStato() {
        return stato;
    }

    public String getRiferimento() {
        return riferimento;
    }
}
