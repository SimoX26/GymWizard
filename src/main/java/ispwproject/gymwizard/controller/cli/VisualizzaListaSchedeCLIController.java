package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.view.VisualizzaListaSchedeView;

import java.util.ArrayList;
import java.util.List;

public class VisualizzaListaSchedeCLIController {
    private final VisualizzaListaSchedeView view = new VisualizzaListaSchedeView();

    public void selezionaScheda() {
        List<String> schede = getSchedeUtente(); // Mock o DAO
        int index = view.mostraSchedeDisponibili(schede);
        String nomeScheda = schede.get(index);

        List<String> esercizi = getEserciziScheda(nomeScheda); // Mock o DAO
        view.mostraEsercizi(esercizi);

        int scelta = view.mostraMenuEsercizi();
        if (scelta == 1) {
            new VisualizzaEserciziSchedaCLIController().aggiungiEsercizio(nomeScheda);
        }
    }

    private List<String> getSchedeUtente() {
        return List.of("Scheda Forza", "Scheda Ipertrofia");
    }

    private List<String> getEserciziScheda(String nomeScheda) {
        return switch (nomeScheda) {
            case "Scheda Forza" -> List.of("Squat 5x5", "Panca 5x5");
            case "Scheda Ipertrofia" -> List.of("Curl 3x12", "Leg Extension 4x10");
            default -> new ArrayList<>();
        };
    }
}

