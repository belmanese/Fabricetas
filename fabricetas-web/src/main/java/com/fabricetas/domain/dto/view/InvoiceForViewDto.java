package com.fabricetas.domain.dto.view;

import java.util.Collection;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity that models a Stamp for home view
 * Created on 06/05/2017.
 * @author belman
 */
@ToString
@EqualsAndHashCode
public class InvoiceForViewDto {

    @Getter @Setter
    private String userId;

    @Getter @Setter
    private Collection<ItemInvoiceDto> itemInvoiceDto;                    
    
}
