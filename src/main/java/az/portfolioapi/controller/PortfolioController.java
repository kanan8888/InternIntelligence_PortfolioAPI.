package az.portfolioapi.controller;

import az.portfolioapi.dto.Portfolio.PortfolioRequest;
import az.portfolioapi.dto.Portfolio.PortfolioResponse;
import az.portfolioapi.service.portfolio.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/portfolios")
public class PortfolioController {

    private final PortfolioService portfolioService;

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PortfolioResponse> createPortfolio(@RequestBody PortfolioRequest request) {
        return ResponseEntity.ok(portfolioService.createPortfolio(request));
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PortfolioResponse> updatePortfolio(@PathVariable Long id, @RequestBody PortfolioRequest request) {
        return ResponseEntity.ok(portfolioService.updatePortfolio(id, request));
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<PortfolioResponse> getPortfolioById(@PathVariable Long id) {
        return ResponseEntity.ok(portfolioService.getPortfolioById(id));
    }

    @GetMapping("/user/{userId}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<List<PortfolioResponse>> getPortfoliosByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(portfolioService.getPortfoliosByUserId(userId));
    }

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PortfolioResponse>> getAllPortfolios() {
        return ResponseEntity.ok(portfolioService.getAllPortfolios());
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePortfolio(@PathVariable Long id) {
        portfolioService.deletePortfolio(id);
        return ResponseEntity.noContent().build();
    }
}
