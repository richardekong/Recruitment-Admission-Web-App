package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.ManageUser;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.mapper.UserMapper;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper UserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ManageUser manageUser = UserMapper.selectByUsername(username);
        if (null == manageUser) {
            throw new UsernameNotFoundException(username);
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + manageUser.getUserRole()));
        return new User(manageUser.getUsername(), manageUser.getPassword(), authorities);
    }
}