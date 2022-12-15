package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.ManageUser;

import java.util.List;
@Service
public interface UserMapper {
    @Insert("Insert Into user(username, password) Values(#{username}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "uid")
    void insert(ManageUser manageuser);

    @Select("Select uid,username, password,user_role From user Where username=#{username}")
    ManageUser selectByUsername(String username);

    @Select("select * from user")
    List<ManageUser> findAll();

//    @Update("UPDATE USER SET name=#{name}, password=#{password}, user_role=#{userRole} WHERE uid=#{uid}")
//    void update(ManageUser manageUser);
    @Delete("DELETE FROM user WHERE uid=#{uid}")
    void delete(Long uid);
}