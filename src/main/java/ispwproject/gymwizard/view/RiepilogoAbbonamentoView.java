package ispwproject.gymwizard.view;

import ispwproject.gymwizard.model.Abbonamento;

import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class RiepilogoAbbonamentoView {

    private final Scanner scanner = new Scanner(System.in);

    public int mostraRiepilogo(Abbonamento abbonamento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("\n🧾 RIEPILOGO ORDINE:");
        System.out.println("📅 Tipo: " + abbonamento.getTipo());
        System.out.println("🟢 Data inizio: " + abbonamento.getDataInizio().format(formatter));
        System.out.println("🔴 Data fine: " + abbonamento.getDataFine().format(formatter));
        System.out.println("💳 Stato: " + abbonamento.getStato());

        System.out.println("\n1. ✅ Paga con PayPal");
        System.out.println("0. 🔙 Annulla ordine");
        System.out.print("👉 Scelta: ");

        try {
            int scelta = Integer.parseInt(scanner.nextLine());
            if (scelta == 0 || scelta == 1) {
                return scelta;
            }
        } catch (NumberFormatException ignored) {}

        System.out.println("❌ Scelta non valida.");
        return mostraRiepilogo(abbonamento); // ripeti menu
    }

    public void mostraMessaggio(String msg) {
        System.out.println(msg);
    }

    public void attesaInvio() {
        System.out.print("👉 Premi invio per continuare...");
        scanner.nextLine();
    }
}
