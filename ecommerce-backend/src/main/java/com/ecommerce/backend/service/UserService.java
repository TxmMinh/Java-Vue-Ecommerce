package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.ResponseDto;
import com.ecommerce.backend.dto.user.SignInDto;
import com.ecommerce.backend.dto.user.SignInResponseDto;
import com.ecommerce.backend.dto.user.SignupDto;
import com.ecommerce.backend.exceptions.AuthenticationFailException;
import com.ecommerce.backend.exceptions.CustomException;
import com.ecommerce.backend.model.AuthenticationToken;
import com.ecommerce.backend.model.User;
import com.ecommerce.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final AuthenticationService authenticationService;

    @Transactional
    public ResponseDto signUp(SignupDto signupDto) {
        userRepository.findByEmail(signupDto.getEmail()).ifPresent((entity) -> {
            throw new CustomException("user already present");
        });
        // hash the password
        String encryptedPassword;
        try {
            encryptedPassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new CustomException(e.getMessage());
        }
        User user = new User();
        BeanUtils.copyProperties(signupDto, user);
        user.setPassword(encryptedPassword);
        userRepository.save(user);

        // create the token
        final AuthenticationToken authenticationToken = new AuthenticationToken(user);
        authenticationService.saveConfirmationToken(authenticationToken);

        return new ResponseDto("success", "user created successfully");
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }

    public SignInResponseDto signIn(SignInDto signInDto) {
        User user = userRepository.findByEmail(signInDto.getEmail())
                .orElseThrow(() -> new AuthenticationFailException("user is not valid"));
        // compare password in DB
        try {
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))) {
                throw new AuthenticationFailException("wrong password");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new AuthenticationFailException(e.getMessage());
        }
        // retrieve the token
        AuthenticationToken token = authenticationService.getToken(user)
                .orElseThrow(() -> new CustomException("token is not present"));

        return new SignInResponseDto("success", token.getToken());
    }
}
