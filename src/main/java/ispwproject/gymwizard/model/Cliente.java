package ispwproject.gymwizard.model;

import java.util.Date;

public class Cliente extends Utente {
    private String obiettiviPersonali;
    private Date iscrizione;

    public String getObiettiviPersonali(){
        return this.obiettiviPersonali;
    }

    public void setObiettiviPersonali(String obiettiviPersonali){
        this.obiettiviPersonali = obiettiviPersonali;
    }

    public Date getIscrizione(){
        return this.iscrizione;
    }

    public void setIscrizione(Date iscrizione){
        this.iscrizione = iscrizione;
    }

}
