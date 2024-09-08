package com.vass.inditex.domain.exception.dto;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String message;
}
