package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.ManageUser;

import java.util.List;
public interface UserService extends UserDetailsService {

    ManageUser insert(ManageUser manageuser);
    List<ManageUser> findAllManagedUsers();
    void delete(Long uid);
}
