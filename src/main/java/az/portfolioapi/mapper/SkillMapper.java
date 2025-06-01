package az.portfolioapi.mapper;

import az.portfolioapi.dto.Skill.SkillRequest;
import az.portfolioapi.dto.Skill.SkillResponse;
import az.portfolioapi.entity.PortfolioEntity;
import az.portfolioapi.entity.SkillEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface SkillMapper {

    @Mapping(target = "portfolioId", source = "portfolio.id")
    SkillResponse toResponse(SkillEntity skill);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "portfolio", source = "portfolio")
    SkillEntity toEntity(SkillRequest request, PortfolioEntity portfolio);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "portfolio", ignore = true)
    void updateEntity(SkillRequest request, @MappingTarget SkillEntity skill);
}
