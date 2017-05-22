package com.fabricetas.domain.dto;

import com.fabricetas.domain.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

/**
 * Entity that models a tshirt for view layer
 * Created on 20/04/2017.
 * @author belman
 */
@ToString
@EqualsAndHashCode(exclude={"tshirtId"})
public class TshirtDto {

    @Getter @Setter
    private Integer tshirtId;

    @Getter @Setter
    private String description;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String path;

    @Getter @Setter
    private String price;

    @Getter @Setter
    private Collection<Color> color;

    @Getter @Setter
    private Collection<Invoice> invoice;

    @Getter @Setter
    private Collection<Material> material;

    @Getter @Setter
    private Collection<Stamp> stamp;

    @Getter @Setter
    private Collection<Text> text;

    @Getter @Setter
    private Collection<User> user;

    public TshirtDto(String description, String name, String path, String price, Integer tshirtId) {
        this.description = description; this.name = name; this.path = path;
        this.price = price; this.tshirtId = tshirtId;
    }

}
