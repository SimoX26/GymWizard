package ispwproject.gymwizard.controller.demo;

import ispwproject.gymwizard.controller.app.*;
import ispwproject.gymwizard.util.Config;

public class DemoFactory {

    private DemoFactory() {
        throw new UnsupportedOperationException("Classe di utilità - non istanziabile");
    }

    public static LoginController getLoginController() {
        return Config.isDemoMode() ? new LoginControllerDemo() : new LoginController();
    }

    public static AbbonamentoController getAbbonamentoController() {
        return Config.isDemoMode() ? new AbbonamentoControllerDemo() : new AbbonamentoController();
    }

    public static PagamentoController getPagamentoController() {
        return Config.isDemoMode() ? new PagamentoControllerDemo() : new PagamentoController();
    }

    public static AttivitaController getAttivitaController() {
        return Config.isDemoMode() ? new AttivitaControllerDemo() : new AttivitaController();
    }

    public static ClientiController getClientiController() {
        return Config.isDemoMode() ? new ClientiControllerDemo() : new ClientiController();
    }

    public static SchedaController getSchedaController() {
        return Config.isDemoMode() ? new SchedaControllerDemo() : new SchedaController();
    }

}
