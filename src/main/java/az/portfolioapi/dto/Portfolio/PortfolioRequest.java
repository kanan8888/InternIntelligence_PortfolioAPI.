package az.portfolioapi.dto.Portfolio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PortfolioRequest {

    @NotBlank(message = "{validation.portfolio.title.notblank}")
    @Size(max = 200, message = "{validation.portfolio.title.size}")
    String title;

    @Size(max = 2000, message = "{validation.portfolio.description.size}")
    String description;
}
