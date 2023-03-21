package com.cintra.helpers;

import com.cintra.model.User;
import com.cintra.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.http.HttpRequest;

@Component
public class Users {
    public static User getCurrentUser(HttpServletRequest request, UserService userService){
        User currentUser = null;
        try{
            Cookie[] cookies = request.getCookies();

            for(Cookie c: cookies){
                if(c.getName().equals("userToken")){
                    currentUser = userService.findByToken(c.getValue());
                    break;
                }
            }
        }catch (Exception err){
            err = err;
        }
        return currentUser;
    }
}
