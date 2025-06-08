package az.portfolioapi.dto.Experience;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExperienceRequest {

    @NotBlank(message = "{validation.education.company.not.blank}")
    @Size(max = 200, message = "{validation.education.company.size}")
    String company;

    @NotBlank(message = "{validation.education.position.not.blank}")
    @Size(max = 200, message = "{validation.education.position.size}")
    String position;

    @NotNull(message = "{validation.education.start.date.not.null}")
    LocalDate startDate;

    LocalDate endDate;

    @Size(max = 2000, message = "{validation.education.description.size}")
    String description;

    @NotNull(message = "{validation.education.portfolio.id.not.null}")
    Long portfolioId;
}
