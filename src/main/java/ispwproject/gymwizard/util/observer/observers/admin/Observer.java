package ispwproject.gymwizard.util.observer.observers.admin;

import ispwproject.gymwizard.util.observer.subjects.cliente.ClienteSubject;

public interface Observer {
    void update(ClienteSubject cliente);
}
