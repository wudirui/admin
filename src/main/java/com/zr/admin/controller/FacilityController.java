package com.zr.admin.controller;

import com.zr.admin.common.PageUtils;
import com.zr.admin.service.FacilityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

//import org.apache.tomcat.util.http.fileupload.FileItem;
//import org.apache.tomcat.util.http.fileupload.FileItemFactory;
//import org.apache.tomcat.util.http.fileupload.FileUploadException;
//import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
//import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

@RestController
@RequestMapping("/facility")
public class FacilityController {


    @Autowired
    private FacilityService facilityService;

    @RequestMapping("add")
    public Object add(@RequestParam Map<String,Object> map){
        String id =  map.get("id").toString();
        int re =-1;
        if(!StringUtils.isBlank(id)){
            re  =  facilityService.update(map);
            return  re;
        }

      re =  facilityService.insert(map);

        return  re;
    }
    @RequestMapping("update")
    public Object update(@RequestParam Map<String,Object> map){

        int i =  facilityService.update(map);

        return i;
    }

    @RequestMapping("getinfo")
    public Object getInfo(@RequestParam Map<String,Object> map){

        Object   o = facilityService.getInfo(map);

        return  o;
    }

    @RequestMapping("del")
    public Object del(@RequestParam Map<String,Object> map){
        if(!map.containsKey("id")){
            return "id为空";
        }

        int id = Integer.valueOf(map.get("id").toString());
        int i =  facilityService.delById(id);

        return  i;
    }
    @RequestMapping("/getlist")
    @ResponseBody
    public Object list( @RequestParam( defaultValue="1",required=true) int   page,
                        @RequestParam( defaultValue="10",required=true) Integer limit,
                        HttpServletResponse response){

        //  response.addHeader("Access-Control-Allow-Origin", "http://192.168.1.101:8848");
        PageHelper.startPage(page,limit);
        Map<String, Object> map =  new HashMap<>();
        List<Map<String,Object>> list =facilityService.getList(map);
        PageInfo<Map<String,Object>> info = new PageInfo< >( list);
        //  model.addAttribute("pageInfo",info);
        PageUtils pageUtils = new PageUtils();
        pageUtils.setCode("0");

        pageUtils.setCount(String.valueOf(info.getTotal()));
        pageUtils.setData(info.getList());
        return pageUtils;
    }



        //

    /**
     * 设备图片上传
     * @return {Result}
     */
    @RequestMapping(value = "/savefile", method = {RequestMethod.POST})
    @ResponseBody
    public Object headImg(@RequestParam(value="file",required=false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String prefix="";
        String dateStr="";
        String uploadDir = "/upload/";
        //保存上传
        OutputStream out = null;
        InputStream fileInput=null;
        try{
            if(file!=null){
                String originalName = file.getOriginalFilename();
                prefix=originalName.substring(originalName.lastIndexOf(".")+1);
                dateStr = String.valueOf(new Date().getTime());
                String filepath = request.getServletContext().getRealPath("/static") + uploadDir + dateStr + "." + prefix;
                filepath = filepath.replace("\\", "/");
                File files=new File(filepath);
                //打印查看上传路径
                System.out.println(filepath);
                if(!files.getParentFile().exists()){
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
            }
        }catch (Exception e){
        }finally{
            try {
                if(out!=null){
                    out.close();
                }
                if(fileInput!=null){
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        Map<String,Object> map2=new HashMap<>();
        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("data",map2);
        map2.put("src","../../../static"+uploadDir + dateStr + "." + prefix);
        return map;
    }



    @RequestMapping("/untying")
    public Object untying(@RequestParam String id ){
        try {
            if(!StringUtils.isBlank(id)){
                return   facilityService.untying(Integer.valueOf(id));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }


    //end
}
