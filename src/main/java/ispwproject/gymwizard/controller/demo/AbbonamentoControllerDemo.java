package ispwproject.gymwizard.controller.demo;

import ispwproject.gymwizard.controller.app.AbbonamentoController;
import ispwproject.gymwizard.model.Abbonamento;
import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.bean.AbbonamentoBean;
import ispwproject.gymwizard.util.singleton.SessionManager;

public class AbbonamentoControllerDemo extends AbbonamentoController {

    private static Abbonamento abbonamentoMock = null;

    @Override
    public void getDatiAbbonamento(AbbonamentoBean bean) {
        if (abbonamentoMock == null) {
            bean.setAbbonamento(null);
        }
        bean.setAbbonamento(abbonamentoMock);
    }

    public void aggiungiAbbonamento(String tipo, String riferimentoPagamento) {
        if (abbonamentoMock != null) {
            throw new IllegalStateException("Esiste già un abbonamento attivo per questo utente (DEMO).");
        }

        Utente utente = (Utente) SessionManager.getInstance().getAttributo("utente");

        Abbonamento nuovoAbbonamento = new Abbonamento();
        nuovoAbbonamento.setIdUtente(utente.getId());
        nuovoAbbonamento.setTipo(tipo);
        nuovoAbbonamento.setDataInizio(getDataEmissione());
        nuovoAbbonamento.setDataFine(getDataScadenza(tipo));
        nuovoAbbonamento.setStato("attivo");
        nuovoAbbonamento.setRiferimentoPagamento(riferimentoPagamento);

        abbonamentoMock = nuovoAbbonamento;
    }

    public static void resetMock() {
        abbonamentoMock = null;
    }
}
