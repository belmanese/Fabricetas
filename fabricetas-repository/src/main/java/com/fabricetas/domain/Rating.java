package com.fabricetas.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fabricetas.domain.dto.RatingDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity that models the rating of a stamp
 * Created on 13/04/2017.
 * @author belman
 */
@Entity
@ToString
@Table(name="RATING")
@EqualsAndHashCode(exclude = {"ratingId"})
public class Rating implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter @Setter
    @Id @GeneratedValue
    @Column(name = "RATING_ID")
	private Integer ratingId;
/*
	@Getter @Setter
    @Id @GeneratedValue
    @Column(name = "DATE")
	private Date date;
*/
	@Getter @Setter
	@Column(name = "DESCRIPTION")
	private String description;

	@Getter @Setter
    @Column(name = "VALUE")
	private Short value;

	@JsonIgnore
	@Getter @Setter
	@ManyToMany(mappedBy="rating")
	private Collection<Stamp> stamp;

	@ManyToOne
	@JsonIgnore
	@Getter @Setter
	@JoinColumn(name="USER_ID")
	private User user;

	@JsonIgnore
	public RatingDto getDto(){
		return new RatingDto(description, ratingId, value);
	}
}
