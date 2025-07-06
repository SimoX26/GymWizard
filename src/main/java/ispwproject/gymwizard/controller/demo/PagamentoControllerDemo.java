package ispwproject.gymwizard.controller.demo;

import ispwproject.gymwizard.controller.app.PagamentoController;
import ispwproject.gymwizard.util.logger.AppLogger;

import java.util.logging.Level;

public class PagamentoControllerDemo extends PagamentoController {

    public PagamentoControllerDemo() {
        // Nessuna inizializzazione necessaria
        AppLogger.getLogger().log(
                Level.INFO,
                "[DEMO] PagamentoControllerDemo inizializzato (nessuna connessione PayPal)."
        );
    }

    @Override
    public String creaOrdine(int prezzoCentesimi) {
        double prezzoEuro = prezzoCentesimi / 100.0;
        AppLogger.getLogger().log(
                Level.INFO,
                "[DEMO] Ordine creato per importo: €{0}",
                new Object[]{prezzoEuro}
        );

        // Simula un URL PayPal mock
        return "https://demo.paypal.com/checkoutnow?ordine=demo123&amount=" + prezzoEuro;
    }
}
