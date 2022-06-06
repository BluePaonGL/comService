package fr.isep.comService.application.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Setter(value = AccessLevel.NONE)
public class ArticleDto {
    @NotNull
    @NotEmpty
    private String articleTitle;
}
