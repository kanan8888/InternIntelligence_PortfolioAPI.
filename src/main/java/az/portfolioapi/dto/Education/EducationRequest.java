package az.portfolioapi.dto.Education;

import az.portfolioapi.entity.enums.DegreeLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EducationRequest {

    @NotBlank(message = "{validation.education.institution.not.blank}")
    @Size(max = 200, message = "{validation.education.institution.size}")
    String institution;

    @NotNull(message = "{validation.education.degree.not.null}")
    DegreeLevel degree;

    @Size(max = 2000, message = "{validation.education.description.size}")
    String description;

    @NotNull(message = "{validation.education.start.date.not.null}")
    @PastOrPresent(message = "{validation.education.start.date.past.or.present}")
    LocalDate startDate;

    LocalDate endDate;

    @NotNull(message = "{validation.education.portfolio.id.not.null}")
    Long portfolioId;
}

