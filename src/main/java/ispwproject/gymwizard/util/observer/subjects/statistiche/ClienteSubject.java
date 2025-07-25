package ispwproject.gymwizard.util.observer.subjects.statistiche;

import ispwproject.gymwizard.util.logger.AppLogger;
import ispwproject.gymwizard.util.observer.subjects.Subject;

public class ClienteSubject extends Subject {

    private String username;
    private String ultimoAccesso;
    private boolean abbonamentoAttivo;

    private long lastNotificationTimestamp;
    private int timeOut = 2000; // intervallo per le notifiche

    public ClienteSubject(String username, String ultimoAccesso, boolean abbonamentoAttivo) {
        super();
        this.username = username;
        this.ultimoAccesso = ultimoAccesso;
        this.abbonamentoAttivo = abbonamentoAttivo;
        this.lastNotificationTimestamp = System.currentTimeMillis();
    }

    @Override
    protected boolean isThereAnythingToNotify() {
        long currentTime = System.currentTimeMillis();
        return (currentTime - this.lastNotificationTimestamp) > this.timeOut;
    }

    @Override
    protected void doSomething() {
        // Simula un aggiornamento dello stato (es: ultimo accesso)
        this.lastNotificationTimestamp = System.currentTimeMillis();

        // Usa AppLogger invece di System.out
        AppLogger.getLogger().info(() ->
                "Stato aggiornato: " + username + " | Abbonamento: " + (abbonamentoAttivo ? "Attivo" : "Non attivo")
        );

        try {
            Thread.sleep(1000); // Simula un’attività periodica
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Ripristina il flag di interruzione
            AppLogger.logError("Thread interrotto durante l’aggiornamento: " + e.getMessage());
        }
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getUltimoAccesso() {
        return ultimoAccesso;
    }

    public boolean isAbbonamentoAttivo() {
        return abbonamentoAttivo;
    }

    // Setters
    public void setUltimoAccesso(String ultimoAccesso) {
        this.ultimoAccesso = ultimoAccesso;
    }

    public void setAbbonamentoAttivo(boolean stato) {
        this.abbonamentoAttivo = stato;
    }
}
