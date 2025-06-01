package az.portfolioapi.entity;

import az.portfolioapi.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "refresh_tokens")
@Getter
@Setter
@ToString(callSuper = true, exclude = "user") /**/
@NoArgsConstructor
@AllArgsConstructor
@Builder /**/
/**/@EqualsAndHashCode(of = "id")
public class RefreshTokenEntity /*extends BaseEntity*/ {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @Column(nullable = false, unique = true/*, length = 255*/) /**/
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public boolean isExpired() {
        return expiryDate.isBefore(Instant.now());
    }
}
