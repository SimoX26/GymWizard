package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.model.Attivita;
import ispwproject.gymwizard.util.DAO.AttivitaDAO;
import ispwproject.gymwizard.util.DAO.PrenotazioneDAO;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.singleton.SessionManager;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AttivitaController {

    public static List<Attivita> getAttivitaDisponibili() throws DAOException {
            return AttivitaDAO.getInstance().getAllDisponibili();
    }

    public void creaAttivita(Attivita attivita) throws DAOException {
        Attivita nuova = new Attivita(
                0,
                attivita.getNome(),
                attivita.getDescrizione(),
                attivita.getData(),
                attivita.getOraInizio(),
                attivita.getOraFine(),
                attivita.getPostiDisponibili(),
                attivita.getTrainerName()
        );

        AttivitaDAO.getInstance().inserisciAttivita(nuova);
    }
/*
  public void prenotaAttivita(Attivita attivita) throws DAOException {
        // Ottieni ID cliente dalla sessione
        int idCliente = SessionManager.getInstance().getSession().getIdUtente(); // Assicurati che SessionBean abbia questo metodo

        // Chiamata alla DAO
        PrenotazioneDAO.getInstance().inserisciPrenotazione(attivita.getId(), idCliente);
    }
 */
}
