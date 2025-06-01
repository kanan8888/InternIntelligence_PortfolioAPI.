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

    @NotBlank(message = "{validation.education.institution.notblank}")
    @Size(max = 200, message = "{validation.education.institution.size}")
    String institution;

    @NotNull(message = "{validation.education.degree.notnull}")
    DegreeLevel degree;

    @Size(max = 2000, message = "{validation.education.description.size}")
    String description;

    @NotNull(message = "{validation.education.startDate.notnull}")
    @PastOrPresent(message = "{validation.education.startDate.pastorpresent}")
    LocalDate startDate;

    LocalDate endDate;

    @NotNull(message = "{validation.education.portfolioId.notnull}")
    Long portfolioId;
}

