package dao;

import domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionDao {
    @Select("SELECT * FROM permission WHERE id IN (SELECT permissionId FROM role_permission WHERE roleId = #{roleId})")
    List<Permission> findByRoleId(String roleId);

    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission values(#{id},#{permissionName},#{url})")
    void add(Permission permission);
    /**
     * 通过RoleId找到他没有的权限
     */
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findOtherPermissionByRoleId(String roleId);
}
