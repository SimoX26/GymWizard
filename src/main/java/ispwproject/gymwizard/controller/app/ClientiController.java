package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.DAO.UtenteDAO;

import java.sql.SQLException;
import java.util.List;

public class ClientiController {

    public static List<Utente> getClienti() throws SQLException {
        UtenteDAO utenteDAO = new UtenteDAO();
        return utenteDAO.getClienti();
    }

}
