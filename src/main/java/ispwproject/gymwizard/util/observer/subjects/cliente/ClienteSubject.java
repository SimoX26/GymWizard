package ispwproject.gymwizard.util.observer.subjects.cliente;

import ispwproject.gymwizard.util.observer.observers.admin.Observer;

import java.util.ArrayList;
import java.util.List;

public class ClienteSubject implements Subject {

    private final List<Observer> observers = new ArrayList<>();
    private String username;
    private String ultimoAccesso;
    private boolean abbonamentoAttivo;

    public ClienteSubject(String username, String ultimoAccesso, boolean abbonamentoAttivo) {
        this.username = username;
        this.ultimoAccesso = ultimoAccesso;
        this.abbonamentoAttivo = abbonamentoAttivo;
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(this);
        }
    }

    // Getters per l'observer
    public String getUsername() {
        return username;
    }

    public String getUltimoAccesso() {
        return ultimoAccesso;
    }

    public boolean isAbbonamentoAttivo() {
        return abbonamentoAttivo;
    }

    // Setters che notificano
    public void setUltimoAccesso(String ultimoAccesso) {
        this.ultimoAccesso = ultimoAccesso;
        notifyObservers();
    }

    public void setAbbonamentoAttivo(boolean stato) {
        this.abbonamentoAttivo = stato;
        notifyObservers();
    }
}


