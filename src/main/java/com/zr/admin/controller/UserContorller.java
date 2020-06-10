package com.zr.admin.controller;


import com.zr.admin.bean.Role;
import com.zr.admin.bean.User;
import com.zr.admin.common.*;
import com.zr.admin.common.utils.ExcelUtil;
import com.zr.admin.service.RoleService;
import com.zr.admin.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@RequestMapping("/user")
@RestController
public class UserContorller {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    private static final Logger logger = Logger.getLogger(UserContorller.class);

    @RequestMapping("/login")
    @ResponseBody
    @LoggerManager(type = LogType.LOGIN,module = "用户模块",logDesc = "用户登录")
    public Object login(User user, HttpServletRequest request , HttpServletResponse response){
        logger.info("log4j:______________________login");

        Result<Object> re = new Result<>("-1","登录失败");
            //  加上 shiro
            Map<String,Object> map = new HashMap<>();
            // code -1  登录异常
            map.put("code","-1");
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassWord());
            try {
                subject.login(token);
              //  re.setCode("0000");
                re.setMsg("result:登录成功");
                map.put("code","0");
                map.put("msg","登录成功");
            } catch (IncorrectCredentialsException e) {
                map.put("msg", "密码错误");
            } catch (LockedAccountException e) {
                map.put("msg", "登录失败，该用户已被冻结");
            } catch (AuthenticationException e) {
                map.put("msg", "该用户不存在");
            } catch (Exception e) {
                e.printStackTrace();
            }
//


            //
            User loginUser = userService.getUser( user.getUserName());

            if ( null != loginUser ) {
                if ( user.getPassWord().equals(loginUser.getPassWord())){
                    HttpSession session = request.getSession();
                    session.setAttribute("loginUser",loginUser);
                    Role role  = roleService.getRoleByUserId(loginUser.getUserName());
                    // 根据角色id 获取资源 保存session
                   List<Map<String,Object>>  permissions  = roleService.getListByRoleId(role.getId());
                   List<String> pps = new ArrayList<>();
                    for ( Map<String,Object >  m:permissions                         ) {
                        if(m.containsKey("href")){
                            pps.add(m.get("href").toString());
                        }

                    }

                    session.setAttribute("permission",pps);
                    session.setAttribute("role",role );
                    map.put("loginUser",loginUser);
                    map.put("role",role);
                    re.setCode("0");
                    re.setData(map);
                    return  re;
                }
            }



