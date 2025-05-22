package ispwproject.gymwizard.util.singleton;

import ispwproject.gymwizard.util.bean.SessionBean;

import java.util.HashMap;
import java.util.Map;

public class SessionManager {
    private static SessionManager instance;
    private SessionBean session;
    private final Map<String, Object> attributi = new HashMap<>(); // ✅ Mappa per attributi personalizzati

    private SessionManager() {}

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void setSession(SessionBean session) {
        this.session = session;
    }

    public SessionBean getSession() {
        return session;
    }

    public void clearSession() {
        this.session = null;
        attributi.clear(); // ✅ pulisci anche gli attributi
    }

    public boolean isLoggedIn() {
        return session != null;
    }

    // ✅ Metodi per attributi personalizzati
    public void setAttributo(String chiave, Object valore) {
        attributi.put(chiave, valore);
    }

    public Object getAttributo(String chiave) {
        return attributi.get(chiave);
    }

    public void removeAttributo(String chiave) {
        attributi.remove(chiave);
    }

    public void clearAll() {
        clearSession(); // delega
    }

}
