package ispwproject.gymwizard.util.singleton;

import ispwproject.gymwizard.util.bean.SessionBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton per la gestione della sessione utente.
 */

// NOSONAR: Singleton necessario per gestione centralizzata della sessione
public class SessionManager {

    // ✅ Istanza Singleton eagerly inizializzata (thread-safe)
    private static final SessionManager instance = new SessionManager();

    private SessionBean session;
    private final Map<String, Object> attributi = new HashMap<>(); // Mappa per attributi personalizzati

    // ✅ Costruttore privato per impedire l'istanziazione esterna
    private SessionManager() {}

    // ✅ Metodo di accesso all’unica istanza
    public static SessionManager getInstance() {
        return instance;
    }

    // ✅ Imposta l’oggetto sessione
    public void setSession(SessionBean session) {
        this.session = session;
    }

    public SessionBean getSession() {
        return session;
    }

    // ✅ Pulisce sessione e attributi
    public void clearSession() {
        this.session = null;
        attributi.clear();
    }

    public boolean isLoggedIn() {
        return session != null;
    }

    // ✅ Attributi personalizzati
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
