package az.portfolioapi.dto.Portfolio;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PortfolioRequest {

    String title;
    String description;
    Long userId;
}
