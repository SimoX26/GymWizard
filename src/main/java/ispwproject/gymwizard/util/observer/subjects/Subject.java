package ispwproject.gymwizard.util.observer.subjects;

import ispwproject.gymwizard.util.observer.observers.Observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public abstract class Subject implements Runnable {

    private List<Observer> observers;
    private final Object mutex = new Object();
    protected boolean isAlive;

    protected Subject() {
        this((Observer) null);
    }

    protected Subject(Observer obs) {
        this(new Vector<>());
        if (obs != null)
            this.observers.add(obs);
    }

    protected Subject(List<Observer> list) {
        this.observers = list;
        this.isAlive = false;
    }

    public void attach(Observer obs) {
        synchronized (mutex) {
            this.observers.add(obs);
        }
    }

    public void detach(Observer obs) {
        synchronized (mutex) {
            this.observers.remove(obs);
        }
    }

    protected void notifyObservers() {
        List<Observer> observersLocal = null;

        synchronized (mutex) {
            if (this.isThereAnythingToNotify())
                observersLocal = new ArrayList<>(this.observers);
        }

        if (observersLocal != null) {
            Iterator<Observer> i = observersLocal.iterator();
            while (i.hasNext()) {
                Observer obs = i.next();
                obs.update();
            }
        }
    }

    @Override
    public void run() {
        this.isAlive = true;
        while (this.isAlive) {
            this.doSomething();
            this.notifyObservers();
        }
    }

    public void stopIt() {
        this.isAlive = false;
    }

    protected abstract boolean isThereAnythingToNotify();
    protected abstract void doSomething();
}
