package com.fabricetas.domain.dto.view;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity that models a Stamp for home view
 * Created on 06/05/2017.
 * @author belman
 */
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class StampForHomeDto {

	@Getter @Setter
    private String stampId;

    @Getter @Setter
    private String description;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String path;

    @Getter @Setter
    private String price;
    
    @Getter @Setter
    private String totalRating;

    @Getter @Setter
    private String themeId;

    @Getter @Setter
    private String themeName;

    @Getter @Setter
    private String artistId;

    @Getter @Setter
    private String artistName;
    
}
