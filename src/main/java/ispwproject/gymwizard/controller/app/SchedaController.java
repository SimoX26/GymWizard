package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.model.EsercizioScheda;
import ispwproject.gymwizard.util.DAO.EsercizioSchedaDAO;
import ispwproject.gymwizard.util.exception.DAOException;

import java.util.List;

public class SchedaController {

    private static final EsercizioSchedaDAO dao = new EsercizioSchedaDAO();

    public static List<EsercizioScheda> getEserciziSchedaByIdCliente(int idCliente) throws DAOException {
        return dao.getEserciziByClientId(idCliente);
    }

    public void aggiungiEsercizio(int idScheda, String nomeEsercizio, int serie, int ripetizioni, String note)
            throws DAOException, EsercizioDuplicatoException {

        // Controllo duplicati
        if (EsercizioSchedaDAO.getInstance().existsEsercizio(idScheda, nomeEsercizio)) {
            throw new EsercizioDuplicatoException(nomeEsercizio);
        }

        EsercizioScheda nuovo = new EsercizioScheda(idScheda, nomeEsercizio, serie, ripetizioni, note);
        EsercizioSchedaDAO.getInstance().insertEsercizio(nuovo);
    }

    public static class EsercizioDuplicatoException extends Exception {
        public EsercizioDuplicatoException(String nomeEsercizio) {
            super("L'esercizio \"" + nomeEsercizio + "\" è già presente nella scheda.");
        }
    }
}
