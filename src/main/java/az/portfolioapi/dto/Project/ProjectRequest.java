package az.portfolioapi.dto.Project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectRequest {

    @NotBlank(message = "{validation.project.title.notblank}")
    @Size(max = 200, message = "{validation.project.title.size}")
    String title;

    @Size(max = 2000, message = "{validation.project.description.size}")
    String description;

    @Size(max = 2000, message = "{validation.project.url.size}")
    String url;

    @NotNull(message = "{validation.project.portfolioId.notnull}")
    Long portfolioId;
}
