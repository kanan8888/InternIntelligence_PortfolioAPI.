package az.portfolioapi.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectRequest {

    String title;
    String description;
    String url;
    Long portfolioId;
}
