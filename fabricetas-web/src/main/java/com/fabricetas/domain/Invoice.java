package com.fabricetas.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Collection;

import javax.persistence.*;

import com.fabricetas.domain.dto.InvoiceDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity that models the invoice of a user/stamp/tshirt
 * Created on 13/04/2017.
 * @author belman
 */
@Entity
@ToString
@Table(name="INVOICE")
@EqualsAndHashCode(exclude = {"invoiceId"})
public class Invoice implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter @Setter
    @Id @GeneratedValue	
    @Column(name = "INVOICE_ID")
    private Integer invoiceId;

	@Getter @Setter
    @Column(name = "NUMBER")
    private String number;

	@Getter @Setter
    @Column(name = "DATE")
    private Date date;

	@Getter @Setter
    @Column(name = "ITEM_PRICE")
    private String itemPrice;

	@Getter @Setter
    @Column(name = "TOTAL_PRICE")
    private String totalPrice;

    @JsonIgnore
    @Getter @Setter
    @ManyToMany(mappedBy="invoice")
    private Collection<Stamp> stamp;

    @JsonIgnore
    @Getter @Setter
    @ManyToMany(mappedBy="invoice")
    private Collection<Tshirt> tshirt;

    @ManyToOne
    @JsonIgnore
    @Getter @Setter
    @JoinColumn(name="USER_ID")
    private User user;

    @JsonIgnore
    public InvoiceDto getDto(){
        return new InvoiceDto(date, invoiceId, itemPrice, number, totalPrice);
    }

}