package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.model.Credentials;
import ispwproject.gymwizard.util.DAO.LoginProcedureDAO;
import ispwproject.gymwizard.util.exception.DAOException;

public class LoginController {

    private final Credentials credentials;

    public LoginController(Credentials credentials) {
        this.credentials = credentials;
    }

    public Credentials login() throws DAOException {
        LoginProcedureDAO loginDAO = LoginProcedureDAO.getInstance();
        return loginDAO.execute(credentials.getEmail(), credentials.getPassword());
    }
}
