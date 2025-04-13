package az.portfolioapi.dto.response;

import az.portfolioapi.entity.enums.SkillLevel;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SkillResponse {

    Long id;
    String name;
    SkillLevel skillLevel;
    Long portfolioId;
}
