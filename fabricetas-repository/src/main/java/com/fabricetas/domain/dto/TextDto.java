package com.fabricetas.domain.dto;

import com.fabricetas.domain.Color;
import com.fabricetas.domain.Stamp;
import com.fabricetas.domain.Tshirt;
import com.fabricetas.domain.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

/**
 * Entity that models a text for view layer
 * Created on 21/04/2017.
 * @author belman
 */
@ToString
@EqualsAndHashCode(exclude={"textId"})
public class TextDto {

    @Getter @Setter
    private Integer textId;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private Collection<Color> color;

    @Getter @Setter
    private Collection<Stamp> stamp;

    @Getter @Setter
    private Collection<Tshirt> tshirt;

    @Getter @Setter
    private User user;

    public TextDto(String name, Integer textId) {
        this.name = name; this.textId = textId;
    }
}
