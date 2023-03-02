package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.ManagedUser;

import java.util.List;

public interface UserRepository extends CrudRepository<ManagedUser, Long> {

    @Query("SELECT u From managed_user u Where u.username=:username")
    ManagedUser findManageUserByUsername(String username);

    @Query("SELECT u FROM managed_user u")
    List<ManagedUser> findAllManagedUsers();

    boolean existsByUsername(String username);

}
