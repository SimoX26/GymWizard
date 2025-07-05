package ispwproject.gymwizard.controller.demo;

import ispwproject.gymwizard.controller.app.AttivitaController;
import ispwproject.gymwizard.model.Attivita;
import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.exception.AttivitaDuplicataException;
import ispwproject.gymwizard.util.exception.AttivitaPienaException;
import ispwproject.gymwizard.util.singleton.SessionManager;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AttivitaControllerDemo extends AttivitaController {

    private static final List<Attivita> attivitaDemo = new ArrayList<>();
    private static int nextId = 1;

    static {
        // Inizializza demo con alcune attività fittizie
        attivitaDemo.add(new Attivita(nextId++, "Yoga", "Lezione di rilassamento", LocalDate.now().plusDays(1), LocalTime.of(10, 0), LocalTime.of(11, 0), 10, "Anna"));
        attivitaDemo.add(new Attivita(nextId++, "Crossfit", "Allenamento ad alta intensità", LocalDate.now().plusDays(2), LocalTime.of(18, 0), LocalTime.of(19, 0), 5, "Marco"));
    }

    @Override
    public List<Attivita> getAttivitaDisponibili() {
        return new ArrayList<>(attivitaDemo);  // restituisce una copia
    }

    @Override
    public void creaAttivita(String nome, String descrizione, LocalDate data, LocalTime oraInizio, LocalTime oraFine, int posti, String trainerName)
            throws AttivitaDuplicataException {

        for (Attivita a : attivitaDemo) {
            if (a.getNome().equalsIgnoreCase(nome) && a.getData().equals(data) && a.getOraInizio().equals(oraInizio)) {
                throw new AttivitaDuplicataException(nome);
            }
        }

        Attivita nuova = new Attivita(nextId++, nome, descrizione, data, oraInizio, oraFine, posti, trainerName);
        attivitaDemo.add(nuova);
    }

    @Override
    public void prenotaAttivita(Attivita attivita) throws AttivitaPienaException {
        Utente utente = (Utente) SessionManager.getInstance().getAttributo("utente");

        if (utente == null) {
            System.out.println("Nessun utente loggato.");
            return;
        }

        for (Attivita a : attivitaDemo) {
            if (a.getId() == attivita.getId()) {
                if (a.getPostiDisponibili() <= 0) {
                    throw new AttivitaPienaException(a.getNome());
                }
                a.setPostiDisponibili(a.getPostiDisponibili() - 1);
                System.out.println("Prenotazione DEMO effettuata per " + utente.getUsername() + " su " + a.getNome());
                return;
            }
        }

        System.out.println("Attività non trovata nella demo.");
    }
}
