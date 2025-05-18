package az.portfolioapi.dto.Education;

import az.portfolioapi.entity.enums.DegreeLevel;
import jakarta.validation.constraints.PastOrPresent;
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

    @PastOrPresent
    LocalDate startDate;

    LocalDate endDate;
    Long portfolioId;
}
/*
        @Past
        @PastOrPresent
        @Future
        @FutureOrPresent
*/
