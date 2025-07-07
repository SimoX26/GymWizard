package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.model.Credentials;
import ispwproject.gymwizard.util.dao.UtenteDAO;
import ispwproject.gymwizard.util.dao.ConnectionFactory;
import ispwproject.gymwizard.util.dao.LoginProcedureDAO;
import ispwproject.gymwizard.util.bean.SessionBean;
import ispwproject.gymwizard.util.exception.CredenzialiException;
import ispwproject.gymwizard.util.singleton.SessionManager;

import java.sql.SQLException;

public class LoginController {

    public enum LoginResult {
        SUCCESSO_CLIENTE,
        SUCCESSO_TRAINER,
        SUCCESSO_ADMIN
    }

    public LoginResult login(String email, String password) throws CredenzialiException, SQLException {
        Credentials fullCred = LoginProcedureDAO.getInstance().execute(email, password);

        if (fullCred == null || fullCred.getRole() == null) {
            throw new CredenzialiException();
        }

        // Configura il contesto DB in base al ruolo
        ConnectionFactory.changeRole(fullCred.getRole());

        // Salva la sessione
        SessionBean sessionBean = new SessionBean(fullCred.getEmail(), fullCred.getRole());
        SessionManager.getInstance().setSession(sessionBean);

        SessionManager.getInstance().setAttributo("utente", UtenteDAO.getInstance().getByEmail(email));

        return switch (fullCred.getRole()) {
            case CLIENTE -> LoginResult.SUCCESSO_CLIENTE;
            case TRAINER -> LoginResult.SUCCESSO_TRAINER;
            case ADMIN -> LoginResult.SUCCESSO_ADMIN;
        };
    }


}
