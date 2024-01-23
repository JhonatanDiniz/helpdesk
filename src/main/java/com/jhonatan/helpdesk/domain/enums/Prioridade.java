package com.jhonatan.helpdesk.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum Prioridade {
    BAIXA(0, "BAIXA"), MEDIA(1, "MÉDIA"), ALTA(2, "ALTA");

    private Integer code;
    private String description;

    public static Prioridade toEnum(Integer cod){
        if(cod == null){
            return  null;
        }

        for(Prioridade x : Prioridade.values()){
            if(cod.equals(x.getCode())){
                return x;
            }
        }
        throw  new IllegalArgumentException("Prioridade informada não encontrada!");
    }
}
