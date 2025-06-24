package ispwproject.gymwizard.model;

public class Pagamento {
    private String orderId;
    private String stato;
    private double importo;
    private String metodoPagamento;
    private String payerId;
    private int idAbbonamento;

    public Pagamento(String orderId, String stato, double importo, String metodoPagamento, String payerId, int idAbbonamento) {
        this.orderId = orderId;
        this.stato = stato;
        this.importo = importo;
        this.metodoPagamento = metodoPagamento;
        this.payerId = payerId;
        this.idAbbonamento = idAbbonamento;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public double getImporto() {
        return importo;
    }

    public void setImporto(double importo) {
        this.importo = importo;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public int getIdAbbonamento() {
        return idAbbonamento;
    }

    public void setIdAbbonamento(int idAbbonamento) {
        this.idAbbonamento = idAbbonamento;
    }
}
