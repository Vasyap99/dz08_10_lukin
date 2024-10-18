package kok.spring21.repo;

import kok.spring21.models.*;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.*;


@Component
public class UserRepository{
    @Autowired
    public SessionFactory sessionFactory;

    public Optional<User> findByName(String name){
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("from User where name='"+name+"'",User.class) .getResultList().stream().findFirst();
        }catch(Exception e){System.out.println(">>ERR111");e.printStackTrace(); return Optional.empty();}
    }

    public void save(User user){
        try(Session session = sessionFactory.openSession()){
            session.save(user);
        }catch(Exception e){System.out.println(">>ERR112");e.printStackTrace();}
    }
}