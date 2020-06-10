//package com.example.demo.shiro;
//
//import com.example.demo.bean.Permission;
//import com.example.demo.bean.Role;
//import com.example.demo.bean.User;
//import com.example.demo.service.UserService;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.*;
//
//public class AuthRealm {
//    @Autowired
//    private UserService userService;
//
//    //认证.登录
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        UsernamePasswordToken utoken=(UsernamePasswordToken) token;//获取用户输入的token
//        String username = utoken.getUsername();
//        User user = userService.getUser(username);
//        return new SimpleAuthenticationInfo(user, user.getPassWord(),this.getClass().getName());//放入shiro.调用CredentialsMatcher检验密码
//    }
//    //授权
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
//        User user=(User) principal.fromRealm(this.getClass().getName()).iterator().next();//获取session中的用户
//        List<String> permissions=new ArrayList<>();
//        Set<Role> roles = user.getRoleList();
//        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
//        List<String> listrole = new ArrayList<>();
//        if(roles.size()>0) {
//            for(Role role : roles) {
//                if(!listrole.contains(role.)){
//                    listrole.add(role.getRole());
//                }
//                Set<Permission> modules = role.getModules();
//                if(modules.size()>0) {
//                    for(Permission module : modules) {
//                        permissions.add(module.getName());
//                    }
//                }
//            }
//        }
//        info.addRoles(listrole);                       //将角色放入shiro中.
//        info.addStringPermissions(permissions);         //将权限放入shiro中.
//        return info;
//    }
//
//}
