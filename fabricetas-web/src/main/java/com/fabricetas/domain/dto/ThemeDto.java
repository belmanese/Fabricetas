package com.fabricetas.domain.dto;

import com.fabricetas.domain.PersistentFile;
import com.fabricetas.domain.Stamp;
import com.fabricetas.domain.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

/**
 * Entity that models a theme for view layer
 * Created on 20/04/2017.
 * @author belman
 */
@ToString
@EqualsAndHashCode(exclude={"themeId"})
public class ThemeDto {

    @Getter @Setter
    private Integer themeId;

    @Getter @Setter
    private String description;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String path;

    @Getter @Setter
    private Collection<PersistentFile> persistentFile;

    @Getter @Setter
    private Collection<Stamp> stamp;

    @Getter @Setter
    private Collection<User> user;

    public ThemeDto(String description, String name, String path, Integer themeId) {
        this.description = description; this.name = name;
        this.path = path; this.themeId = themeId;
    }
}
