package ispwproject.gymwizard.util.dao;

public class DAOFactory {

    private DAOFactory() {
        // Evita l'istanziazione
    }

    public static AbbonamentoDAO getAbbonamentoDAO() {
        return new AbbonamentoDAO();
    }

    public static AttivitaDAO getAttivitaDAO(){
        return new AttivitaDAO();
    }

    public static EsercizioSchedaDAO getEsercizioSchedaDAO(){
        return new EsercizioSchedaDAO();
    }

    public static PrenotazioneDAO getPrenotazioneDAO(){
        return new PrenotazioneDAO();
    }

    public static UtenteDAO getUtenteDAO(){
        return new UtenteDAO();
    }

    public static SchedaDAO getSchedaDAO(){
        return new SchedaDAO();
    }

    public static StatisticaDAO getStatisticaDAO() {
        return new StatisticaDAO();
    }
    // Qui aggiungerai anche gli altri DAO
    // es: public static AttivitaDAO getAttivitaDAO() { return new AttivitaDAO(); }
}
