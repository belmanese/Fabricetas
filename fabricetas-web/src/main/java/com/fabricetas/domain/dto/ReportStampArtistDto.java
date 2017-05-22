package com.fabricetas.domain.dto;

import lombok.*;

/**
 * Entity that models a report by stamp for view layer
 * Created on 04/04/2017.
 * @author belman
 */
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class ReportStampArtistDto implements ReportDto {

    @Getter @Setter
    private String artista;

    @Getter @Setter
    private String nombre;

    @Getter @Setter
    private String tema;

    @Getter @Setter
    private String valor;

    @Getter @Setter
    private String cantidad;

    @Getter @Setter
    private String total;

}
