package com.filemanagement.enums;

public enum ItemType {
    SPACE (0, "Space"),
    FOLDER (1, "Folder"),
    FILE (2, "File");

    private final Integer id;
    private final String name;

    private ItemType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
