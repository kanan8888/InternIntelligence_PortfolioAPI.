package az.portfolioapi.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PortfolioResponse {

    Long id;
    String title;
    String description;
    Long userId;
    List<EducationResponse> educations;
    List<ExperienceResponse> experiences;
    List<ProjectResponse> projects;
    List<SkillResponse> skills;
}
