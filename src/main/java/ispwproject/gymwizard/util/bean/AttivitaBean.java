package ispwproject.gymwizard.util.bean;

import ispwproject.gymwizard.model.Attivita;

import java.util.List;

public class AttivitaBean {
    private List<Attivita> attivita;

    public List<Attivita> getAttivita(){
        return this.attivita;
    }

    public void setAttivita(List<Attivita> attivita){
        this.attivita = attivita;
    }
}
