package az.portfolioapi.controller;

import az.portfolioapi.dto.request.PortfolioRequestDTO;
import az.portfolioapi.dto.response.PortfolioResponseDTO;
import az.portfolioapi.service.portfolio.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/portfolios")
public class PortfolioController {

    private final PortfolioService portfolioService;

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PortfolioResponseDTO> createPortfolio(@RequestBody PortfolioRequestDTO portfolioRequestDTO) {
        return ResponseEntity.ok(portfolioService.createPortfolio(portfolioRequestDTO));
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PortfolioResponseDTO> updatePortfolio(@PathVariable Long id, @RequestBody PortfolioRequestDTO portfolioRequestDTO) {
        return ResponseEntity.ok(portfolioService.updatePortfolio(id, portfolioRequestDTO));
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<PortfolioResponseDTO> getPortfolioById(@PathVariable Long id) {
        return ResponseEntity.ok(portfolioService.getPortfolioById(id));
    }

    @GetMapping("/user/{userId}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<List<PortfolioResponseDTO>> getPortfoliosByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(portfolioService.getPortfoliosByUserId(userId));
    }

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PortfolioResponseDTO>> getAllPortfolios() {
        return ResponseEntity.ok(portfolioService.getAllPortfolios());
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePortfolio(@PathVariable Long id) {
        portfolioService.deletePortfolio(id);
        return ResponseEntity.noContent().build();
    }
}
