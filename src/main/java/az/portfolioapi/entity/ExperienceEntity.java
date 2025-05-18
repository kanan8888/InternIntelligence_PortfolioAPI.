package az.portfolioapi.entity;

import az.portfolioapi.entity.common.BaseAuditableEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "experiences")
@Getter
@Setter
@ToString(callSuper = true, exclude = "portfolio") /**/
@NoArgsConstructor
@AllArgsConstructor
@Builder /**/
/**/@EqualsAndHashCode(of = "id") @EntityListeners(AuditingEntityListener.class)
public class ExperienceEntity /*extends BaseAuditableEntity*/ {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)   private Long id;
    @CreatedDate @Column(updatable = false, nullable = false) private Instant createdAt;
    @LastModifiedDate                                         private Instant updatedAt;

    @Column(nullable = false, length = 200)
    private String company;

    @Column(nullable = false, length = 200)
    private String position;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    @Column(length = 2000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false)
    private PortfolioEntity portfolio;
}
