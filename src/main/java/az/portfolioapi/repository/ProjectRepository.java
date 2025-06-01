package az.portfolioapi.repository;

import az.portfolioapi.entity.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    Optional<ProjectEntity> findByIdAndPortfolio_User_Id(Long projectId, Long userId);
    Page<ProjectEntity> findByPortfolio_Id(Long portfolioId, Pageable pageable);
    Page<ProjectEntity> findByPortfolio_User_Id(Long userId, Pageable pageable);
    Page<ProjectEntity> findByPortfolio_IdAndPortfolio_User_Id(Long portfolioId, Long userId, Pageable pageable);
}
