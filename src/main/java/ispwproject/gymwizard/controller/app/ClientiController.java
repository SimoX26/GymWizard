package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.dao.UtenteDAO;

import java.sql.SQLException;
import java.util.List;

public class ClientiController {


    public ClientiController() {
    }

    public List<Utente> getClienti() throws SQLException {
        return UtenteDAO.getInstance().getClienti();
    }
}
