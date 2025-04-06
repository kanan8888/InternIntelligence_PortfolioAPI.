package az.portfolioapi.configuration.mapper;

import az.portfolioapi.dto.request.PortfolioRequestDTO;
import az.portfolioapi.dto.response.PortfolioResponseDTO;
import az.portfolioapi.entity.PortfolioEntity;
import az.portfolioapi.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        uses = {EducationMapper.class, ExperienceMapper.class, ProjectMapper.class, SkillMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PortfolioMapper {

    @Mapping(target = "userId", source = "user.id")
    PortfolioResponseDTO toResponse(PortfolioEntity portfolio);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    PortfolioEntity toEntity(PortfolioRequestDTO request, UserEntity user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateEntity(PortfolioRequestDTO request, @MappingTarget PortfolioEntity portfolio);
}
