package az.portfolioapi.repository;

import az.portfolioapi.entity.ExperienceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExperienceRepository extends JpaRepository<ExperienceEntity, Long> {

    Optional<ExperienceEntity> findByIdAndPortfolio_User_Id(Long experienceId, Long userId);
    Page<ExperienceEntity> findByPortfolio_Id(Long portfolioId, Pageable pageable);
    Page<ExperienceEntity> findByPortfolio_User_Id(Long userId, Pageable pageable);
    Page<ExperienceEntity> findByPortfolio_IdAndPortfolio_User_Id(Long portfolioId, Long userId, Pageable pageable);
}
