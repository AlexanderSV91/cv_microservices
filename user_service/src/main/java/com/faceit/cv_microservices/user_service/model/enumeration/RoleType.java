package com.faceit.cv_microservices.user_service.model.enumeration;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum RoleType {

    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_EMPLOYEE("ROLE_EMPLOYEE");

    private final String nameRoleType;
    private static final Map<String, RoleType> nameToValue;

    static {
        nameToValue = Stream.of(values()).collect(Collectors.toMap(RoleType::getNameRoleType, Function.identity()));
    }

    RoleType(String nameRoleType) {
        this.nameRoleType = nameRoleType;
    }

    public String getNameRoleType() {
        return nameRoleType;
    }

    public static RoleType fromString(String name) {
        return nameToValue.get(name);
    }
}
