package az.portfolioapi.service.auth;

import az.portfolioapi.dto.auth.request.LoginRequest;
import az.portfolioapi.dto.auth.request.RegisterRequest;
import az.portfolioapi.dto.auth.response.TokenResponse;
import az.portfolioapi.dto.auth.response.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);
    TokenResponse login(LoginRequest request);
    TokenResponse refresh(String refreshToken);
    //void logout(); //hələlik sadəcə refreshtokeni cookiedən silməklə logout edirəm, blacklist məntiqi yoxdur  deyə bu methoda ehtiyac yoxdur
}

