package az.portfolioapi.configuration.mapper;

import az.portfolioapi.dto.request.ExperienceRequest;
import az.portfolioapi.dto.response.ExperienceResponse;
import az.portfolioapi.entity.ExperienceEntity;
import az.portfolioapi.entity.PortfolioEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ExperienceMapper {

    @Mapping(target = "portfolioId", source = "portfolio.id")
    ExperienceResponse toResponse(ExperienceEntity experience);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "portfolio", source = "portfolio")
    @Mapping(target = "description", source = "request.description")
    ExperienceEntity toEntity(ExperienceRequest request, PortfolioEntity portfolio);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "portfolio", ignore = true)
    void updateEntity(ExperienceRequest request, @MappingTarget ExperienceEntity experience);
}
