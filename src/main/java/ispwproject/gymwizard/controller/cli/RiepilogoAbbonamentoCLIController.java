package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.controller.app.AbbonamentoController;
import ispwproject.gymwizard.controller.app.PagamentoController;
import ispwproject.gymwizard.controller.demo.DemoFactory;
import ispwproject.gymwizard.model.Abbonamento;
import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.view.RiepilogoAbbonamentoView;

public class RiepilogoAbbonamentoCLIController {

    private final RiepilogoAbbonamentoView view = new RiepilogoAbbonamentoView();

    // Controller dinamico (DEMO o DBMS)
    private final AbbonamentoController controller = DemoFactory.getAbbonamentoController();

    public CLIState start() {
        Abbonamento abbonamento = (Abbonamento) SessionManager.getInstance().getAttributo("abbonamentoInAttesa");

        if (abbonamento == null) {
            view.mostraMessaggio("⚠️ Nessun abbonamento da riepilogare.");
            view.attesaInvio();
            return CLIState.STATO_ABBONAMENTO;
        }

        int scelta = view.mostraRiepilogo(abbonamento);

        if (scelta == 0) {
            view.mostraMessaggio("🔙 Ordine annullato.");
            view.attesaInvio();
            return CLIState.STATO_ABBONAMENTO;
        }

        String tipo = abbonamento.getTipo();
        int prezzo = controller.getPrezzoAbbonamento(tipo);  // ✅ non statico

        try {
            PagamentoController paypal = new PagamentoController();
            String url = paypal.creaOrdine(prezzo);

            controller.apriNelBrowser(url);  // ✅ non statico
            controller.aggiungiAbbonamento(tipo, "Pagamento mock");  // ✅ non statico

            view.mostraMessaggio("🌐 Verrai reindirizzato al pagamento PayPal...");
            view.mostraMessaggio("\n✅ Dimostrazione: abbonamento attivato correttamente.\n");

        } catch (IllegalStateException e) {
            view.mostraMessaggio("⚠️ Hai già un abbonamento attivo.");
        } catch (Exception e) {
            view.mostraMessaggio("❌ Errore nel reindirizzamento: " + e.getMessage());
        }

        view.attesaInvio();
        return CLIState.STATO_ABBONAMENTO;
    }
}
