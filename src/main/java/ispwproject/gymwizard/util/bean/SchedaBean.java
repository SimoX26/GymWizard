package ispwproject.gymwizard.util.bean;

import ispwproject.gymwizard.model.EsercizioScheda;
import ispwproject.gymwizard.model.Scheda;

import java.util.List;

public class SchedaBean {
    List<EsercizioScheda> ListaEsercizi;
    List<Scheda> ListaSchede;

    public void setEserciziScheda(List<EsercizioScheda> ListaEsercizi){
        this.ListaEsercizi = ListaEsercizi;
    }

    public List<EsercizioScheda> getEserciziScheda(){
        return this.ListaEsercizi;
    }

    public void setListaSchede(List<Scheda> ListaSchede){
        this.ListaSchede = ListaSchede;
    }

    public List<Scheda> getListaSchede(){
        return this.ListaSchede;
    }
}
