package ispwproject.gymwizard.model;

public class Scheda {
    private int id;
    private int idCliente;
    private String nomeScheda;
    private String tipo;

    // Costruttore vuoto richiesto da Gson
    public Scheda() {}

    public Scheda(int id, int idCliente, String nome, String tipo) {
        this.id = id;
        this.idCliente = idCliente;
        this.nomeScheda = nome;
        this.tipo = tipo;
    }

    // Getter e Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setTipo(String tipo){this.tipo = tipo;}

    public String getTipo() {
        return tipo;
    }

    public String getNomeScheda() {
        return nomeScheda;
    }

    public void setNomeScheda(String nomeScheda) {
        this.nomeScheda = nomeScheda;
    }
}
