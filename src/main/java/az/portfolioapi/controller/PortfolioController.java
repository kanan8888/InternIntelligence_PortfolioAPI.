package az.portfolioapi.controller;

import az.portfolioapi.dto.Portfolio.PortfolioRequest;
import az.portfolioapi.dto.Portfolio.PortfolioResponse;
import az.portfolioapi.security.CustomUserDetails;
import az.portfolioapi.service.portfolio.PortfolioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PortfolioController {

    private final PortfolioService portfolioService;

    @PostMapping("/portfolios")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<PortfolioResponse> createPortfolio(@AuthenticationPrincipal CustomUserDetails user,
                                                             @RequestBody @Valid PortfolioRequest request) {
        return ResponseEntity.ok(
                portfolioService.createPortfolio(user.getId(), request)
        );
    }

    @PutMapping("/portfolios/{portfolioId}")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<PortfolioResponse> updatePortfolio(@PathVariable Long portfolioId,
                                                             @AuthenticationPrincipal CustomUserDetails user,
                                                             @RequestBody @Valid PortfolioRequest request) {

        return ResponseEntity.ok(
                portfolioService.updatePortfolio(portfolioId, user.getId(), request)
        );
    }

    @GetMapping("/portfolios/{portfolioId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<PortfolioResponse> getPortfolioById(@PathVariable Long portfolioId,
                                                              @AuthenticationPrincipal CustomUserDetails user) {
        return ResponseEntity.ok(
                portfolioService.getPortfolioById(portfolioId, user.getId(), user.getRole())
        );
    }

    @GetMapping("/users/{userId}/portfolios")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<PortfolioResponse>> getPortfoliosByUserId(@PathVariable Long userId,
                                                                         Pageable pageable) {
        return ResponseEntity.ok(
                portfolioService.getPortfoliosByUserId(userId, pageable)
        );
    }

    @GetMapping("/users/me/portfolios")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<Page<PortfolioResponse>> getMyPortfolios(@AuthenticationPrincipal CustomUserDetails user,
                                                                   Pageable pageable) {
        return ResponseEntity.ok(
                portfolioService.getPortfoliosByUserId(user.getId(), pageable)
        );
    }

    @GetMapping("/portfolios")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<PortfolioResponse>> getAllPortfolios(Pageable pageable) {
        return ResponseEntity.ok(
                portfolioService.getAllPortfolios(pageable)
        );
    }

    @DeleteMapping("/portfolios/{portfolioId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<Void> deletePortfolio(@PathVariable Long portfolioId,
                                                @AuthenticationPrincipal CustomUserDetails user) {

        portfolioService.deletePortfolio(portfolioId, user.getId(), user.getRole());
        return ResponseEntity.noContent().build();
    }
}
