package az.portfolioapi.dto.request;

import az.portfolioapi.entity.enums.DegreeLevel;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EducationRequestDTO {

    private String institution;
    private DegreeLevel degree;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long portfolioId;
}
