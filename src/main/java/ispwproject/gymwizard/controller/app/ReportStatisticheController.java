package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.util.bean.AbbonamentoBean;
import ispwproject.gymwizard.util.dao.AbbonamentoDAO;
import ispwproject.gymwizard.util.dao.DAOFactory;
import ispwproject.gymwizard.util.dao.PrenotazioneDAO;
import ispwproject.gymwizard.util.dao.UtenteDAO;
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

    private static final AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO();
    private static final PrenotazioneDAO prenotazioneDAO = DAOFactory.getPrenotazioneDAO();
    private static final UtenteDAO utenteDAO = DAOFactory.getUtenteDAO();

    public static void getStoricoAbbonamenti(AbbonamentoBean bean) {
        try {
            bean.setListaAbbonamenti(abbonamentoDAO.getAllDisponibili());
        } catch (DAOException e) {
            AppLogger.getLogger().log(Level.SEVERE, ERROR_MESSAGE, e);
            bean.setListaAbbonamenti(Collections.emptyList());
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

