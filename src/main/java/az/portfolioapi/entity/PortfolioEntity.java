package az.portfolioapi.entity;

import az.portfolioapi.entity.common.BaseAuditableEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "portfolios")
@Getter
@Setter
@ToString(callSuper = true, exclude = {"user","educations","experiences","projects","skills"}) /**/
@NoArgsConstructor
@AllArgsConstructor
@Builder /**/
/**/@EqualsAndHashCode(of = "id") @EntityListeners(AuditingEntityListener.class)
public class PortfolioEntity /*extends BaseAuditableEntity*/ {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)   private Long id;
    @CreatedDate @Column(updatable = false, nullable = false) private Instant createdAt;
    @LastModifiedDate                                         private Instant updatedAt;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(length = 2000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<EducationEntity> educations;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ExperienceEntity> experiences;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProjectEntity> projects;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SkillEntity> skills;
}
