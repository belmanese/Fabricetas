package com.fabricetas.domain.dto;

import com.fabricetas.domain.Color;
import com.fabricetas.domain.Stamp;
import com.fabricetas.domain.Theme;
import com.fabricetas.domain.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

/**
 * Entity that models a file for view layer
 * Created on 21/04/2017.
 * @author belman
 */
@ToString
@EqualsAndHashCode(exclude={"fileId"})
public class PersistentFileDto {

    @Getter @Setter
    private Integer fileId;

    @Getter @Setter
    private byte[] content;

    @Getter @Setter
    private String description;

    @Getter @Setter
    private String image;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String path;

    @Getter @Setter
    private String type;

    @Getter @Setter
    private Collection<Color> color;

    @Getter @Setter
    private Collection<Stamp> stamp;

    @Getter @Setter
    private Theme theme;

    @Getter @Setter
    private User user;

    public PersistentFileDto(byte[] content, String description,
                             Integer fileId, String name, String path, String type) {
        this.content = content; this.description = description; this.fileId = fileId;
        this.name = name; this.path = path; this.type = type;
    }
}
