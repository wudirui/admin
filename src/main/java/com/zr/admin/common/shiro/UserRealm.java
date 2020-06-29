package com.zr.admin.common.shiro;


import com.zr.admin.bean.Permission;
import com.zr.admin.bean.Role;
import com.zr.admin.bean.User;
import com.zr.admin.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lenovo on  三月
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    private Logger logger= LoggerFactory.getLogger(UserRealm.class);

    /**
     * 提供用户信息，返回权限信息
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("---------------------------->授权认证：");
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        String userName=(String) principals.getPrimaryPrincipal();
        User user = userService.getUser(userName);

        Set<Role> roles =user.getRoleList();
        Set<String> roleSet=new HashSet<>();
        Set<String>  pemissionSet=new HashSet<>();

        for (Role r: roles
             ) {
            roleSet.add(r.getName());
            for (Permission p :r.getPermissionList()
                 ) {
                pemissionSet.add(p.getName());
            }

        }
        //    roleSet.add( userService.findRoleByRoleId( roleId  ) );
            //将拥有角色的所有权限放进Set里面，也就是求Set集合的并集
            //由于我这边的数据表设计得不太好，所以提取set集合比较麻烦


        // 将角色名称组成的Set提供给授权info
        authorizationInfo.setRoles( roleSet );
        // 将权限名称组成的Set提供给info
        authorizationInfo.setStringPermissions(pemissionSet);

        return authorizationInfo;
    }

    /**
     * 提供帐户信息，返回认证信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("---------------------------->登陆验证:");
        String userName=(String)authenticationToken.getPrincipal();
        User user = userService.getUser(userName);
        if(user==null) {
            //用户不存在就抛出异常
            throw new UnknownAccountException();
        }
//        if( State.LOCKED.equals( user.getState() )  ) {
//            //用户被锁定就抛异常
//            throw new  LockedAccountException();
//        }
        //密码可以通过SimpleHash加密，然后保存进数据库。
        //此处是获取数据库内的账号、密码、盐值，保存到登陆信息info中
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(user.getUserName(),
              user.getPassWord(),
                ByteSource.Util.bytes(user.getPassWord())   ,
                getName());                   //realm name

        return authenticationInfo;
    }
}