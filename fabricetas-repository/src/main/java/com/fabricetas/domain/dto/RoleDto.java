package com.fabricetas.domain.dto;

import com.fabricetas.domain.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

/**
 * Entity that models a address for view layer
 * Created on 19/04/2017.
 * @author belman
 */
@ToString
@EqualsAndHashCode(exclude={"roleId"})
public class RoleDto {

    @Getter @Setter
    private Integer roleId;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private Collection<User> user;

    public RoleDto(String name, Integer roleId) {
        this.name = name; this.roleId = roleId;
    }
}
