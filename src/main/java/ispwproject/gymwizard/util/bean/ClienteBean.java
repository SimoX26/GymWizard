package ispwproject.gymwizard.util.bean;

import ispwproject.gymwizard.model.Utente;

import java.util.List;

public class ClienteBean {
    private List<Utente> ListaClienti;

    public List<Utente> getListaClienti(){
        return this.ListaClienti;
    }

    public void setListaClienti(List<Utente> ListaClienti){
        this.ListaClienti = ListaClienti;
    }
}
