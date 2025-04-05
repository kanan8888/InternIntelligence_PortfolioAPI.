package az.portfolioapi.dto.request;

import lombok.Data;

@Data
public class PortfolioRequestDTO {

    private String title;
    private String description;
    private Long userId;
}
