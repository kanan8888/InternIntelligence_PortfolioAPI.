package az.portfolioapi.configuration.mapper;

import az.portfolioapi.dto.request.EducationRequest;
import az.portfolioapi.dto.response.EducationResponse;
import az.portfolioapi.entity.EducationEntity;
import az.portfolioapi.entity.PortfolioEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface EducationMapper {

    @Mapping(target = "portfolioId", source = "portfolio.id")
    EducationResponse toResponse(EducationEntity education);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "portfolio", source = "portfolio")
    @Mapping(target = "description", source = "request.description")
    EducationEntity toEntity(EducationRequest request, PortfolioEntity portfolio);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "portfolio", ignore = true)
    void updateEntity(EducationRequest request, @MappingTarget EducationEntity education);
}





//@Mapping(target = "portfolioId", source = "portfolio.id")
//List<EducationResponse> toResponseList(List<EducationEntity> educationList);