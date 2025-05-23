package ispwproject.gymwizard.util.bean;

import ispwproject.gymwizard.model.Role;

public class SessionBean {
    private final String email;
    private final Role role;
    private String username; // ✅ Aggiunto campo opzionale

    public SessionBean(String email, Role role) {
        this.email = email;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    // ✅ Aggiunto getter e setter per username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
