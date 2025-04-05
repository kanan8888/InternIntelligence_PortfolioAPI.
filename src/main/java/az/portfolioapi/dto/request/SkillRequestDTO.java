package az.portfolioapi.dto.request;

import az.portfolioapi.entity.enums.SkillLevel;
import lombok.Data;

@Data
public class SkillRequestDTO {

    private String name;
    private SkillLevel skillLevel;
    private Long portfolioId;
}
