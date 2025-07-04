package ispwproject.gymwizard.view;

import java.util.Scanner;

public class LoginView {

    private final Scanner scanner = new Scanner(System.in);

    public String[] chiediCredenziali() {
        System.out.println("\n===== LOGIN GYM WIZARD CLI =====\n");

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        return new String[]{email, password};
    }

    public void mostraErroreInput() {
        System.out.println("❌ Inserisci sia email che password.");
    }

    public void mostraErroreDB(String messaggio) {
        System.out.println("❌ Errore di accesso al database: " + messaggio);
    }

    public void mostraSuccesso() {
        System.out.println("✅ Login effettuato!");
    }
}
