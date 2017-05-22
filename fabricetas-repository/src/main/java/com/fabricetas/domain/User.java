package com.fabricetas.domain;

import com.fabricetas.domain.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Collection;

/**
 * Entity that models a user
 * Created on 12/04/2017.
 * @author belman
 */
@Entity
@ToString
@Table(name="USER")
@EqualsAndHashCode(exclude={"userId"})
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter @Setter
    @Id @GeneratedValue	
    @Column(name = "USER_ID")	
    private Integer userId;	
	
	@Getter @Setter
	@Column(name="EMAIL")
	private String email;
	
	@Getter @Setter
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Getter @Setter
	@Column(name = "IDENTIFICATION_NUMBER")
	private String identificationNumber;

	@Getter @Setter
    @Column(name = "IDENTIFICATION_TYPE")
    private String identificationType;

	@Getter @Setter
	@Column(name="LAST_NAME")
	private String lastName;
	
	//@Transient
	@Getter  @Setter
	@Column(name="USUARIO")
	private String name;
	
	@Getter @Setter
	@Column(name="SSO_ID")
	private String ssoId;
	
	@Getter @Setter
	@Column(name="PASSWORD")
	private String password;
	
	@Getter @Setter
	@Column(name="ESTADO")
	private String estado;
	
	@JsonIgnore
	@Getter @Setter
	@OneToMany(mappedBy="user")
	private Collection<Address> address;

	@JsonIgnore
	@ManyToMany
	@Getter @Setter
	@JoinTable(name = "R_COLOR_USER",
			joinColumns = { @JoinColumn(name = "USER_ID") },
			inverseJoinColumns = { @JoinColumn(name = "COLOR_ID") })
	private Collection<Color> color;

	@JsonIgnore
	@Getter @Setter
	@OneToMany(mappedBy="user")
	private Collection<Invoice> invoice;

	@JsonIgnore
	@Getter @Setter
	@OneToMany(mappedBy="user")
	private Collection<PersistentFile> persistentFile;

	@JsonIgnore
	@Getter @Setter
	@OneToMany(mappedBy="user")
	private Collection<Rating> rating;

	@JsonIgnore
	@ManyToMany
	@Getter @Setter
	@JoinTable(name = "R_USER_ROLE",
			joinColumns = { @JoinColumn(name = "USER_ID") },
			inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	private Collection<Role> role;

	@JsonIgnore
	@Getter @Setter
	@OneToMany(mappedBy="user")
	private Collection<Stamp> stamp;

	@JsonIgnore
	@Getter @Setter
	@OneToMany(mappedBy="user")
	private Collection<Text> text;

	@JsonIgnore
	@ManyToMany
	@Getter @Setter
	@JoinTable(name = "R_THEME_USER",
			joinColumns = { @JoinColumn(name = "USER_ID") },
			inverseJoinColumns = { @JoinColumn(name = "THEME_ID") })
	private Collection<Theme> theme;

	@JsonIgnore
	@ManyToMany
	@Getter @Setter
	@JoinTable(name = "R_TSHIRT_USER",
			joinColumns = { @JoinColumn(name = "USER_ID") },
			inverseJoinColumns = { @JoinColumn(name = "TSHIRT_ID") })
	private Collection<Tshirt> tshirt;

	@JsonIgnore
	public UserDto getDto(){
		return new UserDto(
			userId, email, firstName, identificationNumber,
			identificationType, lastName, ssoId, password);
	}
	
}