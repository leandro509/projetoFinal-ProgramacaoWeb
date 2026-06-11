package com.leandro.projeto_petshop_web.exception;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ErrorResponse {
    private String message;
    private Integer status;
}
