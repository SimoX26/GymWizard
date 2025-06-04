package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.model.Role;
import ispwproject.gymwizard.util.bean.SessionBean;
import ispwproject.gymwizard.util.singleton.SessionManager;

import java.util.Scanner;

public class LoginCLIController {

    public static void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Seleziona il tipo di login:");
        for (Role role : Role.values()) {
            System.out.println(role.getId() + ". " + role.name());
        }

        System.out.print("👉 Scelta: ");
        try {
            int scelta = Integer.parseInt(sc.nextLine());
            Role selectedRole = Role.fromInt(scelta);
            loginAs(selectedRole);
        } catch (Exception e) {
            System.out.println("❌ Scelta non valida.");
            start();
        }
    }

    private static void loginAs(Role role) {
        SessionBean session = new SessionBean("SESSION-" + role.name(), role);
        session.setUsername("utente_" + role.name().toLowerCase());
        SessionManager.getInstance().setSession(session);

        System.out.println("✅ Login effettuato come " + session.getUsername());

        switch (role) {
            case CLIENTE -> new DashboardClientCLIController().start();
            case TRAINER -> new DashboardTrainerCLIController().start();
            case ADMIN -> new DashboardAdminCLIController().start();
        }
    }
}

