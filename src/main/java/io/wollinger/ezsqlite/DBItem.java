package io.wollinger.ezsqlite;

import lombok.Getter;

@Getter
public class DBItem<TYPE> {
    private final String key;
    private final TYPE value;
    private final boolean doUpdate;

    public DBItem(String key, TYPE value, boolean doUpdate) {
        this.key = key;
        this.value = value;
        this.doUpdate = doUpdate;
    }
}
