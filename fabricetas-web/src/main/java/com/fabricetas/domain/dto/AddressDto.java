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
	//private Integer user;
	
	@Getter @Setter
    private Integer userId;

    public AddressDto(Integer addressId, String name, User user) {
        this.addressId = addressId; 
        this.name = name;
        this.user = user;
    }

    public AddressDto(Integer addressId, String name, Integer userId) {
        this.addressId = addressId; 
        this.name = name;
        this.userId = userId;
    }
    
    public AddressDto(){
    }
}
