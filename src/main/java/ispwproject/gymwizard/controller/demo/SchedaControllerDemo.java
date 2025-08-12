package ispwproject.gymwizard.controller.demo;

import ispwproject.gymwizard.controller.app.SchedaController;
import ispwproject.gymwizard.model.EsercizioScheda;
import ispwproject.gymwizard.model.Scheda;
import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.bean.SchedaBean;
import ispwproject.gymwizard.util.exception.EsercizioDuplicatoException;
import ispwproject.gymwizard.util.singleton.SessionManager;

import java.util.*;

public class SchedaControllerDemo extends SchedaController {

    private final Map<Integer, List<Scheda>> schedePerUtente = new HashMap<>();
    private final Map<Integer, List<EsercizioScheda>> eserciziPerScheda = new HashMap<>();
    private int schedaCounter = 1;

    @Override
    public void getSchedeByIdCliente(SchedaBean bean, int idCliente) {
        if (!schedePerUtente.containsKey(idCliente)) {
            Scheda s1 = new Scheda(schedaCounter++, idCliente, "Scheda Demo 1", "bulk");
            Scheda s2 = new Scheda(schedaCounter++, idCliente, "Scheda Demo 2", "cut");

            schedePerUtente.put(idCliente, new ArrayList<>(List.of(s1, s2)));

            eserciziPerScheda.put(s1.getId(), new ArrayList<>(List.of(
                    new EsercizioScheda(s1.getId(), "Panca piana", 3, 10, "Riscaldamento"),
                    new EsercizioScheda(s1.getId(), "Squat", 4, 8, "")
            )));

            eserciziPerScheda.put(s2.getId(), new ArrayList<>(List.of(
                    new EsercizioScheda(s2.getId(), "Trazioni", 3, 6, "Assistite se serve")
            )));
        }

        bean.setListaSchede(schedePerUtente.get(idCliente));
    }

    @Override
    public void getEserciziScheda(SchedaBean bean, int idScheda) {
        bean.setEserciziScheda(eserciziPerScheda.getOrDefault(idScheda, new ArrayList<>()));
    }

    @Override
    public void aggiungiEsercizio(String nomeEsercizio, int serie, int ripetizioni, String note) throws EsercizioDuplicatoException {
        Scheda scheda = (Scheda) SessionManager.getInstance().getAttributo("scheda");
        int idScheda = scheda.getId();

        List<EsercizioScheda> esercizi = eserciziPerScheda.computeIfAbsent(idScheda, k -> new ArrayList<>());
        for (EsercizioScheda e : esercizi) {
            if (e.getNomeEsercizio().equalsIgnoreCase(nomeEsercizio)) {
                throw new EsercizioDuplicatoException(nomeEsercizio);
            }
        }
        esercizi.add(new EsercizioScheda(idScheda, nomeEsercizio, serie, ripetizioni, note));
    }

    @Override
    public void creaScheda(String nome, String tipo) {
        Utente utente = (Utente) SessionManager.getInstance().getAttributo("clienteSelezionato");
        int idUtente = utente.getId();
        Scheda nuova = new Scheda(schedaCounter++, idUtente, nome, tipo);
        schedePerUtente.computeIfAbsent(idUtente, k -> new ArrayList<>()).add(nuova);
        eserciziPerScheda.put(nuova.getId(), new ArrayList<>());
    }
}
