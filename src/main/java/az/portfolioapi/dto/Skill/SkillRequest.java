package az.portfolioapi.dto.Skill;

import az.portfolioapi.entity.enums.SkillLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SkillRequest {

    @NotBlank(message = "{validation.skill.name.not.blank}")
    @Size(max = 100, message = "{validation.skill.name.size}")
    String name;

    @NotNull(message = "{validation.skill.skill.level.not.null}")
    SkillLevel skillLevel;

    @NotNull(message = "{validation.skill.portfolio.id.not.null}")
    Long portfolioId;
}
