package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.ManagedUser;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    ManagedUser insert(ManagedUser manageduser);

    ManagedUser updateUser(ManagedUser managedUser);

    Optional<ManagedUser> findById(Long id);
    List<ManagedUser> findAllManagedUsers();
    void delete(Long uid);
}
