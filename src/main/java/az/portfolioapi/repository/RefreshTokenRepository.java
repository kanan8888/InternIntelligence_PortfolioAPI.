package az.portfolioapi.repository;

import az.portfolioapi.entity.RefreshTokenEntity;
import az.portfolioapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {

    Optional<RefreshTokenEntity> findByToken(String token);
    Optional<RefreshTokenEntity> findByUser(UserEntity user);
    int deleteByUser(UserEntity user);
}
