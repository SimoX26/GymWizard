package ispwproject.gymwizard.util.bean;

import ispwproject.gymwizard.model.Abbonamento;

import java.util.List;

public class AbbonamentoBean {
    private Abbonamento abbonamento;
    private List<Abbonamento> listaAbbonamenti;

    public void setListaAbbonamenti(List<Abbonamento> listaAbbonamenti){
        this.listaAbbonamenti = listaAbbonamenti;
    }

    public List<Abbonamento> getListaAbbonamenti() {
        return this.listaAbbonamenti;
    }

    public Abbonamento getAbbonamento() {
        return this.abbonamento;
    }

    public void setAbbonamento(Abbonamento abbonamento) {
        this.abbonamento = abbonamento;
    }
}
