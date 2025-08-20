package ispwproject.gymwizard.util.bean;

import ispwproject.gymwizard.model.Utente;

import java.util.List;

public class ClienteBean {
    private List<Utente> listaClienti;

    public List<Utente> getListaClienti(){
        return this.listaClienti;
    }

    public void setListaClienti(List<Utente> listaClienti){
        this.listaClienti = listaClienti;
    }
}
