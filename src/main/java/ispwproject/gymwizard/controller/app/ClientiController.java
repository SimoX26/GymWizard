package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.DAO.UtenteDAO;

import java.sql.SQLException;
import java.util.List;

public class ClientiController {

    private final UtenteDAO utenteDAO;

    public ClientiController() {
        this.utenteDAO = new UtenteDAO();
    }

    public List<Utente> getClienti() throws SQLException {
        return utenteDAO.getClienti();
    }
}
