package logic.cards;

import logic.constantFolder.ObjectiveEnum;

public class ObjectiveCard {

    private final ObjectiveEnum type;
    public ObjectiveCard (ObjectiveEnum type) {
        this.type = type;
    }

    public ObjectiveEnum getType () {
        return type;
    }
}
