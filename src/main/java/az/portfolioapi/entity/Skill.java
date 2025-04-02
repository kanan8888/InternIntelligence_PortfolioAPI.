package az.portfolioapi.entity;

import az.portfolioapi.entity.enums.SkillLevel;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @Enumerated(EnumType.STRING)
    SkillLevel skillLevel;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    Portfolio portfolio;
}
