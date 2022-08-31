package io.wollinger.ezsqlite;

import lombok.Getter;

@Getter
public class DBCondition<TYPE> {
    enum CONDITION_TYPE {EQUALS}

    private final String key;
    private final TYPE value;
    private final CONDITION_TYPE conditionType;

    public DBCondition(String key, TYPE value, CONDITION_TYPE conditionType) {
        this.key = key;
        this.value = value;
        this.conditionType = conditionType;
    }

    public String asString() {
        switch (conditionType) {
            case EQUALS -> {
                return "=";
            }
        }
        return "";
    }
}
