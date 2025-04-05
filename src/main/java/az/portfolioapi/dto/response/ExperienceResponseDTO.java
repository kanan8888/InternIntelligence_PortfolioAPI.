package az.portfolioapi.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExperienceResponseDTO {

    private Long id;
    private String company;
    private String position;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private Long portfolioId;
}
