package az.portfolioapi.security;

import az.portfolioapi.entity.UserEntity;
import az.portfolioapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserEntity user = userRepository.findByUserName(username)
                    .orElseThrow(() -> new UsernameNotFoundException("İstifadəçi tapılmadı: " + username));

            log.debug("İstifadəçi yükləndi: {}, Email: {}", user.getUserName(), user.getEmail());

            return new CustomUserDetails(user);
        } catch (UsernameNotFoundException ex) {
            log.error("İstifadəçi tapılmadı: {}", username);
            throw ex;
        } catch (Exception ex) {
            log.error("İstifadəçi yüklənərkən xəta: {}", ex.getMessage());
            throw new UsernameNotFoundException("İstifadəçi yüklənərkən xəta", ex);
        }
    }
}


