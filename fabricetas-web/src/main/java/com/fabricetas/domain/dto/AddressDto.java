package com.fabricetas.domain.dto;

import lombok.*;

import com.fabricetas.domain.User;

/**
 * Entity that models a address for view layer
 * Created on 19/04/2017.
 * @author belman
 */
@ToString
@EqualsAndHashCode(exclude={"addressId"})
public class AddressDto {

	@Getter @Setter
    private Integer addressId;

	@Getter @Setter
    private String name;

	@Getter @Setter
    private User user;

    public AddressDto(Integer addressId, String name) {
        this.addressId = addressId; this.name = name;
    }

}
