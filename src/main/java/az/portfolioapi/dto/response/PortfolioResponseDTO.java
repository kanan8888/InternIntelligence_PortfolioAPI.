package az.portfolioapi.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class PortfolioResponseDTO {

    private Long id;
    private String title;
    private String description;
    private Long userId;
    private List<EducationResponseDTO> educations;
    private List<ExperienceResponseDTO> experiences;
    private List<ProjectResponseDTO> projects;
    private List<SkillResponseDTO> skills;
}
