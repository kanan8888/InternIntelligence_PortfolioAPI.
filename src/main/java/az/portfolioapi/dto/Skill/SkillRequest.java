package az.portfolioapi.dto.Skill;

import az.portfolioapi.entity.enums.SkillLevel;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SkillRequest {

    String name;
    SkillLevel skillLevel;
    Long portfolioId;
}
