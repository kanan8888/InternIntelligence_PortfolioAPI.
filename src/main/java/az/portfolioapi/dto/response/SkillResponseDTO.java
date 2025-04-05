package az.portfolioapi.dto.response;

import az.portfolioapi.entity.enums.SkillLevel;
import lombok.Data;

@Data
public class SkillResponseDTO {

    private Long id;
    private String name;
    private SkillLevel skillLevel;
    private Long portfolioId;
}
