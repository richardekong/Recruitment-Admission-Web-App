package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.ManagedUser;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository.UserRepository;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.response.CustomException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository repo;

    @Autowired
    public void setRepo(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public ManagedUser insert(ManagedUser manageduser) {
        boolean existsByUsername = repo.existsByUsername(manageduser.getUsername());
        boolean existsById = repo.existsById(manageduser.getUid());
        if (existsById && existsByUsername) {
            throw new CustomException(
                    format("User already with %s already exists", manageduser.getUsername()),
                    HttpStatus.CONFLICT);
        }
        return repo.save(manageduser);
    }


    @Override
    public ManagedUser updateUser(ManagedUser managedUser) {
        return repo.save(managedUser);
    }

    @Override
    public Optional<ManagedUser> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ManagedUser manageUser = repo.findManageUserByUsername(username);
        if (manageUser == null) {
            throw new UsernameNotFoundException(username);
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + manageUser.getUserRole()));
        return new org.springframework.security.core.userdetails.User(manageUser.getUsername(), manageUser.getPassword(), authorities);
    }

    @Override
    public List<ManagedUser> findAllManagedUsers() {
        return repo.findAllManagedUsers();
    }

    @Override
    public void delete(Long uid) {
        repo.deleteById(uid);
    }
}
