package ispwproject.gymwizard.controller.app;


import ispwproject.gymwizard.model.EsercizioScheda;
import ispwproject.gymwizard.model.Scheda;
import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.bean.SchedaBean;
import ispwproject.gymwizard.util.dao.DAOFactory;
import ispwproject.gymwizard.util.exception.EsercizioDuplicatoException;
import ispwproject.gymwizard.util.factorymethod.*;
import ispwproject.gymwizard.util.factorymethod.concreteproduct.SchedaBase;
import ispwproject.gymwizard.util.filesystem.EsercizioSchedaFileDAO;
import ispwproject.gymwizard.util.filesystem.SchedaFileDAO;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.util.logger.AppLogger;
import java.util.logging.Level;

public class SchedaController {

    private SchedaBase tipoScheda;

    public SchedaController(){
        //Costruttore vuoto
    }

    public SchedaController(String tipo){
        Factory factory = new Factory();
        try{
            this.tipoScheda = factory.createSchedaBase(tipo);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void getEserciziScheda(SchedaBean bean, int idScheda) {
        bean.setEserciziScheda(DAOFactory.getEsercizioSchedaDAO().getEserciziByScheda(idScheda));
    }

    public void getSchedeByIdCliente(SchedaBean bean, int idCliente) {
        Utente loggato = (Utente) SessionManager.getInstance().getAttributo("utente");

        if (loggato == null) {
            throw new SecurityException("Utente non loggato.");
        }

        bean.setListaSchede(DAOFactory.getSchedaDAO().getSchedeByUtente(idCliente));
    }


    public void creaScheda(String nome, String tipo) throws DAOException {
        Utente cliente = (Utente) SessionManager.getInstance().getAttributo("clienteSelezionato");
        if (cliente == null) {
            throw new DAOException("Cliente non selezionato.");
        }

        SchedaController s = new SchedaController(tipo);
        Scheda scheda = new Scheda();
        scheda.setIdCliente(cliente.getId());
        scheda.setNomeScheda(nome);
        scheda.setTipo(s.tipoScheda.getObiettivo());

        DAOFactory.getSchedaDAO().insertScheda(scheda);

        try {
            SchedaFileDAO.getInstance().insertScheda(scheda);
        } catch (Exception e) {
            AppLogger.getLogger().log(Level.WARNING, "⚠️ Errore salvataggio su FileSystem", e);
        }
    }

    public void aggiungiEsercizio(String nomeEsercizio, int serie, int ripetizioni, String note) throws EsercizioDuplicatoException {
        Scheda schedaCorrente = (Scheda) SessionManager.getInstance().getAttributo("scheda");
        if (schedaCorrente == null) {
            throw new IllegalStateException("Scheda non selezionata.");
        }

        int idScheda = schedaCorrente.getId();

        try {
            if (DAOFactory.getEsercizioSchedaDAO().existsEsercizio(idScheda, nomeEsercizio)) {
                throw new EsercizioDuplicatoException(nomeEsercizio);
            }
        } catch (Exception e) {
            AppLogger.getLogger().log(Level.SEVERE, "Errore durante la verifica dell'esistenza dell'esercizio nel database");
            return;
        }

        EsercizioScheda nuovo = new EsercizioScheda(idScheda, nomeEsercizio, serie, ripetizioni, note);

        try {
            DAOFactory.getEsercizioSchedaDAO().insertEsercizio(nuovo);
        } catch (Exception e) {
            AppLogger.getLogger().log(Level.SEVERE, "Errore durante l'inserimento dell'esercizio nel database");
            return;
        }

        try {
            EsercizioSchedaFileDAO.getInstance().insertEsercizio(nuovo);
        } catch (Exception e) {
            AppLogger.getLogger().log(Level.WARNING, "Errore durante il salvataggio dell'esercizio nel FileSystem", e);
        }
    }

}
