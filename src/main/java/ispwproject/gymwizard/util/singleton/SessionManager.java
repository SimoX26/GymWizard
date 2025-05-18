package ispwproject.gymwizard.util.singleton;

import ispwproject.gymwizard.util.bean.SessionBean;

import java.util.HashMap;
import java.util.Map;

public class SessionManager {

    private static final SessionManager instance = new SessionManager();

    private SessionBean sessionBean;

    // Contesto dinamico (solo stringhe)
    private final Map<String, String> attributi = new HashMap<>();

    private SessionManager() {}

    public static SessionManager getInstance() {
        return instance;
    }

    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    // Attributi dinamici
    public void setAttributo(String key, String value) {
        attributi.put(key, value);
    }

    public String getAttributo(String key) {
        return attributi.get(key);
    }

    public void removeAttributo(String key) {
        attributi.remove(key);
    }

    public void clearAttributi() {
        attributi.clear();
    }

    // Pulizia completa (es. al logout)
    public void clearAll() {
        sessionBean = null;
        attributi.clear();
    }
}
