package com.jhonatan.helpdesk.domain.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum Status {
    ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), ENCERRADO(2, "ENCERRADO");

    private Integer code;
    private String description;

    public static Status toEnum(Integer cod){
        if(cod == null){
            return null;
        }

        for(Status x : Status.values()){
            if(cod.equals(x.getCode())){
                return x;
            }
        }

        throw new IllegalArgumentException("Status informado não encontrado!");
    }
}
