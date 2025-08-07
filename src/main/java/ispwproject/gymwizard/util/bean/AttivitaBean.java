package ispwproject.gymwizard.util.bean;

import ispwproject.gymwizard.model.Attivita;

import java.util.List;

public class AttivitaBean {
    private List<Attivita> attivitaList;
    private Attivita attivita;

    public List<Attivita> getAttivita(){
        return this.attivitaList;
    }

    public void setAttivita(Attivita attivita){
        this.attivita = attivita;
    }
}
