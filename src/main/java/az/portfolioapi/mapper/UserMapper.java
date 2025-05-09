package az.portfolioapi.mapper;

import az.portfolioapi.dto.auth.request.RegisterRequest;
import az.portfolioapi.dto.User.UserRequest;
import az.portfolioapi.dto.User.UserResponse;
import az.portfolioapi.dto.auth.response.RegisterResponse;
import az.portfolioapi.entity.PortfolioEntity;
import az.portfolioapi.entity.UserEntity;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        uses = {PortfolioMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UserMapper {

    @Mapping(target = "portfolioIds", source = "portfolios", qualifiedByName = "mapPortfoliosToPortfolioIds")
    UserResponse toResponse(UserEntity user);

    RegisterResponse toRegisterResponse(UserEntity user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "portfolios", ignore = true)
    UserEntity toEntity(UserRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "portfolios", ignore = true)
    @Mapping(target = "password", ignore = true)
    UserEntity toEntity(RegisterRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "portfolios", ignore = true)
    void updateEntity(UserRequest request, @MappingTarget UserEntity user);

    @Named("mapPortfoliosToPortfolioIds")
    default List<Long> mapPortfoliosToPortfolioIds(List<PortfolioEntity> portfolios) {
        if (portfolios == null) return null;

        return portfolios.stream()
                .map(PortfolioEntity::getId)
                .collect(Collectors.toList());
    }
}

