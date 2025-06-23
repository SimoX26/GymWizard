package ispwproject.gymwizard.util.bean;

public class PagamentoBean {
    private final String data;
    private final String importo;
    private final String metodo;

    public PagamentoBean(String data, String importo, String metodo) {
        this.data = data;
        this.importo = importo;
        this.metodo = metodo;
    }

    public String getData() {
        return data;
    }

    public String getImporto() {
        return importo;
    }

    public String getMetodo() {
        return metodo;
    }
}
