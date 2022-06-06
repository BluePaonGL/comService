package fr.isep.comService.application.DTO;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Setter(value = AccessLevel.NONE)
public class ContenuDto {
    @NotNull
    @NotEmpty
    private String contenuType;

    @NotNull
    @NotEmpty
    private String contenu;
}
