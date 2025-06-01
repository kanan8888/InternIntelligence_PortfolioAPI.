package az.portfolioapi.exception.portfolio;

import az.portfolioapi.exception.BaseException;
import org.springframework.http.HttpStatus;

public class PortfolioNotFoundException extends BaseException {

    public PortfolioNotFoundException(Long portfolioId) {
        super(
                "error.portfolio.not.found",
                String.format("Portfolio not found with id: %d", portfolioId),
                HttpStatus.NOT_FOUND
        );
    }

    public PortfolioNotFoundException(Long portfolioId, Long userId) {
        super(
                "error.portfolio.not.found",
                String.format("Portfolio not found with id: %d for user with id: %d", portfolioId, userId),
                HttpStatus.NOT_FOUND
        );
    }
}
