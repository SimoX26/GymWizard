package ispwproject.gymwizard.model;

public enum Role {
    CLIENTE(1),
    TRAINER(2),
    ADMIN(3);

    private final int id;

    Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Role fromInt(int id) {
        for (Role role : values()) {
            if (role.getId() == id) {
                return role;
            }
        }
        throw new IllegalArgumentException("ID ruolo non valido: " + id);
    }
}
