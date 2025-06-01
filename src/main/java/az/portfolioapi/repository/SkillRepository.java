package az.portfolioapi.repository;

import az.portfolioapi.entity.SkillEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<SkillEntity, Long> {

    Optional<SkillEntity> findByIdAndPortfolio_User_Id(Long skillId, Long userId);
    Page<SkillEntity> findByPortfolio_Id(Long portfolioId, Pageable pageable);
    Page<SkillEntity> findByPortfolio_User_Id(Long userId, Pageable pageable);
    Page<SkillEntity> findByPortfolio_IdAndPortfolio_User_Id(Long portfolioId, Long userId, Pageable pageable);
}
