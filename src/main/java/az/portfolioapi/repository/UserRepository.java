package az.portfolioapi.repository;

import az.portfolioapi.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
    //Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUsername(String username);
    List<UserEntity> findAllByUsernameOrEmail(String username, String email);
    //Page<UserEntity> findByRole(UserRole role, Pageable pageable);

    @Query("""
    SELECT DISTINCT u FROM UserEntity u
    LEFT JOIN u.portfolios p
    LEFT JOIN EducationEntity e ON e.portfolio = p
    LEFT JOIN ExperienceEntity ex ON ex.portfolio = p
    LEFT JOIN SkillEntity s ON s.portfolio = p
    WHERE (:username IS NULL OR LOWER(u.username) LIKE LOWER(CONCAT('%', :username, '%')))
      AND (:firstName IS NULL OR LOWER(u.firstName) LIKE LOWER(CONCAT('%', :firstName, '%')))
      AND (:lastName IS NULL OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :lastName, '%')))
      AND (:email IS NULL OR LOWER(u.email) LIKE LOWER(CONCAT('%', :email, '%')))
      AND (:role IS NULL OR u.role = :role)
      AND (:educationInstitution IS NULL OR LOWER(e.institution) LIKE LOWER(CONCAT('%', :educationInstitution, '%')))
      AND (:educationDegree IS NULL OR e.degree = :educationDegree)
      AND (:experienceCompany IS NULL OR LOWER(ex.company) LIKE LOWER(CONCAT('%', :experienceCompany, '%')))
      AND (:experiencePosition IS NULL OR LOWER(ex.position) LIKE LOWER(CONCAT('%', :experiencePosition, '%')))
      AND (:skillName IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :skillName, '%')))
    """)
    Page<UserEntity> filterUsers(@Param("username") String username,
                                 @Param("firstName") String firstName,
                                 @Param("lastName") String lastName,
                                 @Param("email") String email,
                                 @Param("role") String role,
                                 @Param("educationInstitution") String educationInstitution,
                                 @Param("educationDegree") String educationDegree,
                                 @Param("experienceCompany") String experienceCompany,
                                 @Param("experiencePosition") String experiencePosition,
                                 @Param("skillName") String skillName,
                                 Pageable pageable);

}
