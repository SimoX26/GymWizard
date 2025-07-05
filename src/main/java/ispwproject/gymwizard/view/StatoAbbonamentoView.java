package ispwproject.gymwizard.view;

import ispwproject.gymwizard.model.Abbonamento;

import java.util.Scanner;

public class StatoAbbonamentoView {

    private final Scanner scanner = new Scanner(System.in);

    public int mostraAbbonamento(Abbonamento abbonamento) {
        System.out.println("\n📄 STATO ABBONAMENTO:");

        if (abbonamento == null) {
            System.out.println("⚠️ Nessun abbonamento attivo trovato.");
        } else {
            System.out.println("Tipo: " + abbonamento.getTipo());
            System.out.println("Data inizio: " + abbonamento.getDataInizio());
            System.out.println("Data fine: " + abbonamento.getDataFine());
            System.out.println("Stato: " + abbonamento.getStato());
            System.out.println("Riferimento pagamento: " +
                    (abbonamento.getRiferimentoPagamento() != null ? abbonamento.getRiferimentoPagamento() : "Nessuno"));
        }

        // Mostra comunque le opzioni
        System.out.println("\n1. 🔄 Rinnova abbonamento");
        System.out.println("0. 🔙 Torna alla Dashboard");
        System.out.print("👉 Scelta: ");

        try {
            int scelta = Integer.parseInt(scanner.nextLine());
            if (scelta == 1 || scelta == 0) {
                return scelta;
            }
        } catch (NumberFormatException ignored) {}

        System.out.println("❌ Scelta non valida.");
        return mostraAbbonamento(abbonamento); // ripeti la view
    }

    public void mostraMessaggio(String msg) {
        System.out.println(msg);
    }

    public void attesaInvio() {
        System.out.print("👉 Premi invio per continuare...");
        scanner.nextLine();
    }
}
