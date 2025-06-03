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

    public List<Attivita> getAttivitaDisponibiliMock(){
        List<Attivita> attivitaList = new ArrayList<>();

        attivitaList.add(new Attivita(1, "Yoga", "Lezione rilassante", LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(11, 0), 20, "Luca"));
        attivitaList.add(new Attivita(2, "Pilates", "Rinforzo posturale", LocalDate.now(), LocalTime.of(12, 0), LocalTime.of(13, 0), 8, "Marco"));
        attivitaList.add(new Attivita(3,"CrossFit", "Allenamento intensivo", LocalDate.now().plusDays(1), LocalTime.of(18, 0), LocalTime.of(19, 0), 12, "Chiara"));

        return attivitaList;
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

    public void prenotaAttivita(Attivita attivita) throws DAOException {
        // Ottieni ID cliente dalla sessione
        int idCliente = SessionManager.getInstance().getSession().getIdUtente(); // Assicurati che SessionBean abbia questo metodo

        // Chiamata alla DAO
        PrenotazioneDAO.getInstance().inserisciPrenotazione(attivita.getId(), idCliente);
    }
}
