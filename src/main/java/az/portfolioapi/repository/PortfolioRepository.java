package az.portfolioapi.repository;

import az.portfolioapi.entity.PortfolioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<PortfolioEntity, Long> {

    Optional<PortfolioEntity> findByIdAndUser_Id(Long portfolioId, Long userId);
    Page<PortfolioEntity> findByUser_Id(Long userId, Pageable pageable);
}
