package service;

import dao.PermissionDao;
import domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PermissionService {
    @Autowired
    private PermissionDao permissionDao;
    public List<Permission> findAll(){
        return permissionDao.findAll();
    }
    public void add(Permission permission){
        permission.setId(UUID.randomUUID().toString());
        permissionDao.add(permission);
    }
    public List<Permission> findOtherPermissionByRoleId(String roleId){
        return permissionDao.findOtherPermissionByRoleId(roleId);
    }

}
