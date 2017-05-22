package com.fabricetas.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fabricetas.domain.dto.AddressDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity that models the addresses of a user
 * Created on 12/04/2017.
 * @author belman
 */
@Entity
@ToString@Table(name="ADDRESS")
@EqualsAndHashCode(exclude = {"addressId"})
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter @Setter
	@Id @GeneratedValue
    @Column(name = "ADDRESS_ID")
    private Integer addressId;

	@Getter @Setter
	@Column(name = "NAME")
    private String name;

	@ManyToOne
	@JsonIgnore
	@Getter @Setter
	@JoinColumn(name="USER_ID")
    private User user;
	
	@JsonIgnore
	public AddressDto getDto(){
		return new AddressDto(addressId, name);
	}

}