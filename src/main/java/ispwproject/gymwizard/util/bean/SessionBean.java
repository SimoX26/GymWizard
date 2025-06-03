package ispwproject.gymwizard.util.bean;

import ispwproject.gymwizard.model.Role;

public class SessionBean {
    private final String email;
    private final Role role;
    private int idUtente;

    public SessionBean(String email, Role role) {
        this.email = email;
        this.role = role;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }
}
