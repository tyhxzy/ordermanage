package service;

import dao.UserDao;
import domain.Role;
import domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    /**
     * 这个方法是给spring security调用的
     * spring security调用该方法，返回一个User对象
     * spring security通过操作该User对象完成认证。
     * 也就是说，不管我们查出来是什么对象，都要转成spring security的User对象
     */
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userDao.findByUsername(username);

        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for(Role role:userInfo.getRoles()){
            list.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        // 把自己的UserInfo对象转成SpringSecurity的User对象
        User user  = new User(userInfo.getUsername(),
                "{noop}"+userInfo.getPassword(),
                userInfo.getStatus() == 0 ? false:true,
                true,
                true,
                true,
                list);
        return user;
    }
}
