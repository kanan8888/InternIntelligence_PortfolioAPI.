package az.portfolioapi.exception.skill;

import az.portfolioapi.exception.BaseException;
import org.springframework.http.HttpStatus;

public class SkillNotFoundException extends BaseException {

    public SkillNotFoundException() {
        super("Skill not found", HttpStatus.NOT_FOUND);
    }

    public SkillNotFoundException(Long skillId) {
        super("Skill not found with id: " + skillId, HttpStatus.NOT_FOUND);
    }
}
