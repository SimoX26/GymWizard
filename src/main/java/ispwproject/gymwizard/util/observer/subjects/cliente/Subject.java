package ispwproject.gymwizard.util.observer.subjects.cliente;

import ispwproject.gymwizard.util.observer.observers.admin.Observer;

public interface Subject {
    void attach(Observer o);
    void detach(Observer o);
    void notifyObservers();
}
