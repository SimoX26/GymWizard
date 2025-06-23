package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.util.DAO.PagamentoDAO;
import ispwproject.gymwizard.util.DAO.PrenotazioneDAO;
import ispwproject.gymwizard.util.DAO.UtenteDAO;
import ispwproject.gymwizard.util.bean.PagamentoBean;
import ispwproject.gymwizard.util.bean.PrenotazioneBean;
import ispwproject.gymwizard.util.bean.UtenteAttivoBean;
import ispwproject.gymwizard.util.exception.DAOException;

import java.util.Collections;
import java.util.List;

public class ReportStatisticheController {

    private final PagamentoDAO pagamentoDAO = new PagamentoDAO();
    private final PrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();
    private final UtenteDAO utenteDAO = new UtenteDAO();

    public List<PagamentoBean> getStoricoPagamenti() {
        try {
            return pagamentoDAO.getStoricoPagamenti();
        } catch (DAOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<PrenotazioneBean> getStoricoPrenotazioni() {
        try {
            return prenotazioneDAO.getStoricoPrenotazioni();
        } catch (DAOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<UtenteAttivoBean> getUtentiAttivi() {
        try {
            return utenteDAO.getUtentiAttivi();
        } catch (DAOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}

