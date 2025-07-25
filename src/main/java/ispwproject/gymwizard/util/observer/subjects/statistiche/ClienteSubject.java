package ispwproject.gymwizard.util.observer.subjects.statistiche;

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
        // Qui potresti simulare un aggiornamento dello stato (es: ultimo accesso)
        this.lastNotificationTimestamp = System.currentTimeMillis();
        // Per debug:
        System.out.println("Stato aggiornato: " + username + " | Abbonamento: " + (abbonamentoAttivo ? "Attivo" : "Non attivo"));
        try {
            Thread.sleep(1000); // Simula un’attività periodica
        } catch (InterruptedException e) {
            e.printStackTrace();
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

    // Setters (ora senza notify diretto)
    public void setUltimoAccesso(String ultimoAccesso) {
        this.ultimoAccesso = ultimoAccesso;
    }

    public void setAbbonamentoAttivo(boolean stato) {
        this.abbonamentoAttivo = stato;
    }
}
