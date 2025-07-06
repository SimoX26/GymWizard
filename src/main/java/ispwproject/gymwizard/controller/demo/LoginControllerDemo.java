package ispwproject.gymwizard.controller.demo;

import ispwproject.gymwizard.controller.app.LoginController;
import ispwproject.gymwizard.model.Role;
import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.bean.SessionBean;
import ispwproject.gymwizard.util.exception.CredenzialiException;
import ispwproject.gymwizard.util.singleton.SessionManager;

import java.sql.SQLException;

public class LoginControllerDemo extends LoginController {

    @Override
    public LoginResult login(String email, String password) throws CredenzialiException, SQLException {
        Utente utente;
        SessionBean sessionBean;

        switch (email.toLowerCase()) {
            case "cliente1@demo" -> {
                utente = new Utente(1, "Cliente 1", email);
                sessionBean = new SessionBean(email, Role.CLIENTE);
                sessionBean.setUsername("Cliente 1");
                SessionManager.getInstance().setSession(sessionBean);
                SessionManager.getInstance().setAttributo("utente", utente);
                return LoginResult.SUCCESSO_CLIENTE;
            }
            case "trainer1@demo" -> {
                utente = new Utente(2, "Trainer 1", email);
                sessionBean = new SessionBean(email, Role.TRAINER);
                sessionBean.setUsername("Trainer 1");
                SessionManager.getInstance().setSession(sessionBean);
                SessionManager.getInstance().setAttributo("utente", utente);
                return LoginResult.SUCCESSO_TRAINER;
            }
            case "admin1@demo" -> {
                utente = new Utente(3, "Admin 1", email);
                sessionBean = new SessionBean(email, Role.ADMIN);
                sessionBean.setUsername("Admin 1");
                SessionManager.getInstance().setSession(sessionBean);
                SessionManager.getInstance().setAttributo("utente", utente);
                return LoginResult.SUCCESSO_ADMIN;
            }
            default -> {
                return null;
            }
        }
    }
}
