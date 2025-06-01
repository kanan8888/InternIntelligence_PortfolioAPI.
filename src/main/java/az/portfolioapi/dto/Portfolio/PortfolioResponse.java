package az.portfolioapi.dto.Portfolio;

import az.portfolioapi.dto.Education.EducationResponse;
import az.portfolioapi.dto.Experience.ExperienceResponse;
import az.portfolioapi.dto.Project.ProjectResponse;
import az.portfolioapi.dto.Skill.SkillResponse;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PortfolioResponse {

    Long id;
    Long userId;
    String title;
    String description;
    List<EducationResponse> educations;
    List<ExperienceResponse> experiences;
    List<ProjectResponse> projects;
    List<SkillResponse> skills;
}
