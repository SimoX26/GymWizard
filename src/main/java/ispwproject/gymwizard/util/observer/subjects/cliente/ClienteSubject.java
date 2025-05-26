package ispwproject.gymwizard.util.observer.subjects.cliente;

import ispwproject.gymwizard.util.observer.observers.admin.Observer;
import java.util.ArrayList;
import java.util.List;

public class ClienteSubject implements Subject {

    private final List<Observer> observers = new ArrayList<>();

    private String nome;
    private boolean abbonamentoAttivo;
    private double peso;

    public ClienteSubject(String nome) {
        this.nome = nome;
    }

    public void setPeso(double peso) {
        this.peso = peso;
        notifyObservers();
    }

    public void rinnovaAbbonamento() {
        this.abbonamentoAttivo = true;
        notifyObservers();
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

    public String getNome() {
        return nome;
    }

    public boolean isAbbonamentoAttivo() {
        return abbonamentoAttivo;
    }

    public double getPeso() {
        return peso;
    }
}
