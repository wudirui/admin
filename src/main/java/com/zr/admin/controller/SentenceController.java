package com.zr.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zr.admin.common.LogType;
import com.zr.admin.common.LoggerManager;
import com.zr.admin.common.PageUtils;
import com.zr.admin.service.SentenceService;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.zr.admin.common.utils.OfficeUtils.importMain;
import static com.zr.admin.common.utils.OfficeUtils.readExcelContentz;

@RestController
@RequestMapping(value = "/sentence")
public class SentenceController {
    private final static Logger logger = LoggerFactory.getLogger(SentenceController.class);
    @Autowired
    SentenceService sentenceService;

    @RequestMapping("getlist")
    @ResponseBody
    public Object list(@RequestParam(defaultValue = "0", required = true) int page,
                       @RequestParam(defaultValue = "10", required = true) int limit,
                       @RequestParam Map<String, Object> params) {
        PageHelper.startPage(page, limit);
        Iterator it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (entry.getValue() == null || entry.getValue() == "") {
                params.put((String) entry.getKey(), null);
            }
        }
        List<Map<String, Object>> list = sentenceService.getList(params);
        PageInfo<Map<String, Object>> info = new PageInfo<>(list);
        PageUtils pageUtils = new PageUtils();
        pageUtils.setCode("0");
        pageUtils.setCount(String.valueOf(info.getTotal()));
        pageUtils.setData(info.getList());
        return pageUtils;
    }

    @RequestMapping("del")
    public Object del(@RequestParam Map<String, Object> map) {
        if (!map.containsKey("id")) {
            return "id为空";
        }
        int id = Integer.valueOf(map.get("id").toString());
        int i = sentenceService.delById(id);
        return i;
    }

    @RequestMapping("delAll")
    public Object delAll(@RequestParam Map<String, Object> map) {
        String[] ids = map.get("ids").toString().split(",");
        int i = -1;
        for (String id : ids) {
            if (id != null && !id.equals("")) {
                i = sentenceService.delById(Integer.valueOf(id));
            }
        }
        return i;
    }

    @RequestMapping("add")
    @LoggerManager(type = LogType.INSERT, module = "语料", logDesc = "语料添加/编辑")
    public Object add(@RequestParam Map<String, Object> map) {
        String id = map.get("id").toString();
        if (!StringUtils.isBlank(id)) { //  有id 就修改
            sentenceService.updateSentence(map);
            return "ok";
        }
        return "error";
    }

    // 导入
    @RequestMapping("fileUpload")
    public String fileUpload(@RequestParam MultipartFile file) throws IOException, JSONException {
        JSONObject ret = new JSONObject();
        String msg = "success";
        String code = "0";
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = formatter.format(currentTime);
        try {
            List<Object> objects = readExcelContentz(file);
            ret.put("data", objects);
            for (Object object : objects) {
                String sentence = String.valueOf(object);
                logger.info("out the add:"+sentence);
                if (sentence!=null&&!sentence.equals("")){
                    logger.info("int the add:"+sentence);
                    sentenceService.addSentence(sentence,createTime);
                }
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            msg = "error";
            code = "1";
        }
        ret.put("message", msg);
        ret.put("code", code);
        return ret.toString();
    }
}
