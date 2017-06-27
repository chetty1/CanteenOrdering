package Security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import Repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import  Repositories.userRepository;

/**
 *
 * @author anjana.m
 */
@Component
public class MongoUserDetailService implements UserDetailsService {

    @Resource
    @Autowired
    private userRepository userRepo;


    private org.springframework.security.core.userdetails.User userdetails;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        Model.Staff user = getUserDetail(username);


        userdetails = new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getAuthorities(user));
        System.out.println(userdetails);
        return userdetails;
    }

    public List<GrantedAuthority> getAuthorities(Model.Staff user) {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        for (String role:user.getRole()) {
            authList.add(new SimpleGrantedAuthority(role));

        }

        return authList;
    }

    public Model.Staff getUserDetail(String email) {
        Model.Staff user  = userRepo.findByUsername(email);
      //  System.out.println(user.toString());
        return user;
    }


}