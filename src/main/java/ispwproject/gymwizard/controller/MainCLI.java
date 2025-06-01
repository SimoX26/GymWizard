package ispwproject.gymwizard.controller;

import ispwproject.gymwizard.controller.cli.LoginCLIController;

public class MainCLI {
    public static void main(String[] args) {
        System.out.println("Benvenuto in GymWizard (CLI Mode)");
        new LoginCLIController().start();
    }
}