package ispwproject.gymwizard.util.factorymethod;

import ispwproject.gymwizard.util.factorymethod.concreteproduct.*;

public class Factory {
    public SchedaBase createSchedaBase(String tipo) throws IllegalArgumentException{
        return switch (tipo.toLowerCase()) {
            case "bulk" ->  createSchedaBulk();
            case "cut" ->  createSchedaCut();
            default -> throw new IllegalArgumentException("Tipo scheda non valido: " + tipo);
        };
    }

    public SchedaBase createSchedaBulk(){
        return new ConcreteProductSchedaBulk();
    }

    public SchedaBase createSchedaCut(){
        return new ConcreteProductSchedaCut();
    }
}
