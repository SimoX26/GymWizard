package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.bean.ClienteBean;
import ispwproject.gymwizard.util.dao.DAOFactory;

import java.sql.SQLException;
import java.util.List;

public class ClientiController {


    public ClientiController() {
        //Costruttore vuoto
    }

    public void getClienti(ClienteBean bean) throws SQLException {
        bean.setListaClienti(DAOFactory.getUtenteDAO().getClienti());
    }
}
