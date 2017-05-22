package com.fabricetas.domain.dto;

import com.fabricetas.domain.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

/**
 * Entity that models a color for view layer
 * Created on 21/04/2017.
 * @author belman
 */
@ToString
@EqualsAndHashCode(exclude={"colorId"})
public class ColorDto {

    @Getter @Setter
    private Integer colorId;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private Collection<PersistentFile> persistentFile;

    @Getter @Setter
    private Collection<Stamp> stamp;

    @Getter @Setter
    private Collection<Text> text;

    @Getter @Setter
    private Collection<Tshirt> tshirt;

    @Getter @Setter
    private Collection<User> user;

    public ColorDto(Integer colorId, String name) {
        this.colorId = colorId; this.name = name;
    }

}
