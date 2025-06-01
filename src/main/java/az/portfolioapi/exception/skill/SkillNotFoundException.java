package az.portfolioapi.exception.skill;

import az.portfolioapi.exception.BaseException;
import org.springframework.http.HttpStatus;

public class SkillNotFoundException extends BaseException {

    public SkillNotFoundException(Long skillId) {
        super(
                "error.skill.not.found",
                String.format("Skill not found with id: %d", skillId),
                HttpStatus.NOT_FOUND
        );
    }

    public SkillNotFoundException(Long skillId, Long userId) {
        super(
                "error.skill.not.found",
                String.format("Skill not found with id: %d for user with id: %d", skillId, userId),
                HttpStatus.NOT_FOUND
        );
    }
}
