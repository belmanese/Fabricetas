package com.fabricetas.domain.dto;

import com.fabricetas.domain.Stamp;
import com.fabricetas.domain.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.Collection;

/**
 * Entity that models a color for view layer
 * Created on 21/04/2017.
 * @author belman
 */
@ToString
@EqualsAndHashCode(exclude={"ratingId"})
public class RatingDto {

    @Getter @Setter
    private Integer ratingId;

    @Getter @Setter
    private Date date;

    @Getter @Setter
    private String description;

    @Getter @Setter
    private Short value;

    @Getter @Setter
    private Collection<Stamp> stamp;

    @Getter @Setter
    private User user;

    public RatingDto(String description, Integer ratingId, Short value) {
        /*this.date = date; */this.description = description; this.ratingId = ratingId; this.value = value;
    }

}
