package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.model.Abbonamento;
import ispwproject.gymwizard.util.DAO.AbbonamentoDAO;
import ispwproject.gymwizard.util.DAO.PrenotazioneDAO;
import ispwproject.gymwizard.util.DAO.UtenteDAO;
import ispwproject.gymwizard.util.bean.PrenotazioneBean;
import ispwproject.gymwizard.util.bean.UtenteAttivoBean;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.logger.AppLogger;
import java.util.logging.Level;

import java.util.Collections;
import java.util.List;

public class ReportStatisticheController {

    private ReportStatisticheController() {
        throw new UnsupportedOperationException("Classe di utilità - non istanziabile.");
    }

    public static final String ERROR_MESSAGE = "Errore durante il recupero dei dati dal database: ";

    private static final AbbonamentoDAO abbonamentoDAO = AbbonamentoDAO.getInstance();
    private static final PrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();
    private static final UtenteDAO utenteDAO = new UtenteDAO();

    public static List<Abbonamento> getStoricoAbbonamenti() {
        try {
            return abbonamentoDAO.getAllDisponibili();
        } catch (DAOException e) {
            AppLogger.getLogger().log(Level.SEVERE, ERROR_MESSAGE, e);
            return Collections.emptyList();
        }

    }

    public static List<PrenotazioneBean> getStoricoPrenotazioni() {
        try {
            return prenotazioneDAO.getStoricoPrenotazioni();
        } catch (DAOException e) {
            AppLogger.getLogger().log(Level.SEVERE, ERROR_MESSAGE, e);
            return Collections.emptyList();
        }
    }

    public static List<UtenteAttivoBean> getUtentiAttivi() {
        try {
            return utenteDAO.getUtentiAttivi();
        } catch (DAOException e) {
            AppLogger.getLogger().log(Level.SEVERE, ERROR_MESSAGE, e);
            return Collections.emptyList();
        }
    }

}

