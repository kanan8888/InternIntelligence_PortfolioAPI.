package az.portfolioapi.entity;

import az.portfolioapi.entity.common.BaseAuditableEntity;
import az.portfolioapi.entity.enums.DegreeLevel;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "educations")
@Getter
@Setter
@ToString(callSuper = true, exclude = "portfolio") /**/
@NoArgsConstructor
@AllArgsConstructor
@Builder /**/
/**/@EqualsAndHashCode(of = "id") @EntityListeners(AuditingEntityListener.class)
public class EducationEntity /*extends BaseAuditableEntity*/ {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)   private Long id;
    @CreatedDate @Column(updatable = false, nullable = false) private Instant createdAt;
    @LastModifiedDate                                         private Instant updatedAt;

    @Column(nullable = false, length = 200)
    private String institution;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DegreeLevel degree;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false)
    private PortfolioEntity portfolio;
}
