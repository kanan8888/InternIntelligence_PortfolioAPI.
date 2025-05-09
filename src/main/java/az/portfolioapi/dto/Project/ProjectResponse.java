package az.portfolioapi.dto.Project;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectResponse {

    Long id;
    String title;
    String description;
    String url;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    Long portfolioId;
}
