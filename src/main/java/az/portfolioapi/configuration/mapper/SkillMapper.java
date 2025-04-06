package az.portfolioapi.configuration.mapper;

import az.portfolioapi.dto.request.SkillRequestDTO;
import az.portfolioapi.dto.response.SkillResponseDTO;
import az.portfolioapi.entity.PortfolioEntity;
import az.portfolioapi.entity.SkillEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SkillMapper {

    @Mapping(target = "portfolioId", source = "portfolio.id")
    SkillResponseDTO toResponse(SkillEntity skill);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "portfolio", source = "portfolio")
    SkillEntity toEntity(SkillRequestDTO request, PortfolioEntity portfolio);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "portfolio", ignore = true)
    void update(SkillRequestDTO request, @MappingTarget SkillEntity skill);
}
