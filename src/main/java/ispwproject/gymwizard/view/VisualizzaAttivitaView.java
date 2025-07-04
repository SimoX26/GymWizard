package ispwproject.gymwizard.view;

import ispwproject.gymwizard.model.Attivita;

import java.util.Scanner;

public class VisualizzaAttivitaView {

    private final Scanner scanner = new Scanner(System.in);

    public void mostraRiepilogo(Attivita attivita) {
        System.out.println("\n📄 RIEPILOGO ATTIVITÀ:");
        System.out.println("🔹 Nome: " + attivita.getNome());
        System.out.println("📅 Data: " + attivita.getData());
        System.out.println("⏰ Orario: " + attivita.getOraInizio() + " - " + attivita.getOraFine());
        System.out.println("👥 Posti disponibili: " + attivita.getPostiDisponibili());
    }

    public String chiediConferma() {
        System.out.print("\n✅ Vuoi confermare la prenotazione? (s/n): ");
        return scanner.nextLine().trim();
    }

    public void mostraMessaggio(String msg) {
        System.out.println(msg);
    }

    public void attesaInvio() {
        System.out.print("👉 Premi invio per continuare...");
        scanner.nextLine();
    }
}
