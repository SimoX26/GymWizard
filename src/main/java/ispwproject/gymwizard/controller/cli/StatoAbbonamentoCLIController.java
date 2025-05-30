package ispwproject.gymwizard.controller.cli;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class StatoAbbonamentoCLIController {

    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("\n📋 STATO DELL’ABBONAMENTO\n");

        // Simulazione dati (in un'app reale proverrebbero da DB o controller applicativo)
        String tipoAbbonamento = "Mensile";
        LocalDate dataInizio = LocalDate.of(2024, 5, 1);
        LocalDate dataFine = dataInizio.plusMonths(1);
        long giorniRimanenti = ChronoUnit.DAYS.between(LocalDate.now(), dataFine);

        System.out.println("📦 Tipo: " + tipoAbbonamento);
        System.out.println("📅 Inizio: " + dataInizio);
        System.out.println("📅 Scadenza: " + dataFine);
        System.out.println("⏳ Giorni rimanenti: " + (giorniRimanenti > 0 ? giorniRimanenti : "Scaduto"));

        System.out.println("\n0. 🔙 Torna alla Dashboard");
        System.out.print("👉 Premi invio per tornare: ");
        scanner.nextLine();
    }
}
