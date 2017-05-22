package com.fabricetas.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fabricetas.domain.dto.RoleDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity that models the role of a user
 * Created on 12/04/2017.
 * @author belman
 */
@Entity
@ToString
@Table(name="ROLE")
@EqualsAndHashCode(exclude = {"roleId"})
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter @Setter
	@Id @GeneratedValue
    @Column(name = "ROLE_ID")
    private Integer roleId;

	@Getter @Setter
    @Column(name = "NAME")
    private String name;

	@JsonIgnore
	@Getter @Setter
	@ManyToMany(mappedBy="role")
    private Collection<User> user;

	@JsonIgnore
	public RoleDto getDto(){
		return new RoleDto(name, roleId);
	}
    
}