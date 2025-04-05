package az.portfolioapi.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExperienceRequestDTO {

    private String company;
    private String position;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private Long portfolioId;
}
