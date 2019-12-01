package dao;

import domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {
    @Select("SELECT * FROM role WHERE id IN (SELECT roleId FROM user_role WHERE userid = #{userId})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "permissions",column = "id",many = @Many(select = "dao.PermissionDao.findByRoleId"))
    })
    List<Role> findByUserId(String userId);

    @Select("SELECT * FROM role")
    List<Role> findAll();

    @Insert("insert into role values (#{id},#{roleName},#{roleDesc})")
    void add(Role role);

    /**
     * 通过UserId找到该用户没有的所有角色
     */
    @Select("SELECT * FROM role WHERE id NOT IN (SELECT roleId FROM user_role WHERE userId = #{userId})")
    List<Role> findOtherRoleByUserId(String userId);

    @Insert("insert into role_permission values(#{roleId},#{permissionId})")
    void addRolePermission(@Param("roleId") String roleId,@Param("permissionId") String permissionId);


}
