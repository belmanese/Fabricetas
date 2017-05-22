package com.fabricetas.domain.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class AutenticacionDto {
	
	@Getter @Setter
    private String userId;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private String identificationType;

    @Getter @Setter
    private String identificationNumber;

    @Getter @Setter
    private String tipo;
    
    @Getter @Setter
    private String tipoLogin;
    
    @Getter @Setter
    private String ssoId;
    
    @Getter @Setter
    private String firstName;
    
    @Getter @Setter
    private String lastName;
    
    @Getter @Setter
    private String email;

    @Getter @Setter
    private String mensajeRespuesta;
}
