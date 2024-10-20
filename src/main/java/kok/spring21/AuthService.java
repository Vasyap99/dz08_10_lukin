package kok.spring21;

import kok.spring21.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kok.spring21.models.User;
import kok.spring21.dto.UserDto;

import java.util.Optional;


@Service
public class AuthService {
    @Autowired
    UserRepository ur;
    public boolean authUser(UserDto u){   System.out.println(">>su-B");
        Optional<User> o=ur.findByName(u.getName());
        if(o.isPresent()){
            return o.get().getPass().equals(u.getPass());
        }
        return false;
    }
}
