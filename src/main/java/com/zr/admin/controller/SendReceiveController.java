package com.zr.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zr.admin.common.PageUtils;
import com.zr.admin.service.CorpusService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/wch")
public class SendReceiveController {
    Logger logger = LoggerFactory.getLogger(SendReceiveController.class);
    @Autowired
    CorpusService corpusService;

    @RequestMapping(value = "getSentence", method = RequestMethod.POST)
    public Object receiveMsg(@RequestBody JSONObject jsonObject) {
        JSONObject params = jsonObject.getJSONObject("params");
        Integer index = jsonObject.getInteger("index");
        String region = StringUtils.join(params.getJSONArray("region"), ",");
        params.put("region", region);
//        PageHelper.startPage(index, 1);
        List<Map<String, Object>> list = corpusService.getSentence(params);
        Integer count = corpusService.getAlreadyRecordSentenceCount(params);
        if (count == null) {
            count = 0;
        }
        PageInfo<Map<String, Object>> info = new PageInfo<>(list);
        PageUtils pageUtils = new PageUtils();
        pageUtils.setCode("0");
//        pageUtils.setCount(String.valueOf(info.getTotal()));
        pageUtils.setCount(Integer.valueOf(count) + "");
        pageUtils.setData(info.getList());
        return pageUtils;
    }

    @RequestMapping(value = "sendAudio", method = RequestMethod.POST)
    public String sendMsg(@RequestParam("audioFile") MultipartFile audioFile, @RequestParam("name") String name,
                          @RequestParam("sex") String sex, @RequestParam("address") String address, @RequestParam("age") String age,
                          @RequestParam("dialect") String dialect, @RequestParam("sentenceId") String sentenceId) throws IOException {
        String preffix = "data:audio/wav;base64,";
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String audioStr = preffix + base64Encoder.encode(audioFile.getBytes()).replaceAll("[\\s*\t\n\r]", "");

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = formatter.format(date);
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("audioStr", audioStr);
        map.put("sex", sex);
        map.put("dialect", dialect);
        map.put("address", address);
        map.put("age", age);
        map.put("sentenceId", sentenceId);
        map.put("createTime", createTime);
        corpusService.addCorpus(map);
        System.out.println(name);
        return "ok";
    }
}
