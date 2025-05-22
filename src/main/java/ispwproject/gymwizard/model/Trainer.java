package ispwproject.gymwizard.model;

public class Trainer extends Utente {
    private String specializzazione;
    private int numeroClienti;

    public String getSpecializzazione(){
        return this.specializzazione;
    }
    public void setSpecializzazione(String specializzazione){
        this.specializzazione = specializzazione;
    }
    public int getNumeroClienti(){
        return this.numeroClienti;
    }
    public void setNumeroClienti(int numeroClienti){
        this.numeroClienti = numeroClienti;
    }
}
