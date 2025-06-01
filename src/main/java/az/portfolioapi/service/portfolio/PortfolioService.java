package az.portfolioapi.service.portfolio;

import az.portfolioapi.dto.Portfolio.PortfolioRequest;
import az.portfolioapi.dto.Portfolio.PortfolioResponse;
import az.portfolioapi.entity.enums.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PortfolioService {

    PortfolioResponse createPortfolio(Long userId, PortfolioRequest request);
    PortfolioResponse updatePortfolio(Long portfolioId, Long userId, PortfolioRequest request);
    PortfolioResponse getPortfolioById(Long portfolioId, Long userId, UserRole role);
    Page<PortfolioResponse> getPortfoliosByUserId(Long userId, Pageable pageable);
    Page<PortfolioResponse> getAllPortfolios(Pageable pageable);
    void deletePortfolio(Long portfolioId, Long userId, UserRole role);
}
