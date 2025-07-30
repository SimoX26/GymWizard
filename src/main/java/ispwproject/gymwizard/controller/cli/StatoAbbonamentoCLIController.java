package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.controller.app.AbbonamentoController;
import ispwproject.gymwizard.controller.demo.DemoFactory;
import ispwproject.gymwizard.model.Abbonamento;
import ispwproject.gymwizard.util.bean.AbbonamentoBean;
import ispwproject.gymwizard.view.StatoAbbonamentoView;

public class StatoAbbonamentoCLIController {

    private final StatoAbbonamentoView view = new StatoAbbonamentoView();

    private final AbbonamentoController controller = DemoFactory.getAbbonamentoController();    // Controller dinamico (DEMO o DBMS)
    private final AbbonamentoBean bean = new AbbonamentoBean();

    public CLIState start() {
        controller.getDatiAbbonamento(bean);

        Abbonamento a = bean.getAbbonamento();

        int scelta = view.mostraAbbonamento(a);
        return switch (scelta) {
            case 1 -> CLIState.RINNOVA_ABBONAMENTO;
            case 0 -> CLIState.DASHBOARD_CLIENTE;
            default -> CLIState.DASHBOARD_CLIENTE;
        };
    }
}
