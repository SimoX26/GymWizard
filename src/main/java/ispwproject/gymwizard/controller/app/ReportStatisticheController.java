package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.model.Abbonamento;
import ispwproject.gymwizard.util.DAO.AbbonamentoDAO;
import ispwproject.gymwizard.util.DAO.PrenotazioneDAO;
import ispwproject.gymwizard.util.DAO.UtenteDAO;
import ispwproject.gymwizard.util.bean.PrenotazioneBean;
import ispwproject.gymwizard.util.bean.UtenteAttivoBean;
import ispwproject.gymwizard.util.exception.DAOException;

import java.util.Collections;
import java.util.List;

public class ReportStatisticheController {

    private final AbbonamentoDAO abbonamentoDAO = AbbonamentoDAO.getInstance();
    private final PrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();
    private final UtenteDAO utenteDAO = new UtenteDAO();

    public List<Abbonamento> getStoricoAbbonamenti() {
        try {
            return abbonamentoDAO.getAllDisponibili();
        } catch (DAOException e) {
            System.err.println("Errore durante il recupero dei dati dal database: " + e.getMessage());
            return Collections.emptyList();

        }
    }

    public List<PrenotazioneBean> getStoricoPrenotazioni() {
        try {
            return prenotazioneDAO.getStoricoPrenotazioni();
        } catch (DAOException e) {
            System.err.println("Errore durante il recupero dei dati dal database: " + e.getMessage());
            return Collections.emptyList();

        }
    }

    public List<UtenteAttivoBean> getUtentiAttivi() {
        try {
            return utenteDAO.getUtentiAttivi();
        } catch (DAOException e) {
            System.err.println("Errore durante il recupero dei dati dal database: " + e.getMessage());
            return Collections.emptyList();

        }
    }
}

