package ispwproject.gymwizard.util.bean;

public class UtenteAttivoBean {
    private final String username;
    private final String nomeCompleto;
    private final String ultimoAccesso;

    public UtenteAttivoBean(String username, String nomeCompleto, String ultimoAccesso) {
        this.username = username;
        this.nomeCompleto = nomeCompleto;
        this.ultimoAccesso = ultimoAccesso;
    }

    public String getUsername() {
        return username;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getUltimoAccesso() {
        return ultimoAccesso;
    }
}
