package az.portfolioapi.dto.Experience;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExperienceRequest {

    String company;
    String position;
    LocalDate startDate;
    LocalDate endDate;
    String description;
    Long portfolioId;
}
