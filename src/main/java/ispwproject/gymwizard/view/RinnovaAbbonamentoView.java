package ispwproject.gymwizard.view;

import java.util.Scanner;

public class RinnovaAbbonamentoView {

    private final Scanner scanner = new Scanner(System.in);

    public int scegliTipoAbbonamento() {
        System.out.println("\n📆 Seleziona tipo di abbonamento:");
        System.out.println("1. 10 ingressi");
        System.out.println("2. Mensile");
        System.out.println("3. Trimestrale");
        System.out.println("4. Annuale");
        System.out.println("0. 🔙 Annulla");
        System.out.print("👉 Scelta: ");

        try {
            int scelta = Integer.parseInt(scanner.nextLine());
            if (scelta >= 0 && scelta <= 4) {
                return scelta;
            }
        } catch (NumberFormatException ignored) {}

        System.out.println("❌ Scelta non valida.");
        return scegliTipoAbbonamento(); // ripeti menu
    }

    public void mostraMessaggio(String msg) {
        System.out.println(msg);
    }

    public void attesaInvio() {
        System.out.print("👉 Premi invio per continuare...");
        scanner.nextLine();
    }
}




