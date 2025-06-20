package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.model.Credentials;
import ispwproject.gymwizard.util.DAO.UtenteDAO;
import ispwproject.gymwizard.util.DAO.ConnectionFactory;
import ispwproject.gymwizard.util.DAO.LoginProcedureDAO;
import ispwproject.gymwizard.util.bean.SessionBean;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.singleton.SessionManager;

public class LoginController {

    public enum LoginResult {
        SUCCESSO_CLIENTE,
        SUCCESSO_TRAINER,
        SUCCESSO_ADMIN,
        CREDENZIALI_INVALIDE,
        ERRORE
    }

    public LoginResult login(String email, String password) throws DAOException {
        try {
            Credentials fullCred = LoginProcedureDAO.getInstance().execute(email, password);

            if (fullCred == null || fullCred.getRole() == null) {
                return LoginResult.CREDENZIALI_INVALIDE;
            }

            // Configura il contesto DB in base al ruolo
            ConnectionFactory.changeRole(fullCred.getRole());

            // Salva la sessione
            SessionBean sessionBean = new SessionBean(fullCred.getEmail(), fullCred.getRole());
            // sessionBean.setUsername(UtenteDAO.getByEmail(email));
            SessionManager.getInstance().setSession(sessionBean);
            new UtenteDAO();
            SessionManager.getInstance().setAttributo("utente", UtenteDAO.getByEmail(email));

            return switch (fullCred.getRole()) {
                case CLIENTE -> LoginResult.SUCCESSO_CLIENTE;
                case TRAINER -> LoginResult.SUCCESSO_TRAINER;
                case ADMIN -> LoginResult.SUCCESSO_ADMIN;
            };

        } catch (Exception e) {
            e.printStackTrace();
            return LoginResult.ERRORE;
        }
    }
}
