package ispwproject.gymwizard.model;

public class Utente {
    private int id;
    private String username;
    private String email;

    public Utente(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public Utente() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
