package uz.ama.travel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.ama.travel.dto.LoginDto;
import uz.ama.travel.dto.ResponceDto;
import uz.ama.travel.dto.SignupDto;
import uz.ama.travel.exception.CustomException;
import uz.ama.travel.model.AuthenticationToken;
import uz.ama.travel.model.User;
import uz.ama.travel.repository.UserRepository;

import javax.persistence.Table;
import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Transactional
    public ResponceDto signUp(SignupDto signupDto) {
        //check user
        if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
            throw new CustomException("user already present");
        }
        //save the user
        User user = new User(signupDto.getName(), signupDto.getEmail(), signupDto.getPassword());
        userRepository.save(user);

        ResponceDto responceDto = new ResponceDto("success", "dummy responce");
        return responceDto;
    }

    @Transactional
    public ResponceDto login(LoginDto loginDto) {
        //check user
        boolean f = false;
        if (Objects.nonNull(userRepository.findByEmail(loginDto.getEmail()))) {
            if(Objects.nonNull(userRepository.findByPassword(loginDto.getPassword()))){
                f = true;
            }else{
                throw new CustomException("not matching password");
            }
        }

        ResponceDto responceDto = new ResponceDto("success", "dummy responce");
        return responceDto;
    }


}
