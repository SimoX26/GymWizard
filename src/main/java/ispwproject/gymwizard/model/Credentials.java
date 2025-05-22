package ispwproject.gymwizard.model;

public class Credentials {
    private String email;
    private String password;
    private Role role;

    public Credentials(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole(){
        return this.role;
    }
}
