package ispwproject.gymwizard.controller.cli;


import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.view.VisualizzaListaSchedeView;

import java.util.List;

public class SelezionaSchedaClienteCLIController {

    private final VisualizzaListaSchedeView view = new VisualizzaListaSchedeView();

    public CLIState start() {
        String cliente = (String) SessionManager.getInstance().getAttributo("clienteSelezionato");

        List<String> schede = getSchedePerCliente(cliente);
        if (schede.isEmpty()) {
            view.mostraMessaggio("⚠️ Nessuna scheda trovata per " + cliente);
            view.attesaInvio();
            return CLIState.LISTA_CLIENTI;
        }

        int scelta = view.scegliScheda(schede, true); // true → mostra crea nuova scheda
        if (scelta == -1) {
            return CLIState.LISTA_CLIENTI;
        } else if (scelta == -2) {
            return CLIState.CREA_SCHEDA_CLIENTE;
        }

        SessionManager.getInstance().setAttributo("schedaSelezionata", schede.get(scelta));

        return CLIState.VISUALIZZA_ESERCIZI_SCHEDA;
    }

    private List<String> getSchedePerCliente(String cliente) {
        return switch (cliente) {
            case "Mario Rossi" -> List.of("Scheda Forza", "Scheda Volume");
            case "Giulia Verdi" -> List.of("Scheda Glutei", "Scheda Cardio");
            case "Luca Bianchi" -> List.of("Scheda Recupero", "Scheda Mobilità");
            default -> List.of();
        };
    }
}
