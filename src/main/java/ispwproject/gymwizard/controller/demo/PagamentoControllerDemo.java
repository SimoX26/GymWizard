package ispwproject.gymwizard.controller.DEMO;

import ispwproject.gymwizard.controller.app.PagamentoController;

public class PagamentoControllerDemo extends PagamentoController {

    public PagamentoControllerDemo() {
        // Nessuna inizializzazione necessaria
        System.out.println("[DEMO] PagamentoControllerDemo inizializzato (nessuna connessione PayPal).");
    }

    @Override
    public String creaOrdine(int prezzoCentesimi) {
        double prezzoEuro = prezzoCentesimi / 100.0;
        System.out.println("[DEMO] Ordine creato per importo: €" + prezzoEuro);

        // Simula un URL PayPal mock
        return "https://demo.paypal.com/checkoutnow?ordine=demo123&amount=" + prezzoEuro;
    }
}
