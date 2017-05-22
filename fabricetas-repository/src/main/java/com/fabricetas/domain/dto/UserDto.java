package com.fabricetas.domain.dto;

import java.util.Collection;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fabricetas.domain.Address;
import com.fabricetas.domain.Color;
import com.fabricetas.domain.Invoice;
import com.fabricetas.domain.PersistentFile;
import com.fabricetas.domain.Rating;
import com.fabricetas.domain.Role;
import com.fabricetas.domain.Stamp;
import com.fabricetas.domain.Text;
import com.fabricetas.domain.Theme;
import com.fabricetas.domain.Tshirt;

/**
 * Entity that models a user for view layer
 * Created on 20/04/2017.
 * @author belman
 */
@ToString
@EqualsAndHashCode(exclude={"userId"})
public class UserDto {

	@Getter @Setter
    private Integer userId;

	@Getter @Setter
	private String email;

	@Getter @Setter
	private String firstName;

	@Getter @Setter
	private String identificationNumber;

	@Getter @Setter
    private String identificationType;

	@Getter @Setter
	private String lastName;

	@Setter
	private String name;

	@Setter
	private String password;
	
	@Getter @Setter
	private String ssoId;

	@Getter @Setter
	private Collection<Address> address;

	@Getter @Setter
	private Collection<Color> color;

	@Getter @Setter
	private Collection<Invoice> invoice;

	@Getter @Setter
	private Collection<PersistentFile> persistentFile;

	@Getter @Setter
	private Collection<Rating> rating;

	@Getter @Setter
	private Collection<Role> role;

	@Getter @Setter
	private Collection<Stamp> stamp;

	@Getter @Setter
	private Collection<Text> text;

	@Getter @Setter
	private Collection<Theme> theme;

	@Getter @Setter
	private Collection<Tshirt> tshirt;

	public String getName() {
		return this.firstName + " " + this.lastName;
	}

	public UserDto(Integer userId, String email, String firstName, String identificationNumber,
				   String identificationType, String lastName, String ssoId, String password) {
		this.userId = userId; this.email = email; this.firstName = firstName;
		this.identificationNumber = identificationNumber;
		this.identificationType = identificationType;
		this.lastName = lastName; this.ssoId = ssoId;
		this.password = password;
	}

}
