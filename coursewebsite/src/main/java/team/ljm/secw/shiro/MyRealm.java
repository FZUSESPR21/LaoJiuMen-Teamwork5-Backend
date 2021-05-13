package team.ljm.secw.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import team.ljm.secw.entity.Student;
import team.ljm.secw.entity.Teacher;
import team.ljm.secw.service.ILoginService;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;


public class MyRealm extends AuthorizingRealm {

    @Resource
    ILoginService loginService;

    Logger logger = LoggerFactory.getLogger(MyRealm.class);

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("MyRealm: doGetAuthorizationInfo");
//        return new SimpleAuthorizationInfo();
        //String username = principalCollection.getPrimaryPrincipal().toString();
        String account = JwtUtil.getAccount(principalCollection.toString());
        String type = JwtUtil.getType(principalCollection.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();
        roles.add(loginService.findRoles(type));
        //Set<String> permissions = userService.findPermissions(username);
        info.setRoles(roles);
        //info.setStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("MyRealm: doGetAuthenticationInfo");
        String token = (String) authenticationToken.getCredentials();
        String account = JwtUtil.getAccount(token);
        String type = JwtUtil.getType(token);
        System.out.println("doGetAuthenticationInfo:  " + type + account + token);
        if (account == null || "".equals(account)) {
            throw new AuthenticationException("token invalid");
        }

        assert type != null;
        if (type.equals("0")) {
            Student student = loginService.findStudentByAccount(account);
            if (student == null) {
                System.out.println("student don't exist");
                throw new AuthenticationException("student don't exist");
            }
            System.out.println("doGetAuthenticationInfo:  token:" + token + "  account:" + account + "  password:"+student.getPwd());
            if (!JwtUtil.verify(token, type, account, student.getPwd())) {
                System.out.println("account or password error");
                throw new AuthenticationException("account or password error");
            }
        } else if (type.equals("1")) {
            Teacher teacher = loginService.findTeacherByAccount(account);
            if (teacher == null) {
                System.out.println("teacher don't exist");
                throw new AuthenticationException("teacher don't exist");
            }
            System.out.println("doGetAuthenticationInfo:  token:" + token + "  account:" + account + "  password:"+teacher.getPwd());
            if (!JwtUtil.verify(token, type, account, teacher.getPwd())) {
                System.out.println("account or password error");
                throw new AuthenticationException("account or password error");
            }
        } else {
            System.out.println("type Error");
            throw new AuthenticationException("type Error");
        }

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
