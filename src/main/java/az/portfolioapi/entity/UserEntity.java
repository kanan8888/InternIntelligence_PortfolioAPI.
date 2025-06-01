package az.portfolioapi.entity;

import az.portfolioapi.entity.common.BaseAuditableEntity;
import az.portfolioapi.entity.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString(callSuper = true, exclude = {"password","portfolios"}) /**/
@NoArgsConstructor
@AllArgsConstructor
@Builder /**/
/**/@EqualsAndHashCode(of = "id") @EntityListeners(AuditingEntityListener.class)
public class UserEntity /*extends BaseAuditableEntity*/ {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)   private Long id;
    @CreatedDate @Column(updatable = false, nullable = false) private Instant createdAt;
    @LastModifiedDate                                         private Instant updatedAt;

    @Column(nullable = false, length = 100)
    String firstName;

    @Column(nullable = false, length = 100)
    String lastName;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 254)
    private String email;

    @Column(nullable = false, length = 60) /**/
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role = UserRole.MEMBER;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PortfolioEntity> portfolios;
}


/*
        public class UserEntity {

            @Column(nullable = false)
            private boolean enabled = true;
        }
*/

/*
     @CreatedBy
     @LastModifiedBy
*/
