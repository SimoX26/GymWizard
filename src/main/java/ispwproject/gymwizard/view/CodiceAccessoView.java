package ispwproject.gymwizard.view;

import java.util.Scanner;

public class CodiceAccessoView {
    private final Scanner scanner = new Scanner(System.in);

    public void attesaInvio() {
        System.out.print("👉 Premi invio per continuare...");
        scanner.nextLine();
    }

    public void mostraMessaggio(String msg) {
        System.out.println(msg);
    }
}
