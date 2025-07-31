package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.controller.app.AbbonamentoController;
import ispwproject.gymwizard.model.Abbonamento;
import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.view.RinnovaAbbonamentoView;

public class RinnovaAbbonamentoCLIController {

    private final RinnovaAbbonamentoView view = new RinnovaAbbonamentoView();
    private final AbbonamentoController controller = new AbbonamentoController();

    public CLIState start() {
        Utente utente = (Utente) SessionManager.getInstance().getAttributo("utente");

        if (utente == null) {
            view.mostraMessaggio("❌ Utente non loggato.");
            view.attesaInvio();
            return CLIState.LOGIN;
        }

        int scelta = view.scegliTipoAbbonamento();

        if (scelta == 0) {
            return CLIState.STATO_ABBONAMENTO;
        }

        String tipo = switch (scelta) {
            case 1 -> "10ingressi";
            case 2 -> "mensile";
            case 3 -> "trimestrale";
            case 4 -> "annuale";
            default -> null;
        };

        if (tipo == null) {
            view.mostraMessaggio("❌ Tipo abbonamento non valido.");
            view.attesaInvio();
            return CLIState.STATO_ABBONAMENTO;
        }

        Abbonamento abbonamento = new Abbonamento();
        abbonamento.setIdUtente(utente.getId());
        abbonamento.setTipo(tipo);
        abbonamento.setDataInizio(controller.getDataEmissione());
        abbonamento.setDataFine(controller.getDataScadenza(tipo));
        abbonamento.setStato("in_attesa_pagamento");

        // Salvo l'abbonamento temporaneo in sessione
        SessionManager.getInstance().setAttributo("abbonamentoInAttesa", abbonamento);

        return CLIState.RIEPILOGO_ABBONAMENTO;
    }
}
