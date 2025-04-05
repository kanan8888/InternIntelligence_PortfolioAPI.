package az.portfolioapi.dto.request;

import lombok.Data;

@Data
public class ProjectRequestDTO {

    private String title;
    private String description;
    private String url;
    private Long portfolioId;
}
