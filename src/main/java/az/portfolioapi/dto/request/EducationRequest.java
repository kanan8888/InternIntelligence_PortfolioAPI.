package az.portfolioapi.dto.request;

import az.portfolioapi.entity.enums.DegreeLevel;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EducationRequest {

    String institution;
    DegreeLevel degree;
    String description;
    LocalDate startDate;
    LocalDate endDate;
    Long portfolioId;
}
