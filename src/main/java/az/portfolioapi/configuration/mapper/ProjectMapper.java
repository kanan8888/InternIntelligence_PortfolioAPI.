package az.portfolioapi.configuration.mapper;

import az.portfolioapi.dto.request.ProjectRequestDTO;
import az.portfolioapi.dto.response.ProjectResponseDTO;
import az.portfolioapi.entity.PortfolioEntity;
import az.portfolioapi.entity.ProjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProjectMapper {

    @Mapping(target = "portfolioId", source = "portfolio.id")
    ProjectResponseDTO toResponse(ProjectEntity project);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "portfolio", source = "portfolio")
    ProjectEntity toEntity(ProjectRequestDTO request, PortfolioEntity portfolio);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "portfolio", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(ProjectRequestDTO request, @MappingTarget ProjectEntity project);
}
