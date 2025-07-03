package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.view.MenuSchedaView;

public class MenuSchedaCLIController {
    private final MenuSchedaView view = new MenuSchedaView();

    public void start() {
        int scelta;
        do {
            scelta = view.mostraMenuPrincipale();
            switch (scelta) {
                case 1 -> new VisualizzaListaSchedeCLIController().selezionaScheda();
                case 2 -> new CreaSchedaCLIController().creaScheda();
                case 0 -> System.out.println("Uscita...");
                default -> System.out.println("Scelta non valida.");
            }
        } while (scelta != 0);
    }
}
