package ispwproject.gymwizard.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Attivita {

    private int id;
    private String nome;
    private String descrizione;
    private LocalDate data;
    private LocalTime oraInizio;
    private LocalTime oraFine;
    private int postiDisponibili;
    private String trainerName;

    public Attivita(int id, String nome, String descrizione, LocalDate data, LocalTime oraInizio,
                    LocalTime oraFine, int postiDisponibili, String trainerName) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.data = data;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.postiDisponibili = postiDisponibili;
        this.trainerName = trainerName;
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getDescrizione() { return descrizione; }
    public LocalDate getData() { return data; }
    public LocalTime getOraInizio() { return oraInizio; }
    public LocalTime getOraFine() { return oraFine; }
    public int getPostiDisponibili() { return postiDisponibili; }
    public String getTrainerName() { return trainerName; }

    // ToString utile per debug
    @Override
    public String toString() {
        return nome + " (" + data + " " + oraInizio + "-" + oraFine + ")";
    }

    public void setPostiDisponibili(int i) {
        this.postiDisponibili = i;
    }
}
