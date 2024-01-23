package com.jhonatan.helpdesk.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum Perfil {
    ADMIN(0, "ROLE_ADMIN"), CLIENTE(1, "ROLE_CLIENTE"), TECNICO(2, "ROLE_TECNICO");

    private Integer code;
    private String description;

    public static Perfil toEnum(Integer cod){
        if (cod == null){
            return  null;
        }
        for(Perfil x : Perfil.values()){
            if(cod.equals(x.getCode())){
                return x;
            }
        }
        throw new IllegalArgumentException("Perfil inválido!");
    }
}
