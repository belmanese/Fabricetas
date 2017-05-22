package com.fabricetas.domain.dto.view;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity that models a Stamp for invoice view
 * Created on 06/05/2017.
 * @author belman
 */
@ToString
@EqualsAndHashCode
public class TshirtForInvoiceDto {

    @Getter @Setter
    private String camisetaId;

    @Getter @Setter
    private String nombre;

    @Getter @Setter
    private String descripcion;

    @Getter @Setter
    private String urlImagen;

    @Getter @Setter
    private String material;

    @Getter @Setter
    private String color;

    @Getter @Setter
    private String valor;

    @Getter @Setter
    private String urlCamiseta;
    
}
