package ispwproject.gymwizard.controller.cli;

public enum CLIState {
    LOGIN,

// Stati del flusso cliente
    DASHBOARD_CLIENTE,

    VISUALIZZA_ESERCIZI_SCHEDA, // Visualizzazione degli esercizi della scheda scelta
    SELEZIONA_SCHEDA, // Scelta della scheda assegnata
    STATO_ABBONAMENTO,
    RINNOVA_ABBONAMENTO,
    RIEPILOGO_ABBONAMENTO,

// Stati del flusso trainer
    DASHBOARD_TRAINER,

    LISTA_CLIENTI,
    SELEZIONA_SCHEDA_CLIENTE,
    CREA_SCHEDA_CLIENTE,
    AGGIUNGI_ESERCIZIO,
    SVUOTA_SCHEDA,

// Stati del flusso admin
    DASHBOARD_ADMIN,

    LISTINO_ATTIVITA,
    VISUALIZZA_ATTIVITA,
    CREA_ATTIVITA,


    CHAT,
    CODICE_ACCESSO,


    USCITA
}