        return map;
    }


    @RequestMapping("add")
    @LoggerManager(type = LogType.INSERT,module = "用户模块",logDesc = "用户添加")
    public Object add( @RequestParam Map<String,Object> map){

        String id =  map.get("id").toString();

        if(!StringUtils.isBlank(id)){ //  有id 就修改
            userService.updateUser(map);
            // 并 修改 用户角色表
            roleService.updateUserRole(map);
            return "ok";
        }


        // 管理员添加用户时  默认密码为 123456
       if(!map.containsKey("passWord")){
            map.put("passWord",123456);

       }
       userService.addUser(map);
        // 添加完用户 同时i添加角色
        String  uid  = map.get("id").toString();
        map.put("uid",uid);
        roleService.insertUserRole( map);

        return "ok";
    }

    @RequestMapping("/logout")
    @LoggerManager(type = LogType.DELETE,module = "用户模块",logDesc = "用户退出")
    public Object logout(HttpServletRequest request ){
        HttpSession session = request.getSession();
        session.removeAttribute("loginUser");
        session.removeAttribute("role");
        return "ok";
    }

    @RequestMapping("/del")
    @LoggerManager(type = LogType.DELETE,module = "用户模块",logDesc = "用户删除")
    public Object del(int id ){
        int re = userService.delUserByid(id);
        return "ok";
    }
    @RequestMapping("/batchdel")
    @LoggerManager(type = LogType.DELETE,module = "用户模块",logDesc = "用户批量删除")
    public Object batchdel(@RequestParam Map<String,Object> map){

        if(map.containsKey("ids")){
           // String[] ids = map.get("ids").toString().split(",")
            String[] ids = map.get("ids").toString().split(",");

            for ( String id:ids     ) {
                userService.delUserByid(Integer.valueOf(id));
            }
        }

       // int re = userService.delUserByid(id);
        return "ok";
    }

    @RequestMapping("/getuserlist")
    @LoggerManager(type = LogType.QUERY,module = "用户模块",logDesc = "用户分页查询")
    public Object list( @RequestParam( defaultValue="1",required=true) int   page,
                        @RequestParam( defaultValue="10",required=true) Integer limit,
                        HttpServletResponse response){


      //  response.addHeader("Access-Control-Allow-Origin", "http://192.168.1.101:8848");
        PageHelper.startPage(page,limit);
        Map<String, Object> map =  new HashMap<>();
        List<Map<String, Object>> users = userService.getList(map);
        PageInfo<Map<String, Object>> info = new PageInfo< >(users);
      //  model.addAttribute("pageInfo",info);
        PageUtils pageUtils = new PageUtils();
        pageUtils.setCode("0");
        pageUtils.setCount(String.valueOf(info.getTotal()));
        pageUtils.setData(info.getList());
        return pageUtils;
    }

    @RequestMapping("/getalluser")
    @LoggerManager(type = LogType.QUERY,module = "用户模块",logDesc = "获取所有用户")
    public Object getAllUser(){
        return  userService.getAllUser();
    }

    @RequestMapping("/export") // 导出用户
    @LoggerManager(type = LogType.EXPORT,module = "用户模块",logDesc = "用户导出到Excel")
    public Object export(@RequestParam Map<String,Object> map, HttpServletRequest request,HttpServletResponse response){
        // 表头
        Object[] titles = new Object[]{"用户名","姓名","性别","电话","单位","地址","级别"};

        List<Object[]> data = new ArrayList<>();
       // List< Map<String,Object>> list = userService.getList(map);
        List< Map<String, Object>> list = userService.getList(map);
        for ( Map<String, Object> m :list   ) {

            String sex = "";
            sex = "0".equals(m.get("sex").toString())  ?  "男": "女";
            Object[] obj = new Object[]{
                   m.get("userName"),m.get("realName")
                    ,sex
                    ,m.get("phone")
                    ,m.get("dept"),m.get("address"),m.get("rName")
            };
            data.add(obj);
        }
        ExcelUtil.export(request,response,"用户列表-" + DateFormatUtils.format(new Date(), "yyyyMMdd"),titles, data);
        return "ok";
    }

    @RequestMapping(value = "/import", method = {RequestMethod.POST})
    @LoggerManager(type = LogType.IMPORT,module = "用户模块",logDesc = "用户导入")
    public Object headImg(@RequestParam(value="file",required=false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<String[][]> list =      ExcelUtil.parseExcelData(file.getName(),file.getInputStream(),1);
        for (String[][] s : list) {
            for (int j = 0; j < s.length; j++) {
                String[] rowObject = s[j];  // 每行的数据 都是一个对象
               // 创建一个 map 来接收
                Map<String,Object> map = new HashMap<>();
                map.put("userName",rowObject[0]);
                map.put("passWord",123456);
                map.put("realName",rowObject[1]);
                String sex  =rowObject[2];
                if("男".equals(sex)){
                    map.put("sex",0);
                }else {
                    map.put("sex",1);
                }
                map.put("phone",rowObject[3]);
                map.put("dept",rowObject[4]);
                map.put("address",rowObject[5]);
                userService.addUser(map); // 添加用户 在添加对应角色

                String rName  = rowObject[6].toString();
                Map<String,Object> re = roleService.getRoleIdByName(rName);
                if(null == re ){ //说明角色 尚未创建，，， 所以设置为普通用户
                  re.put("rid", Common.PUTONGROLEID);
                }else {
                  re.put("uid",map.get("id"));
                }
                roleService.insertUserRole(re);// 给用户添加角色
            }
        }
        //

        return  "ok";
    }


}
