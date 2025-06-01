package az.portfolioapi.entity;

import az.portfolioapi.entity.common.BaseAuditableEntity;
import az.portfolioapi.entity.enums.SkillLevel;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Table(name = "skills")
@Getter
@Setter
@ToString(callSuper = true, exclude = "portfolio") /**/
@NoArgsConstructor
@AllArgsConstructor
@Builder /**/
/**/@EqualsAndHashCode(of = "id") @EntityListeners(AuditingEntityListener.class)
public class SkillEntity /*extends BaseAuditableEntity*/ {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)   private Long id;
    @CreatedDate @Column(updatable = false, nullable = false) private Instant createdAt;
    @LastModifiedDate                                         private Instant updatedAt;

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SkillLevel skillLevel;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false)
    private PortfolioEntity portfolio;
}
