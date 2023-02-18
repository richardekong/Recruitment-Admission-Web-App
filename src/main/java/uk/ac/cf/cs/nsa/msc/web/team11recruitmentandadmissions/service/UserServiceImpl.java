package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.ManageUser;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository.UserRepository;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.response.CustomException;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository repo;

    @Autowired
    public void setRepo(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public ManageUser insert(ManageUser manageuser) {
        if (repo.existsById(manageuser.getUid())) {
            throw new CustomException(
                    format("User already with %s already exists", manageuser.getUsername()),
                    HttpStatus.CONFLICT);
        }
        return repo.save(manageuser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ManageUser manageUser = repo.findManageUserByUsername(username);
        if (manageUser == null) {
            throw new UsernameNotFoundException(username);
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + manageUser.getUserRole()));
        return new User(manageUser.getUsername(), manageUser.getPassword(), authorities);
    }

    @Override
    public List<ManageUser> findAllManagedUsers() {
        return repo.findAllManagedUsers();
    }

    @Override
    public void delete(Long uid) {
        repo.delete(uid);
    }
}
