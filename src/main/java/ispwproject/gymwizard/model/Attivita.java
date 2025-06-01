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
    private int trainerId; // puoi sostituirlo con oggetto Trainer se vuoi

    public Attivita(int id, String nome, String descrizione, LocalDate data, LocalTime oraInizio,
                    LocalTime oraFine, int postiDisponibili, int trainerId) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.data = data;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.postiDisponibili = postiDisponibili;
        this.trainerId = trainerId;
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getDescrizione() { return descrizione; }
    public LocalDate getData() { return data; }
    public LocalTime getOraInizio() { return oraInizio; }
    public LocalTime getOraFine() { return oraFine; }
    public int getPostiDisponibili() { return postiDisponibili; }
    public int getTrainerId() { return trainerId; }

    public void setPostiDisponibili(int postiDisponibili) { this.postiDisponibili = postiDisponibili; }

    // ToString utile per debug
    @Override
    public String toString() {
        return nome + " (" + data + " " + oraInizio + "-" + oraFine + ")";
    }
}
