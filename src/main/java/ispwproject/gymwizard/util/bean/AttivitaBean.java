package ispwproject.gymwizard.util.bean;

import ispwproject.gymwizard.model.Attivita;

import java.util.List;

public class AttivitaBean {
    private List<Attivita> attivitaList;

    public List<Attivita> getAttivita(){
        return this.attivitaList;
    }

    public void setAttivita(List<Attivita> attivitaList){
        this.attivitaList = attivitaList;
    }
}
