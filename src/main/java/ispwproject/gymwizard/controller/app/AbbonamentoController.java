package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.DAO.AbbonamentoDAO;
import ispwproject.gymwizard.model.Abbonamento;
import ispwproject.gymwizard.util.singleton.SessionManager;

import java.awt.*;
import java.net.URI;
import java.time.LocalDate;

public class AbbonamentoController {

    public static final String MENSILE = "mensile";


    public Abbonamento getDatiAbbonamento() {
        Utente utente = (Utente) SessionManager.getInstance().getAttributo("utente");
        int idUtente = utente.getId();

        return AbbonamentoDAO.getInstance().trovaAbbonamentoAttivoPerUtente(idUtente);
    }

    public void aggiungiAbbonamento(String tipo, String riferimentoPagamento) {
        Utente utente = (Utente) SessionManager.getInstance().getAttributo("utente");

        AbbonamentoDAO dao = AbbonamentoDAO.getInstance();

        // ✅ BLOCCO se esiste già un abbonamento attivo
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
            case "trimestrale" -> "Abbonamento Trimestrale";
            case "annuale" -> "Abbonamento Annuale";
            case "10ingressi" -> "Abbonamento 10 Ingressi";
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

    public static LocalDate getDataEmissione() {
        return LocalDate.now();
    }

    public static LocalDate getDataScadenza(String tipo) {
        return switch (tipo.toLowerCase()) {
            case MENSILE -> LocalDate.now().plusDays(30);
            case "trimestrale" -> LocalDate.now().plusDays(90);
            case "annuale", "10ingressi" -> LocalDate.now().plusDays(365);

            default -> null;
        };
    }

    public static int getPrezzoAbbonamento(String tipo) {
        return switch (tipo.toLowerCase()) {
            case MENSILE -> 5000;
            case "trimestrale" -> 11000;
            case "annuale" -> 33000;
            case "10ingressi" -> 2500;
            default -> 0;
        };
    }

    public static void apriNelBrowser(String url) throws Exception {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            Desktop.getDesktop().browse(new URI(url));
        } else {
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("linux")) {
                Runtime.getRuntime().exec(new String[]{"xdg-open", url});
            } else if (os.contains("mac")) {
                Runtime.getRuntime().exec(new String[]{"open", url});
            } else if (os.contains("win")) {
                Runtime.getRuntime().exec(new String[]{"rundll32", "url.dll,FileProtocolHandler", url});
            } else {
                throw new UnsupportedOperationException("Apertura browser non supportata su questo sistema operativo.");
            }
        }
    }
}
