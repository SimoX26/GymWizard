package ispwproject.gymwizard.util.singleton;

import ispwproject.gymwizard.util.bean.SessionBean;

import java.util.HashMap;
import java.util.Map;

public class SessionManager { //NOSONAR

    private SessionBean session;
    private final Map<String, Object> attributi = new HashMap<>();

    // Costruttore privato
    private SessionManager() {}

    // Lazy Holder: inizializzazione solo al primo getInstance
    private static class Holder {
        private static final SessionManager INSTANCE = new SessionManager();
    }

    public static SessionManager getInstance() {
        return Holder.INSTANCE;
    }

    // Imposta l’oggetto sessione
    public void setSession(SessionBean session) {
        this.session = session;
    }

    public SessionBean getSession() {
        return session;
    }

    // Pulisce sessione e attributi
    public void clearSession() {
        this.session = null;
        attributi.clear();
    }

    // Attributi personalizzati
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
        clearSession();
    }
}
