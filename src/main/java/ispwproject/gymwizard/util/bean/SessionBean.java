package ispwproject.gymwizard.util.bean;

import ispwproject.gymwizard.model.Role;

public class SessionBean {
    private final String email;
    private final Role role;
    private String username;

    public SessionBean(String email, Role role) {
        this.email = email;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
