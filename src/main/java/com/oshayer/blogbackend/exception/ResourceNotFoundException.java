package com.oshayer.blogbackend.exception;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class ResourceNotFoundException extends RuntimeException {
    String resouceName;
    String FieldName;
    long fieldValue;
    public ResourceNotFoundException(String resouceName, String fieldName, long fieldValue) {
        super(String.format("%s not found with %s : %d", resouceName, fieldName, fieldValue));

        this.resouceName = resouceName;
        this.FieldName = fieldName;
        this.fieldValue = fieldValue;
    }


}
