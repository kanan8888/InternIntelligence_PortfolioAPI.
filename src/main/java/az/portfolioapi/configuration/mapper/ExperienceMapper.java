package az.portfolioapi.configuration.mapper;

import az.portfolioapi.dto.request.ExperienceRequestDTO;
import az.portfolioapi.dto.response.ExperienceResponseDTO;
import az.portfolioapi.entity.ExperienceEntity;
import az.portfolioapi.entity.PortfolioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ExperienceMapper {

    @Mapping(target = "portfolioId", source = "portfolio.id")
    ExperienceResponseDTO toResponse(ExperienceEntity experience);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "portfolio", source = "portfolio")
    ExperienceEntity toEntity(ExperienceRequestDTO request, PortfolioEntity portfolio);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "portfolio", ignore = true)
    void updateEntity(ExperienceRequestDTO request, @MappingTarget ExperienceEntity experience);
}
