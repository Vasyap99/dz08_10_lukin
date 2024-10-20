package kok.spring21;

import kok.spring21.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kok.spring21.models.User;
import kok.spring21.dto.UserDto;


@Service
public class RegisterService {
    @Autowired
    UserRepository ur;    
    public void saveUser(UserDto u){   System.out.println(">>su-B");
        if(!ur.findByName(u.getName()).isPresent()){
				    System.out.println(">>su-1");
            ur.save(new User(u.getName(),u.getPass()));
        }
				    System.out.println(">>su-E");
    }
}
