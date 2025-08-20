package ispwproject.gymwizard.util.bean;

import ispwproject.gymwizard.model.EsercizioScheda;
import ispwproject.gymwizard.model.Scheda;

import java.util.List;

public class SchedaBean {
    List<EsercizioScheda> listaEsercizi;
    List<Scheda> listaSchede;

    public void setEserciziScheda(List<EsercizioScheda> listaEsercizi){
        this.listaEsercizi = listaEsercizi;
    }

    public List<EsercizioScheda> getEserciziScheda(){
        return this.listaEsercizi;
    }

    public void setListaSchede(List<Scheda> listaSchede){
        this.listaSchede = listaSchede;
    }

    public List<Scheda> getListaSchede(){
        return this.listaSchede;
    }
}
