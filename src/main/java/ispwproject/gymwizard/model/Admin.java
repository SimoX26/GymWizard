package ispwproject.gymwizard.model;

public class Admin extends Utente{
    private String ruolo;

    public String getRuolo(){
        return this.ruolo;
    }

    public void setRuolo(String ruolo){
        this.ruolo = ruolo;
    }
}
