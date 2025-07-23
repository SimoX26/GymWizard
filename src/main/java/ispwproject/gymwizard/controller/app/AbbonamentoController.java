package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.dao.AbbonamentoDAO;
import ispwproject.gymwizard.model.Abbonamento;
import ispwproject.gymwizard.util.exception.BrowserAperturaException;
import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.util.BrowserLauncher;
import java.time.LocalDate;

public class AbbonamentoController {

    public static final String MENSILE = "mensile";
    public static final String TRIMESTRALE = "trimestrale";
    public static final String ANNUALE = "annuale";
    public static final String INGRESSI = "10ingressi";

    public Abbonamento getDatiAbbonamento() {
        Utente utente = (Utente) SessionManager.getInstance().getAttributo("utente");
        int idUtente = utente.getId();
        return AbbonamentoDAO.getInstance().trovaAbbonamentoAttivoPerUtente(idUtente);
    }

    public static void aggiungiAbbonamento(String tipo, String riferimentoPagamento) {
        Utente utente = (Utente) SessionManager.getInstance().getAttributo("utente");
        AbbonamentoDAO dao = AbbonamentoDAO.getInstance();

        Abbonamento abbonamentoEsistente = dao.trovaAbbonamentoAttivoPerUtente(utente.getId());
        if (abbonamentoEsistente != null) {
            throw new IllegalStateException("Esiste già un abbonamento attivo per questo utente.");
        }

        Abbonamento nuovoAbbonamento = new Abbonamento();
        nuovoAbbonamento.setIdUtente(utente.getId());
        nuovoAbbonamento.setTipo(tipo);
        nuovoAbbonamento.setDataInizio(getDataEmissione());
        nuovoAbbonamento.setDataFine(getDataScadenza(tipo));
        nuovoAbbonamento.setStato("attivo");
        nuovoAbbonamento.setRiferimentoPagamento(riferimentoPagamento);

        dao.inserisciAbbonamento(nuovoAbbonamento);
    }

    public static String getNomeAbbonamento(String tipo) {
        return switch (tipo.toLowerCase()) {
            case MENSILE -> "Abbonamento Mensile";
            case TRIMESTRALE -> "Abbonamento Trimestrale";
            case ANNUALE -> "Abbonamento Annuale";
            case INGRESSI -> "Abbonamento 10 Ingressi";
            default -> "Abbonamento Sconosciuto";
        };
    }

    public static String getDescrizioneAbbonamento(String tipo) {
        return switch (tipo.toLowerCase()) {
            case MENSILE -> """
                Abbonamento Mensile:
                Valido 30 giorni dalla data di attivazione.
                Accesso illimitato a tutte le attività e corsi base.
                Rinnovabile automaticamente o manualmente.
                Ideale per utenti regolari.
            """;
            case TRIMESTRALE -> """
                Abbonamento Trimestrale:
                Valido 90 giorni dalla data di attivazione.
                Ottimo rapporto qualità/prezzo per una frequenza costante.
                Include sconti su personal trainer e workshop.
                Pensato per chi vuole mantenere una routine a medio termine.
            """;
            case ANNUALE -> """
                Abbonamento Annuale:
                Valido 365 giorni con vantaggi esclusivi.
                Accesso prioritario, eventi riservati e sospensione ferie inclusa.
                Massima convenienza per clienti fedeli.
                Raccomandato per chi frequenta regolarmente tutto l'anno.
            """;
            case INGRESSI -> """
                Abbonamento 10 Ingressi:
                Pacchetto flessibile con 10 accessi singoli.
                Nessuna scadenza immediata.
                Ideale per utenti occasionali o saltuari.
                Accesso valido per qualsiasi giorno e orario.
            """;
            default -> "Tipo di abbonamento non riconosciuto.";
        };
    }

    public static LocalDate getDataEmissione() {
        return LocalDate.now();
    }

    public static LocalDate getDataScadenza(String tipo) {
        return switch (tipo.toLowerCase()) {
            case MENSILE -> LocalDate.now().plusDays(30);
            case TRIMESTRALE -> LocalDate.now().plusDays(90);
            case ANNUALE, INGRESSI -> LocalDate.now().plusDays(365);

            default -> null;
        };
    }


    public static int getPrezzoAbbonamento(String tipo) {
        return switch (tipo.toLowerCase()) {
            case MENSILE -> 5000;
            case TRIMESTRALE -> 11000;
            case ANNUALE -> 33000;
            case INGRESSI -> 2500;
            default -> 0;
        };
    }

    public static void apriNelBrowser(String url) throws BrowserAperturaException {
        BrowserLauncher.apriUrlNelBrowser(url);
    }


}
