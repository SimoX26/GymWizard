package ispwproject.gymwizard.controller.demo;

import ispwproject.gymwizard.controller.app.ClientiController;
import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.bean.ClienteBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientiControllerDemo extends ClientiController {

    @Override
    public void getClienti(ClienteBean bean){
        List<Utente> clientiDemo = new ArrayList<>();

        clientiDemo.add(new Utente(1, "mario.rossi", "mario.rossi@email.com"));
        clientiDemo.add(new Utente(2, "luisa.verdi", "luisa.verdi@email.com"));
        clientiDemo.add(new Utente(3, "giovanni.bianchi", "giovanni.bianchi@email.com"));

        bean.setListaClienti(clientiDemo);
    }
}
