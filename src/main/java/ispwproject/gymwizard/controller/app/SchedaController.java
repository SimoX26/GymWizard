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

    public static void aggiungiEsercizio(EsercizioScheda esercizio) throws DAOException {
        dao.insertEsercizio(esercizio);
    }
}
