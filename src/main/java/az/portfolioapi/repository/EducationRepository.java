package az.portfolioapi.repository;

import az.portfolioapi.entity.EducationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EducationRepository extends JpaRepository<EducationEntity, Long> {

    Optional<EducationEntity> findByIdAndPortfolio_User_Id(Long educationId, Long userId);
    Page<EducationEntity> findByPortfolio_Id(Long portfolioId, Pageable pageable);
    Page<EducationEntity> findByPortfolio_User_Id(Long userId, Pageable pageable);
    Page<EducationEntity> findByPortfolio_IdAndPortfolio_User_Id(Long portfolioId, Long userId, Pageable pageable);
}
