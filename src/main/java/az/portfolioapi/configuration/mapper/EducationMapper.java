package az.portfolioapi.configuration.mapper;

import az.portfolioapi.dto.request.EducationRequestDTO;
import az.portfolioapi.dto.response.EducationResponseDTO;
import az.portfolioapi.entity.EducationEntity;
import az.portfolioapi.entity.PortfolioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EducationMapper {

    @Mapping(target = "portfolioId", source = "portfolio.id")
    EducationResponseDTO toResponse(EducationEntity education);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "portfolio", source = "portfolio")
    EducationEntity toEntity(EducationRequestDTO request, PortfolioEntity portfolio);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "portfolio", ignore = true)
    void updateEntity(EducationRequestDTO request, @MappingTarget EducationEntity education);

    @Mapping(target = "portfolioId", source = "portfolio.id")
    List<EducationResponseDTO> toResponseList(List<EducationEntity> educationList);
}
