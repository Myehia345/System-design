package com.filemanagement.enums;

public enum PermissionGroup {
    ADMIN (0, "Admin"),
    USER (1, "User");

    private final Integer id;
    private final String name;

    private PermissionGroup(Integer id, String name) {
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
