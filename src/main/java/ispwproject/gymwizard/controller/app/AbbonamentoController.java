package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.DAO.AbbonamentoDAO;
import ispwproject.gymwizard.model.Abbonamento;
import ispwproject.gymwizard.util.singleton.SessionManager;

import java.time.LocalDate;

public class AbbonamentoController {

    public static Abbonamento getDatiAbbonamento() {
        Utente utente = (Utente) SessionManager.getInstance().getAttributo("utente");
        int idUtente = utente.getId();

        return AbbonamentoDAO.trovaAbbonamentoAttivoPerUtente(idUtente);
    }

    public static String getNomeAbbonamento(String tipo) {
        return switch (tipo.toLowerCase()) {
            case "mensile" -> "Abbonamento Mensile";
            case "trimestrale" -> "Abbonamento Trimestrale";
            case "annuale" -> "Abbonamento Annuale";
            case "10ingressi" -> "Abbonamento 10 Ingressi";
            default -> "Abbonamento Sconosciuto";
        };
    }

    public static String getDescrizioneAbbonamento(String tipo) {
        return switch (tipo.toLowerCase()) {
            case "mensile" -> """
                Abbonamento Mensile:
                Valido 30 giorni dalla data di attivazione.
                Accesso illimitato a tutte le attività e corsi base.
                Rinnovabile automaticamente o manualmente.
                Ideale per utenti regolari.
            """;
            case "trimestrale" -> """
                Abbonamento Trimestrale:
                Valido 90 giorni dalla data di attivazione.
                Ottimo rapporto qualità/prezzo per una frequenza costante.
                Include sconti su personal trainer e workshop.
                Pensato per chi vuole mantenere una routine a medio termine.
            """;
            case "annuale" -> """
                Abbonamento Annuale:
                Valido 365 giorni con vantaggi esclusivi.
                Accesso prioritario, eventi riservati e sospensione ferie inclusa.
                Massima convenienza per clienti fedeli.
                Raccomandato per chi frequenta regolarmente tutto l'anno.
            """;
            case "10ingressi" -> """
                Abbonamento 10 Ingressi:
                Pacchetto flessibile con 10 accessi singoli.
                Nessuna scadenza immediata.
                Ideale per utenti occasionali o saltuari.
                Accesso valido per qualsiasi giorno e orario.
            """;
            default -> "Tipo di abbonamento non riconosciuto.";
        };
    }

    public static LocalDate getDataEmissione(String tipo) {
        return LocalDate.now();
    }

    public static LocalDate getDataScadenza(String tipo) {
        return switch (tipo.toLowerCase()) {
            case "mensile" -> LocalDate.now().plusDays(30);
            case "trimestrale" -> LocalDate.now().plusDays(90);
            case "annuale" -> LocalDate.now().plusDays(365);
            default -> null;
        };
    }
}
