package ispwproject.gymwizard.util.observer.subjects.cliente;

import ispwproject.gymwizard.util.observer.observers.admin.Observer;

public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
