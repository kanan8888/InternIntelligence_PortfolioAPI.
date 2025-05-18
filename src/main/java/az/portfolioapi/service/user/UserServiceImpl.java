package az.portfolioapi.service.user;

import az.portfolioapi.entity.ExperienceEntity;
import az.portfolioapi.mapper.UserMapper;
import az.portfolioapi.dto.User.UserRequest;
import az.portfolioapi.dto.User.UserResponse;
import az.portfolioapi.entity.UserEntity;
import az.portfolioapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserResponse createUser(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("User with email " + request.getEmail() + " already exists.");

        if (userRepository.existsByUsername(request.getUsername()))
            throw new RuntimeException("User with userName " + request.getUsername() + " already exists.");

        UserEntity user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found"));
        userMapper.updateEntity(userRequest,user);
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public UserResponse getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toResponse)
                .orElseThrow(()-> new RuntimeException("user not found"));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("user not found"));
        userRepository.delete(user);
    }
}
