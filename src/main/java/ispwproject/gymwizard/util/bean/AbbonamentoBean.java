package ispwproject.gymwizard.util.bean;

import ispwproject.gymwizard.model.Abbonamento;

import java.util.List;

public class AbbonamentoBean {
    private Abbonamento abbonamento;
    private List<Abbonamento> ListaAbbonamenti;

    public void setListaAbbonamenti(List<Abbonamento> ListaAbbonamenti){
        this.ListaAbbonamenti = ListaAbbonamenti;
    }

    public List<Abbonamento> getListaAbbonamenti() {
        return this.ListaAbbonamenti;
    }

    public Abbonamento getAbbonamento() {
        return this.abbonamento;
    }

    public void setAbbonamento(Abbonamento abbonamento) {
        this.abbonamento = abbonamento;
    }
}
