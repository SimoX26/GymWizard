package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.model.Scheda;
import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.view.VisualizzaEserciziSchedaView;

import java.util.ArrayList;
import java.util.List;

public class VisualizzaEserciziSchedaCLIController {

    private final VisualizzaEserciziSchedaView view = new VisualizzaEserciziSchedaView();

    public CLIState start() {
        Scheda schedaSelezionata = (Scheda) SessionManager.getInstance().getAttributo("schedaSelezionata");
        String ruolo = (String) SessionManager.getInstance().getAttributo("homePage"); // cliente | trainer | admin

        if (schedaSelezionata == null) {
            view.mostraMessaggio("⚠️ Nessuna scheda selezionata.");
            return CLIState.DASHBOARD_CLIENTE;
        }

        List<String> esercizi = getEserciziPerScheda(schedaSelezionata.getNomeScheda());

        int scelta = view.mostraScheda(schedaSelezionata.getNomeScheda(), esercizi, ruolo.equals("trainer"));

        return switch (scelta) {
            case -1 -> ruolo.equals("trainer") ? CLIState.SELEZIONA_SCHEDA_CLIENTE : CLIState.SELEZIONA_SCHEDA;
            case -2 -> CLIState.AGGIUNGI_ESERCIZIO;
            case -3 -> {
                esercizi.clear(); // mock svuotamento
                view.mostraMessaggio("✅ Scheda svuotata.");
                view.attesaInvio();
                yield CLIState.VISUALIZZA_ESERCIZI_SCHEDA;
            }
            default -> CLIState.VISUALIZZA_ESERCIZI_SCHEDA;
        };
    }

    private List<String> getEserciziPerScheda(String scheda) {
        return new ArrayList<>(switch (scheda) {
            case "Scheda Forza" -> List.of("Squat 5x5", "Panca 5x5");
            case "Scheda Glutei" -> List.of("Hip Thrust 4x12", "Abductor Machine 3x15");
            default -> List.of("Nessun esercizio trovato.");
        });
    }
}
