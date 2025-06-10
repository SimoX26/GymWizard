package ispwproject.gymwizard.util.observer.subjects.cliente;

import ispwproject.gymwizard.util.observer.observers.admin.Observer;

import java.util.ArrayList;
import java.util.List;

public class ClienteSubject implements Subject {

    private final List<Observer> observers = new ArrayList<>();

    private String username;
    private double peso; // peso attuale
    private boolean abbonamentoAttivo;

    public ClienteSubject(String username, double peso, boolean abbonamentoAttivo) {
        this.username = username;
        this.peso = peso;
        this.abbonamentoAttivo = abbonamentoAttivo;
    }

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(this);
        }
    }

    public String getUsername() {
        return username;
    }

    public double getPeso() {
        return peso;
    }

    public boolean isAbbonamentoAttivo() {
        return abbonamentoAttivo;
    }

    // Eventuali metodi per aggiornare i dati
    public void setPeso(double peso) {
        this.peso = peso;
        notifyObservers();
    }

    public void setAbbonamentoAttivo(boolean abbonamentoAttivo) {
        this.abbonamentoAttivo = abbonamentoAttivo;
        notifyObservers();
    }
}

