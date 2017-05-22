package com.fabricetas.domain.dto;

import com.fabricetas.domain.Stamp;
import com.fabricetas.domain.Tshirt;
import com.fabricetas.domain.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;
import java.util.Date;

/**
 * Entity that models a invoice for view layer
 * Created on 21/04/2017.
 * @author belman
 */
@ToString
@EqualsAndHashCode(exclude={"invoiceId"})
public class InvoiceDto {

    @Getter @Setter
    private Integer invoiceId;

    @Getter @Setter
    private String number;

    @Getter @Setter
    private Date date;

    @Getter @Setter
    private String itemPrice;

    @Getter @Setter
    private String totalPrice;

    @Getter @Setter
    private Collection<Stamp> stamp;

    @Getter @Setter
    private Collection<Tshirt> tshirt;

    @Getter @Setter
    private User user;

    public InvoiceDto(Date date, Integer invoiceId, String itemPrice, String number, String totalPrice) {
        this.date = date; this.invoiceId = invoiceId; this.itemPrice = itemPrice;
        this.number = number; this.totalPrice = totalPrice;
    }
}
