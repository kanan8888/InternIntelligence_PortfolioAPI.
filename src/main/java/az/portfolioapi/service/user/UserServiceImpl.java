package az.portfolioapi.service.user;

import az.portfolioapi.dto.User.ResetPasswordRequest;
import az.portfolioapi.entity.enums.DegreeLevel;
import az.portfolioapi.entity.enums.UserRole;
import az.portfolioapi.exception.AccessDeniedException;
import az.portfolioapi.exception.user.EmailAlreadyTakenException;
import az.portfolioapi.exception.user.UserNotFoundException;
import az.portfolioapi.exception.user.UsernameAlreadyTakenException;
import az.portfolioapi.mapper.UserMapper;
import az.portfolioapi.dto.User.UserRequest;
import az.portfolioapi.dto.User.UserResponse;
import az.portfolioapi.entity.UserEntity;
import az.portfolioapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserResponse createUser(UserRequest request) {
        Optional<UserEntity> user = userRepository.findByUsernameOrEmail(request.getEmail(), request.getUsername());
        if (user.isPresent()) {
            if(user.get().getUsername().equals(request.getUsername())) {
                throw new UsernameAlreadyTakenException();
            }
            else {
                throw new EmailAlreadyTakenException();
            }
        }

        String password = request.getRole() == UserRole.ADMIN
                ? "Admin123" /*PasswordGenerator.generatePassword(20)*/
                : request.getPassword();


        UserEntity newUser = userMapper.toEntity(request);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser = userRepository.save(newUser);
        //if(request.getRole() == UserRole.ADMIN) mailService.sendSimpleEmail(to, subject, text);
        return userMapper.toResponse(newUser);
    }   /**/

    @Override
    public UserResponse updateUser(Long userId, UserRequest request) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Optional<UserEntity> userForCheckEmailAndUsername = userRepository.findByUsernameOrEmail(request.getEmail(), request.getUsername());
        if (userForCheckEmailAndUsername.isPresent()) {
            if(userForCheckEmailAndUsername.get().getUsername().equals(request.getUsername())) {
                throw new UsernameAlreadyTakenException();
            }
            else {
                throw new EmailAlreadyTakenException();
            }
        }

        userMapper.updateEntity(request,user);
        return userMapper.toResponse(userRepository.save(user));
    }   /**/

    @Override/**/
    public void resetPassword(String username, ResetPasswordRequest request) {
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(username, request.getCurrentPassword())
           );
           if (!request.getNewPassword().equals(request.getConfirmPassword())) {
               throw new RuntimeException("Passwords do not match");
           }
           if (request.getCurrentPassword().equals(request.getNewPassword())) {
               throw new RuntimeException("Same password");
           }
           UserEntity user = userRepository.findByUsername(username)
                   .orElseThrow(UserNotFoundException::new);
           user.setPassword(passwordEncoder.encode(request.getNewPassword()));
           userRepository.save(user);
    }/**/

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserResponse getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::toResponse)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Page<UserResponse> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toResponse);
    }

    @Override
    public Page<UserResponse>filterUsers(String username,
                                         String firstName,
                                         String lastName,
                                         String email,
                                         UserRole role,
                                         String educationInstitution,
                                         DegreeLevel educationDegree,
                                         String experienceCompany,
                                         String experiencePosition,
                                         String skillName,
                                         Pageable pageable) {
        return userRepository.filterUsers(username, firstName, lastName, email, role, educationInstitution, educationDegree, experienceCompany, experiencePosition, pageable)
                .map(userMapper::toResponse);
    }

    @Override
    public void deleteUser(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        if(user.getRole() == UserRole.ADMIN) {
            throw new AccessDeniedException("This user is admin, it cannot be deleted");
        }
        userRepository.delete(user);
    }
}


/*

    @Override
    public UserResponse createUser(UserRequest request) {
        Optional<UserEntity> user = userRepository.findByUsernameOrEmail(request.getEmail(), request.getUsername());
        if (user.isPresent()) {
            if(user.get().getUsername().equals(request.getUsername())) {
                throw new RuntimeException("Username is already taken");
            }
            else {
                throw new RuntimeException("Email is already taken");
            }
        }
        UserEntity newUser = userMapper.toEntity(request);
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        return userMapper.toResponse(userRepository.save(newUser));
    }


 */