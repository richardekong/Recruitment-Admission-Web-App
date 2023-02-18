package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.ManageUser;

import java.util.List;

public interface UserRepository extends CrudRepository<ManageUser, Long> {

    @Query("SELECT u From user u Where u.username=:username")
    ManageUser findManageUserByUsername(String username);

    @Query("SELECT u FROM user u")
    List<ManageUser> findAllManagedUsers();

    @Query("DELETE FROM user u WHERE u.uid=:uid")
    void delete(Long uid);

}
