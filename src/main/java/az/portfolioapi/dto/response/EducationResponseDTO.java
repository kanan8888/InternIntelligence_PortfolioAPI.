package az.portfolioapi.dto.response;

import az.portfolioapi.entity.enums.DegreeLevel;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EducationResponseDTO {

    private Long id;
    private String institution;
    private DegreeLevel degree;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long portfolioId;
}
