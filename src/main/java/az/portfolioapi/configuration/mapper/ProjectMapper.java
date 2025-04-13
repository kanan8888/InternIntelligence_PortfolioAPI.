package az.portfolioapi.configuration.mapper;

import az.portfolioapi.dto.request.ProjectRequest;
import az.portfolioapi.dto.response.ProjectResponse;
import az.portfolioapi.entity.PortfolioEntity;
import az.portfolioapi.entity.ProjectEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ProjectMapper {

    @Mapping(target = "portfolioId", source = "portfolio.id")
    ProjectResponse toResponse(ProjectEntity project);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "title", source = "request.title")
    @Mapping(target = "description", source = "request.description")
    @Mapping(target = "portfolio", source = "portfolio")
    ProjectEntity toEntity(ProjectRequest request, PortfolioEntity portfolio);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "portfolio", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(ProjectRequest request, @MappingTarget ProjectEntity project);
}
