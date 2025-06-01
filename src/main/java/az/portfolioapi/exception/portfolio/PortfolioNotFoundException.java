package az.portfolioapi.exception.portfolio;

import az.portfolioapi.exception.BaseException;
import org.springframework.http.HttpStatus;

public class PortfolioNotFoundException extends BaseException {

    public PortfolioNotFoundException() {
        super("Portfolio not found", HttpStatus.NOT_FOUND);
    }

    public PortfolioNotFoundException(Long portfolioId) {
        super("Portfolio not found with id: " + portfolioId, HttpStatus.NOT_FOUND);
    }
}
