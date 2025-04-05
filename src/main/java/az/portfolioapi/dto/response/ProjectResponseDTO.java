package az.portfolioapi.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String url;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long portfolioId;
}
