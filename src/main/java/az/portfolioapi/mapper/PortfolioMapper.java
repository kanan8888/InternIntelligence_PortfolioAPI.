package az.portfolioapi.mapper;

import az.portfolioapi.dto.Portfolio.PortfolioRequest;
import az.portfolioapi.dto.Portfolio.PortfolioResponse;
import az.portfolioapi.entity.PortfolioEntity;
import az.portfolioapi.entity.UserEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        uses = {EducationMapper.class, ExperienceMapper.class, ProjectMapper.class, SkillMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface PortfolioMapper {

    @Mapping(target = "userId", source = "user.id")
    PortfolioResponse toResponse(PortfolioEntity portfolio);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "educations", ignore = true)
    @Mapping(target = "experiences", ignore = true)
    @Mapping(target = "projects", ignore = true)
    @Mapping(target = "skills", ignore = true)
    PortfolioEntity toEntity(PortfolioRequest request, UserEntity user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "educations", ignore = true)
    @Mapping(target = "experiences", ignore = true)
    @Mapping(target = "projects", ignore = true)
    @Mapping(target = "skills", ignore = true)
    void updateEntity(PortfolioRequest request, @MappingTarget PortfolioEntity portfolio);
}
