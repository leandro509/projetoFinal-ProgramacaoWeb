package com.leandro.projeto_petshop_web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgendamentoDto {

    @NotNull
    private LocalDateTime data;
    @NotNull
    private Long petId;
    @NotNull
    private List<Long> servicoIds;
}
