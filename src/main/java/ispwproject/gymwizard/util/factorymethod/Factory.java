package ispwproject.gymwizard.util.factorymethod;

import ispwproject.gymwizard.util.factorymethod.concreteproduct.*;

public class Factory {
    public SchedaBase createSchedaBase(String tipo) throws IllegalArgumentException{
        return switch (tipo.toLowerCase()) {
            case "bulk" -> new ConcreteProductSchedaBulk();
            case "cut" -> new ConcreteProductSchedaCut();
            default -> throw new IllegalArgumentException("Tipo scheda non valido: " + tipo);
        };
    }
}
