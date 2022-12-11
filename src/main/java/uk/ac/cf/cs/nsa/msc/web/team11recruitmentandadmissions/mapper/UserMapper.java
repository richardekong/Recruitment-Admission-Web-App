package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.ManageUser;
public interface UserMapper {
    @Insert("Insert Into user(username, password,user_role) Values(#{username}, #{password},#{userRole})")
    @Options(useGeneratedKeys = true, keyProperty = "uid")
    void insert(ManageUser manageuser);

    @Select("Select uid,username, password,user_role From user Where username=#{username}")
    ManageUser selectByUsername(String username);
}