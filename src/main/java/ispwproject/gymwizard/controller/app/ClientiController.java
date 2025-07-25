package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.dao.DAOFactory;

import java.sql.SQLException;
import java.util.List;

public class ClientiController {


    public ClientiController() {
        //Costruttore vuoto
    }

    public List<Utente> getClienti() throws SQLException {
        return DAOFactory.getUtenteDAO().getClienti();
    }
}
