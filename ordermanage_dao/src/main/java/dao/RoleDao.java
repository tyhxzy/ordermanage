package dao;

import domain.Role;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {
    @Select("SELECT * FROM role WHERE id IN (SELECT roleId FROM user_role WHERE userid = #{userId})")
    List<Role> findByUserId(String userId);
}
