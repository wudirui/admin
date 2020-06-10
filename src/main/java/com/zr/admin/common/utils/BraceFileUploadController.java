//package com.example.demo.common.utils;
//
//import com.alibaba.dubbo.config.annotation.Reference;
//import com.cmos.brace.beans.computerlab.ComputerlabUsers;
//import com.cmos.brace.beans.personnel.PersonnelTemp;
//import com.cmos.brace.beans.power.Role;
//import com.cmos.brace.beans.project.ProjectCompact;
//import com.cmos.brace.beans.user.Company;
//import com.cmos.brace.beans.user.User;
//import com.cmos.brace.beans.workload.WorkOrder;
//import com.cmos.brace.beans.workload.Workload;
//import com.cmos.brace.iservice.enclosure.IEnclosureSV;
//import com.cmos.brace.iservice.personnel.IPersonnelSV;
//import com.cmos.brace.iservice.project.ICompactFrameSV;
//import com.cmos.brace.iservice.project.ICompactOrderSV;
//import com.cmos.brace.iservice.project.IProjectCompactSV;
//import com.cmos.brace.iservice.project.IProjectSV;
//import com.cmos.brace.iservice.user.ICompanySV;
//import com.cmos.brace.iservice.user.INumberSV;
//import com.cmos.brace.iservice.user.IUserSV;
//import com.cmos.brace.iservice.workload.IWorkOrderSV;
//import com.cmos.brace.iservice.workload.IWorkloadSV;
//import com.cmos.brace.utils.DateUtils;
//import com.cmos.brace.utils.ExcelUtil;
//import com.cmos.brace.utils.ObjectUtils;
//import com.cmos.brace.web.service.OnestStorage;
//import com.cmos.common.domain.Result;
//import com.cmos.common.exception.GeneralException;
//import com.cmos.common.web.upload.config.StorageConfig;
//import com.cmos.common.web.upload.storage.Storage;
//import com.cmos.common.web.upload.storage.StorageFactory;
//import com.cmos.commons.common.Common;
//import com.cmos.commons.enumtype.ITRoleType;
//import com.cmos.commons.enumtype.OperationEnum;
//import com.cmos.commons.enumtype.ProjectResourceTypes;
//import org.apache.commons.lang3.ArrayUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.tomcat.util.http.fileupload.IOUtils;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.CollectionUtils;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.UnsupportedEncodingException;
//import java.math.BigDecimal;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.regex.Pattern;
//
//@Controller
//public class BraceFileUploadController {
//
//    private static final com.cmos.core.logger.Logger log = com.cmos.core.logger.LoggerFactory.getActionLog(BraceFileUploadController.class);
//
//    private StorageConfig config;
//    private List<String> allFileTypes = new ArrayList<>();
//    private List<String> allFileZyy = new ArrayList<>();
//
//    @Reference(group = "itmanager")
//    private IEnclosureSV enclosureSV;
//    @Reference(group = "itmanager")
//    private IPersonnelSV personnelSV;
//
//    @Reference(group = "itmanager")
//    private IUserSV userSV;
//    @Reference(group = "itmanager")
//    private ICompanySV companySV;
//    @Reference(group = "itmanager")
//    private IProjectSV projectSV;
//    @Reference(group = "itmanager")
//    private IWorkOrderSV workOrdeSV;
//    @Reference(group = "itmanager")
//    private IWorkloadSV workloadSV;
//    @Reference(group = "itmanager")
//    private IProjectCompactSV projectCompactSV;
//    @Reference(group = "itmanager")
//    private INumberSV numberSV;
//    @Reference(group = "itmanager")
//    private ICompactFrameSV compactFrameSV;
//    @Reference(group = "itmanager")
//    private ICompactOrderSV compactOrderSV;
//
//    @Autowired
//    public BraceFileUploadController(StorageConfig config) {
//        this.config = config;
//        allFileTypes.add("text/xml");
//        allFileTypes.add("application/msword");
//        allFileTypes.add("text/plain");
//        allFileTypes.add("application/vnd.ms-excel");
//        allFileTypes.add("application/x-xls");
//        allFileTypes.add("application/vnd.ms-powerpoint");
//        allFileTypes.add("application/x-ppt");
//        allFileTypes.add("application/pdf");
//        allFileTypes.add("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
//        allFileTypes.add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        allFileTypes.add("application/x-zip-compressed");
//        allFileTypes.add("application/zip");
//        allFileTypes.add("application/vnd.openxmlformats-officedocument.presentationml.presentation");
//        allFileTypes.add("application/octet-stream");
//
//
//        allFileZyy.add("jspx");
//        allFileZyy.add("jspf");
//        allFileZyy.add("jsp");
//        allFileZyy.add("war");
//    }
//
//    /**
//     * 文件上传
//     *
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/upload-new", method = RequestMethod.POST)
//    @ResponseBody
//    public String handleFileUpload(HttpServletRequest request) {
//        OnestStorage service = (OnestStorage) StorageFactory.getStorage(config.getStorageClass());
//        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
//        JSONObject json = new JSONObject();
//        String dateTime = request.getParameter("dt");
//        try {
//            String rsMessage = checkFileTypes(files);
//            if(!StringUtils.isEmpty(rsMessage)){
//                json.put("status", "error");
//                json.put("message", rsMessage);
//                return json.toString();
//            }
//            for (int i = 0; i < files.size(); ++i) {
//                MultipartFile file = files.get(i);
//                if (!file.isEmpty()) {
//                    service.store(file, dateTime);
//                    log.info("附件" + file.getOriginalFilename() + "上传成功");
//                }
//            }
//            json.put("status", "ok");
//        } catch (Exception e) {
//            json.put("status", "error");
//            json.put("message", e.getMessage());
//            log.error("附件上传失败", e);
//        }
//        return json.toString();
//    }
//
//    /**
//     * 判断文件类型
//     *
//     * @param files
//     */
//    private String checkFileTypes(List<MultipartFile> files) {
//        StringBuilder builder = new StringBuilder();
//        String types = config.getFileTypes();
//        String[] fileTypes = StringUtils.isEmpty(types) ? new String[0] : types.split(",");
//        for (int i = 0; i < files.size(); ++i) {
//            MultipartFile file = files.get(i);
//            if (!allFileTypes.contains(file.getContentType())) {
//                builder.append("文件类型不支持");
//                break;
//            }
//            if (!file.isEmpty()) {
//                String fileName = file.getOriginalFilename();
//                if (StringUtils.isBlank(fileName)) {
//                    continue;
//                }
//                if (fileName.indexOf("<")!=-1 || fileName.indexOf(">")!=-1 ) {
//                    builder.append("文件名称包含特殊字符，请检查");
//                    break;
//                }
//                for(String s : allFileZyy){
//                    if(fileName.contains(s)){
//                        builder.append("文件为危险文件不能上传");
//                        break;
//                    }
//                }
//                if (!fileName.contains(".")) {
//                    builder.append("文件类型不支持");
//                    break;
//                }
//                String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
//                if (!ArrayUtils.contains(fileTypes, ext)) {
//                    builder.append("文件类型 '" + ext + "' 不支持");
//                    break;
//                }
//            }
//        }
//        return builder.toString();
//    }
//
//    /**
//     * 判断excel文件类型
//     *
//     * @param files
//     */
//    private String checkExcelFileTypes(List<MultipartFile> files){
//        StringBuilder rsMessage = new StringBuilder();
//        List<String> allFileTypes = new ArrayList<>();
//        allFileTypes.add("application/vnd.ms-excel");
//        allFileTypes.add("application/x-xls");
//        allFileTypes.add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        allFileTypes.add("application/octet-stream");
//        allFileTypes.add("application/x-zip-compressed");
//        //校验文件类型
//        String[] fileTypes = {"xls","xlsx"};
//        for (int i = 0; i < files.size(); ++i) {
//            MultipartFile file = files.get(i);
//            if (!allFileTypes.contains(file.getContentType())) {
//                rsMessage.append("文件类型不支持");
//                break;
//            }
//            if (!file.isEmpty()) {
//                String fileName = file.getOriginalFilename();
//                if (StringUtils.isBlank(fileName)) {
//                    continue;
//                }
//                if (fileName.indexOf("<")!=-1 || fileName.indexOf(">")!=-1 ) {
//                    rsMessage.append("文件名称包含特殊字符，请检查");
//                    break;
//                }
//                for(String s : allFileZyy){
//                    if(fileName.contains(s)){
//                        rsMessage.append("文件为危险文件不能上传");
//                        break;
//                    }
//                }
//                if (!fileName.contains(".")) {
//                    rsMessage.append("文件类型不支持");
//                    break;
//                }
//                String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
//                if (!ArrayUtils.contains(fileTypes, ext)) {
//                    rsMessage.append("文件类型 '" + ext + "' 不支持");
//                    break;
//                }
//            }
//        }
//        return rsMessage.toString();
//    }
//
//
//
//    @RequestMapping(value = "/delete-files", method = RequestMethod.POST)
//    @ResponseBody
//    public String deleteFiles(HttpServletRequest request) {
//        JSONObject jo = new JSONObject();
//        Storage service = StorageFactory.getStorage(config.getStorageClass());
//        String fileNames = request.getParameter("fileNames");  // 要删除的文件名 集合
//        String  enclosureIds =  request.getParameter("enclosureIds"); // 要删除的文件id 集合
//        String[] fNames;
//        String[] fIds;
//        try {
//            if (ObjectUtils.isNull(fileNames)) {
//                jo.put("returnCode", "-1");
//                jo.put("returnMessage", "参数错误");
//                return jo.toString();
//            }else {
//                fNames  = fileNames.split(",");
//            }
//            if ( ObjectUtils.isNull(enclosureIds)) {
//                jo.put("returnCode", "-1");
//                jo.put("returnMessage", "参数错误");
//                return jo.toString();
//            }else {
//                fIds  = enclosureIds.split(",");
//            }
//            OnestStorage onestStorage = (OnestStorage) service;
//            for (String fileName: fNames) {
//                onestStorage.deleteFile(fileName);
//            }
//            for(String id :fIds){
//                Integer fId = ObjectUtils.parseInt(id);
//                if(!ObjectUtils.isNull(fId)){
//                    enclosureSV.deleteByEnclosureId(fId);
//                    log.info("删除附件更新操作成功");
//                }else{
//                    log.info("删除的附件不存在");
//                    log.info("删除附件更新操作失败");
//                }
//            }
//            jo.put("returnCode", "0");
//            jo.put("returnMessage", "删除成功");
//            return jo.toString();
//        } catch (Exception e) {
//            log.error("文件删除失败", e);
//            jo.put("returnCode", "-1");
//            jo.put("returnMessage", e.getMessage());
//        }
//        return jo.toString();
//    }
//
//    /**
//     * 文件上传--添加的时候工作量导入
//     * @param request
//     * @return
//     */
//    @RequestMapping(value="/upload-import-workload", produces={"text/html;charset=UTF-8"}, method= RequestMethod.POST)
//    @ResponseBody
//    public String importExcel(HttpServletRequest request){
//        //读取导入excel
//        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
//        JSONObject json = new JSONObject();
//        List<Workload> workloadList = new ArrayList<>();
//        try {
//            //校验文件类型
//            String rsMessage = checkExcelFileTypes(files);
//            if(!StringUtils.isEmpty(rsMessage)){
//                json.put("status", "error");
//                json.put("message", rsMessage);
//                return json.toString();
//            }
////            Integer projectId = ObjectUtils.parseInt(request.getParameter("projectId"));
////            if(ObjectUtils.isNull(projectId)){
////                json.put("status", "error");
////                json.put("message", "请在选择项目后导入数据");
////                return json.toString();
////            }
////            //获取项目人员
////            List<String> projectPerson = new ArrayList<>();
////            List<String> importPerson = new ArrayList<>();
////            Map<String, Object> paramMap = new HashMap<>();
////            paramMap.put("projectId", projectId);
////            List<Map<String,Object>> rsMap = personnelSV.getPersonnelListByProjectId(paramMap);
////            if(rsMap.isEmpty()){
////                json.put("status", "error");
////                json.put("message", "该项目人员为空，请核实");
////                return json.toString();
////            }
////            for (Map<String, Object> map : rsMap) {
////                if(!ObjectUtils.isNull(map.get("personnelName"))){
////                    projectPerson.add((String) map.get("personnelName"));
////                }
////            }
//            for (int i = 0; i < files.size(); ++i) {
//                MultipartFile file = files.get(i);
//                if (file.isEmpty()) {
//                    json.put("status", "error");
//                    json.put("message", "对不起文件内容不能为空");
//                    return json.toString();
//                }
//                List<String[][]> list = ExcelUtil.parseExcelData(file.getOriginalFilename(), file.getInputStream(), 1);
//                if(ObjectUtils.isNull(list) || list.isEmpty()){
//                    json.put("status", "error");
//                    json.put("message", "对不起，工作量信息不能为空，请检查");
//                    return json.toString();
//                }
//                int resCount=0;
//                for (String[][] s : list) {
//                    for (int j = 0; j < s.length; j++) {
//                        ++resCount;
//                        String[] arr2 = s[j];
//                        //姓名
//                        if(ObjectUtils.isNull(arr2[0])){
//                            json.put("status", "error");
//                            json.put("message", "模板中第"+resCount+"行，姓名不能为空，请检查");
//                            return json.toString();
//                        }
//                        if(ObjectUtils.isNull(arr2[1])){
//                            json.put("status", "error");
//                            json.put("message", "模板中第"+resCount+"行，手机号不能为空，请检查");
//                            return json.toString();
//                        }
//                        if(ObjectUtils.isNull(arr2[2])){
//                            json.put("status", "error");
//                            json.put("message", "模板中第"+resCount+"行，工作内容不能为空，请检查");
//                            return json.toString();
//                        }
//                        if(ObjectUtils.isNull(arr2[3])){
//                            json.put("status", "error");
//                            json.put("message", "模板中第"+resCount+"行，正常工时不能为空，请检查");
//                            return json.toString();
//                        }
//                        Workload workloadTemp = new Workload();
//                        workloadTemp.setUserName(arr2[0]);
//                        workloadTemp.setUserPhone(arr2[1]);
//                        workloadTemp.setWorkContent(arr2[2]);
//                        workloadTemp.setNormalHours(arr2[3]);
//                        if(arr2.length > 4){
//                            if(ObjectUtils.isNull(arr2[4])){//如果加班工时为空那么让他为0
//                                workloadTemp.setOvertimeHours(String.valueOf(0));
//                            }else {
//                                workloadTemp.setOvertimeHours(arr2[4]);
//                            }
//                        }
//                        if(arr2.length > 5){
//                            if(ObjectUtils.isNull(arr2[5])){//如果合计工时为空
//                                BigDecimal b1 = new BigDecimal(workloadTemp.getNormalHours());
//                                BigDecimal b2 = new BigDecimal(workloadTemp.getOvertimeHours());
//                                workloadTemp.setTotalHours(String.valueOf(b1.add(b2)));
//                            }else {
//                                workloadTemp.setTotalHours(arr2[5]);
//                            }
//                        }
//                        if(arr2.length > 6){
//                            workloadTemp.setOvertimeRemarks(arr2[6]);
//                        }
//                        workloadList.add(workloadTemp);
//                        //importPerson.add(arr2[0]);
//                    }
//                }
//                json.put("workloadList", workloadList);
//                //判断文件姓名是否属于项目
//                /*for (String personName : importPerson) {
//                	if(!projectPerson.contains(personName)){
//                		json.put("status", "error");
//                		json.put("message", "姓名与项目人员不符合，请检查");
//                		return json.toString();
//                	}
//                }*/
//            }
//            json.put("status", "ok");
//        } catch (Exception e) {
//            json.put("status", "error");
//            json.put("message", e.getMessage());
//            log.error(e.getMessage(), e);
//        }
//        return json.toString();
//    }
//
//    /**
//     * 文件上传-批量上传厂商人员
//     * @param request
//     * @return
//     */
//    @RequestMapping(value="/upload-import-personnel",produces={"text/html;charset=UTF-8"},method= RequestMethod.POST)
//    @ResponseBody
//    public String importExcelPersonnel(HttpServletRequest request){
//        //读取导入excel
//        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
//        JSONObject json = new JSONObject();
//        List<PersonnelTemp> personnelList = new ArrayList<>();
//        try {
//            //校验文件类型
//            checkExcelFileTypes(files);
//            Integer projectId = ObjectUtils.parseInt(request.getParameter("projectId"));
//            if(ObjectUtils.isNull(projectId)){
//                json.put("status", "error");
//                json.put("message", "请在选择项目后导入数据");
//                return json.toString();
//            }
//            //获取项目人员
//            int resCount=0;
//            List<String> listPersons = new ArrayList<>();
//            for (int i = 0; i < files.size(); ++i) {
//                MultipartFile file = files.get(i);
//                List<String[][]> list = ExcelUtil.parseExcelData(file.getOriginalFilename(), file.getInputStream(), 1);
//                if (file.isEmpty()) {
//                    json.put("status", "error");
//                    json.put("message", "模板内容不能为空，请检查");
//                    return json.toString();
//                }
//                if(list.isEmpty()){
//                    json.put("status", "error");
//                    json.put("message", "模板中人员数据为空，请检查");
//                    return json.toString();
//                }
//                for (String[][] s : list) {
//                    for (int j = 0; j < s.length; j++) {
//                        ++resCount;
//                        String[] arr2 = s[j];
//                        if(arr2.length != 17){
//                            json.put("status", "error");
//                            json.put("message", "文件内容不符合模板规范");
//                            return json.toString();
//                        }
//                        PersonnelTemp personnelTemp = new PersonnelTemp();
//                        //合同和合同编号可以为空  故不做为空的判断
//                        if(StringUtils.isNotBlank(arr2[0])){
//                            if(arr2[0].trim().length()>200){
//                                json.put("status", "error");
//                                json.put("message", "模板中第"+resCount+"行，归属合同最大为200个字符，请检查");
//                                return json.toString();
//                            }else{
//                                personnelTemp.setPersonnelContract(arr2[0].trim());//合同(选填)
//                            }
//                        }
//                        //合同编号
//                        if(StringUtils.isNotBlank(arr2[1])){
//                            if(arr2[1].trim().length()>200){
//                                json.put("status", "error");
//                                json.put("message", "模板中第"+resCount+"行，合同编号最大为200个字符，请检查");
//                                return json.toString();
//                            }else{
//                                personnelTemp.setPersonnelContractCode(arr2[1].trim());//合同编号
//                            }
//                        }
//                        //姓名
//                        if(StringUtils.isBlank(arr2[2])){
//                            json.put("status", "error");
//                            json.put("message", "模板中第"+resCount+"行，姓名不能为空，请检查");
//                            return json.toString();
//                        }
//                        if(arr2[2].trim().length() > 10){
//                            json.put("status", "error");
//                            json.put("message", "模板中第"+resCount+"行，姓名最大为10个字符，请检查");
//                            return json.toString();
//                        }
//                        personnelTemp.setPersonnelName(arr2[2].trim());//人员姓名
//                        //身份证
//                        if(StringUtils.isBlank(arr2[3])){
//                            json.put("status", "error");
//                            json.put("message", "模板中第"+resCount+"行，身份证不能为空，请检查");
//                            return json.toString();
//                        }
//                        //验证身份证号18位
//                        if(!ObjectUtils.validateIdCard(arr2[3]) && arr2[3].length() == 18){
//                            json.put("status", "error");
//                            json.put("message", "第"+resCount+"行,身份证格式不正确，请检查");
//                            return json.toString();
//                        }
//                        //验证身份证号15位
//                        if(!ObjectUtils.validateIdCard2(arr2[3]) && arr2[3].length() == 15){
//                            json.put("status", "error");
//                            json.put("message", "第"+resCount+"行,身份证格式不正确，请检查");
//                            return json.toString();
//                        }
//                        if(arr2[3].length() != 18 && arr2[3].length() != 15){
//                            json.put("status", "error");
//                            json.put("message", "第"+resCount+"行,身份证格式不正确，请检查");
//                            return json.toString();
//                        }
//                        personnelTemp.setPersonnelIdNumber(arr2[3].trim());//身份证号
//                        //手机号码
//                        if(StringUtils.isBlank(arr2[4])){
//                            json.put("status", "error");
//                            json.put("message", "模板中第"+resCount+"行，手机号不能为空，请检查");
//                            return json.toString();
//                        }
//                        if(arr2[4].trim().length() != 11){
//                            json.put("status", "error");
//                            json.put("message", "模板中第"+resCount+"行，手机号格式不正确，请检查");
//                            return json.toString();
//                        }
//                        personnelTemp.setPersonnelPhone(arr2[4].trim());
//                        //姓名和手机号拼接  添加到数组里面
//                        listPersons.add(arr2[2].trim()+arr2[4].trim());
//                        //入场时间
//                        if(StringUtils.isBlank(arr2[5])){
//                            json.put("status", "error");
//                            json.put("message", "模板中第"+resCount+"行，入场时间不能为空，请检查");
//                            return json.toString();
//                        }
//                        personnelTemp.setPersonnelAdmissionDate(arr2[5]);
////                        //离场时间
////                        personnelTemp.setPersonnelLeaveDate(arr2[6]);
//
//                        //当入场时间和离场时间都不为空的情况下判断离场时间必须大于入场时间
////                        if(StringUtils.isNotBlank(arr2[5]) && StringUtils.isNotBlank(arr2[6])){
////                            if(!ObjectUtils.validateComPareTo(arr2[5],arr2[6])){
////                                json.put("status", "error");
////                                json.put("message", "第"+resCount+"行,离场日期必须大于等于入场日期，请检查");
////                                return json.toString();
////                            }
////                        }
//                        //评级
//                        if(StringUtils.isBlank(arr2[6])){
//                            json.put("status", "error");
//                            json.put("message", "模板中第"+resCount+"行，人员级别不能为空，请检查");
//                            return json.toString();
//                        }
//                        if(arr2[6].trim().length() > 20){
//                            json.put("status", "error");
//                            json.put("message", "模板中第"+resCount+"行，人员级别最长为20个字符，请检查");
//                            return json.toString();
//                        }
//                        personnelTemp.setPersonnelLevel(arr2[6].trim());
//                        //实习生
//                        personnelTemp.setPersonnelIfTrainee(StringUtils.isNotBlank(arr2[7]) &&  OperationEnum.workLoadTrue.getLabel().equals(arr2[7]) ? 0 : 1);
//                        //工作岗位
//                        if(StringUtils.isBlank(arr2[8])){
//                            json.put("status", "error");
//                            json.put("message", "模板中第"+resCount+"行，工作岗位不能为空，请检查");
//                            return json.toString();
//                        }
//                        if(arr2[8].trim().length() > 50){
//                            json.put("status", "error");
//                            json.put("message", "模板中第"+resCount+"行，工作岗位最长为50个字符，请检查");
//                            return json.toString();
//                        }
//                        personnelTemp.setPersonnelStation(arr2[8].trim());
//                        //是否记工作量(0 是 1 否)
//                        personnelTemp.setPersonnelIfWorkload(StringUtils.isNotBlank(arr2[9]) && OperationEnum.workLoadTrue.getLabel().equals(arr2[9]) ? 0 : 1);
//                        //工作年限
//                        if(StringUtils.isBlank(arr2[10])){
//                            json.put("status", "error");
//                            json.put("message", "模板中第"+resCount+"行，工作年限不能为空，请检查");
//                            return json.toString();
//                        }
//                        if(arr2[10].trim().length() > 20){
//                            json.put("status", "error");
//                            json.put("message", "模板中第"+resCount+"行，工作年限最长为20个字符，请检查");
//                            return json.toString();
//                        }
//                        personnelTemp.setPersonnelWorkYear(arr2[10].trim());
//                        //毕业院校
//                        if(StringUtils.isBlank(arr2[11])){
//                            json.put("status", "error");
//                            json.put("message", "模板中第"+resCount+"行，毕业院校不能为空，请检查");
//                            return json.toString();
//                        }
//                        if(arr2[11].trim().length() > 50){
//                            json.put("status", "error");
//                            json.put("message", "模板中第"+resCount+"行，毕业院校最长为50个字符，请检查");
//                            return json.toString();
//                        }
//                        personnelTemp.setPersonnelAcademy(arr2[11].trim());
//                        //最高学历
//                        if(StringUtils.isBlank(arr2[12])){
//                            json.put("status", "error");
//                            json.put("message", "模板中第"+resCount+"行，最高学历不能为空，请检查");
//                            return json.toString();
//                        }
//                        if(arr2[12].trim().length() > 10){
//                            json.put("status", "error");
//                            json.put("message", "模板中第"+resCount+"行，最高学历最长为10个字符，请检查");
//                            return json.toString();
//                        }
//                        personnelTemp.setPersonnelHighestEducation(arr2[12].trim());
//                        //是否驻场(0 是 1 否)
//                        personnelTemp.setPersonnelIfHomecourt(StringUtils.isNotBlank(arr2[13]) &&  OperationEnum.workLoadTrue.getLabel().equals(arr2[13]) ? 0 : 1);
//                        //是否办理工作证   0 是 1 否
//                        personnelTemp.setPersonnelIfWorkcard(StringUtils.isNotBlank(arr2[14]) &&  OperationEnum.workLoadTrue.getLabel().equals(arr2[14]) ? 0 : 1);
//                        //是否办理门禁卡 0 是 1 否
//                        personnelTemp.setPersonnelIfEntrance(StringUtils.isNotBlank(arr2[15]) &&  OperationEnum.workLoadTrue.getLabel().equals(arr2[15]) ? 0 : 1);
//                        //特殊说明
//                        if(StringUtils.isNotBlank(arr2[16])){
//                            if(arr2[16].trim().length() >300){
//                                json.put("status", "error");
//                                json.put("message", "模板中第"+resCount+"行，特殊说明最多为300个字符，请检查");
//                                return json.toString();
//                            }else{
//                                personnelTemp.setPersonnelRemarks(arr2[16].trim());
//                            }
//                        }
//                        //循环添加
//                        personnelList.add(personnelTemp);
//                    }
//                }
//                json.put("personnelList", personnelList);
//                request.getSession().setAttribute(String.valueOf(Common.getUserId(request.getSession())), personnelList);
//            }
//            //判断是否有重复手机号和姓名
//            Set personList = new HashSet();
//            for(String namephone : listPersons){
//                personList.add(namephone);
//            }
//            if(personList.size() != personnelList.size()){
//                json.put("status", "error");
//                json.put("message", "有相同的姓名和手机号，请检查");
//                return json.toString();
//            }
//            json.put("status", "ok");
//        } catch (Exception e) {
//            json.put("status", "error");
//            json.put("message", e.getMessage());
//            log.error(e.getMessage(), e);
//        }
//        return json.toString();
//    }
//
//    /**
//     * 返回session中excel中的列表数据
//     * @param request
//     * @return
//     */
//    @RequestMapping(value="/back-excel-list",produces={"text/html;charset=UTF-8"},method= RequestMethod.POST)
//    @ResponseBody
//    public 	Result<Object> backExcelList(HttpServletRequest request){
//        Result<Object> r = new Result<>();
//        List list = (List)request.getSession().getAttribute(String.valueOf(Common.getUserId(request.getSession())));
//        request.getSession().removeAttribute(String.valueOf(Common.getUserId(request.getSession())));
//        r.setBeans(list);
//        r.setReturnCode("200");
//        return r;
//    }
//
//    //通用下载
//    @RequestMapping(value = "/download/{filename:.+}", method = RequestMethod.GET)
//    @ResponseBody
//    public void serveFile(@PathVariable("filename") String filename, HttpServletResponse response,HttpServletRequest request) throws GeneralException {
//        Storage storage = StorageFactory.getStorage(config.getStorageClass());
//        if (storage == null) {
//            return;
//        }
//        Resource file = storage.loadAsResource(filename);
//        String newFileName = null != file.getFilename() ? file.getFilename() : filename;
//        String agent = request.getHeader("User-Agent");
//        try {
//            //针对IE或者以IE为内核的浏览器：
//            if (agent.contains("MSIE")||agent.contains("Trident")) {
//                String  enableFileName = java.net.URLEncoder.encode(newFileName, "UTF-8");
//                response.setHeader("Content-Disposition", "attachment; filename=\"" + enableFileName + "\"");
//            }else {
//                //非IE浏览器的处理：
//                String  enableFileName = new String(newFileName.getBytes("UTF-8"),"ISO-8859-1");
//                response.setHeader("Content-Disposition", "attachment; filename=\"" + enableFileName + "\"");
//            }
//        } catch (UnsupportedEncodingException e) {
//            log.error(e.getMessage(), e);
//        }
//        try (InputStream inputStream = file.getInputStream()) {
//            response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
//            try (OutputStream outputStream = response.getOutputStream()) {
//                IOUtils.copy(inputStream, outputStream);
//                outputStream.flush();
//            }
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        }
//    }
//    /**
//     * 工作量明细导入
//     * 项目负责人/超级管理员/总部工时管理员/分公司工时管理员/分公司领导
//     * 如果是超级管理员或是总部工时管理员查询所有
//     * 如果是分公司领导 分公司工时管理员 那么查询分公司的工作量数据
//     * 项目负责人 查询项目负责人所负责项目<子项目父项目都需要查询出来>的工作量数据
//     * @param request
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    @RequestMapping(value="/upload-import-workload-order", produces={"text/html;charset=UTF-8"}, method= RequestMethod.POST)
//    @ResponseBody
//    public String importWorkloadExcel(HttpServletRequest request){
//        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
//        //读取导入excel
//        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
//        JSONObject json = new JSONObject();
//        Map<String,Map<String,Object>> workOrderMap = new LinkedHashMap<>();
//        List<String> listStr = new ArrayList<>();
//        Set<String> set = new HashSet<>();
//        try {
//            //校验文件类型
//            String rsMessage = checkExcelFileTypes(files);
//            if(!StringUtils.isEmpty(rsMessage)){
//                json.put("status", "error");
//                json.put("message", rsMessage);
//                return json.toString();
//            }
//            int resCount = 0 ;
//            for (int i = 0; i < files.size(); ++i) {
//                MultipartFile file = files.get(i);
//                if (file.isEmpty()) {
//                    json.put("status", "error");
//                    json.put("message", "对不起文件内容不能为空");
//                    return json.toString();
//                }
//                List<String[][]> list = ExcelUtil.parseExcelData(file.getOriginalFilename(), file.getInputStream(), 1);
//                if(ObjectUtils.isNull(list) || list.isEmpty()){
//                    json.put("status", "error");
//                    json.put("message", "对不起，工作量信息不能为空，请检查");
//                    return json.toString();
//                }
//                for (String[][] s : list) {
//                    for (int j = 0; j < s.length; j++) {
//                        ++resCount;
//                        String[] arr2 = s[j];
//                        String workOrderTitls= ObjectUtils.trimSpace(arr2[0])+ObjectUtils.trimSpace(arr2[7].trim())+ObjectUtils.trimSpace(arr2[8]);
//                        // 判断是否存在数据库 同一周期
//                        Map<String,Object> paramMap = new HashMap<>();
//                        paramMap.put("userPhone",ObjectUtils.trimSpace(arr2[8]));
//                        paramMap.put("workOrderTitle",ObjectUtils.trimSpace(arr2[0]));
//                        paramMap.put("userName",ObjectUtils.trimSpace(arr2[7].trim()));
//                        Integer resultInt = workloadSV.getWrokLoadByMap(paramMap);
//                        if(pattern.matcher(ObjectUtils.trimSpace(arr2[11])).matches() && pattern.matcher(ObjectUtils.trimSpace(arr2[12])).matches()){
//                            if(resultInt > 0){
//                                json.put("status", "error");
//                                json.put("message","第"+resCount+"条工作量在数据库中已存在，请检查");
//                                return json.toString();
//                            }
//                            //工作量为正数
//                            listStr.add(workOrderTitls);
//                            set.add(workOrderTitls);
//                        }
//
//                        StringBuilder paramCheckedinfo = new StringBuilder();
//                        //必填字段空字符校验
//                        paramCheckedinfo = CheckWorkInfo(arr2, pattern,paramCheckedinfo);
//                        if(paramCheckedinfo.length() > 0){
//                            json.put("status", "error");
//                            json.put("message", "第【" + resCount + "】行：" + paramCheckedinfo);
//                            return json.toString();
//                        }
//
//                        //工作量上报业务逻辑判断
//                        Map<String,Object> resultMap = CheckTotalWorkInfo(arr2,paramCheckedinfo,request);
//                        paramCheckedinfo = (StringBuilder)resultMap.get("paramCheckedinfo");
//                        if(paramCheckedinfo.length() > 0){
//                            json.put("status", "error");
//                            json.put("message", "第【" + resCount + "】行：" + paramCheckedinfo);
//                            return json.toString();
//                        }
//                        //判断是否符合模板要求
//                        if(paramCheckedinfo.length() > 0){
//                            json.put("status", "error");
//                            json.put("message", "第【" + resCount + "】行：" + paramCheckedinfo);
//                            return json.toString();
//                        }
//
//                        Map<String,Object> pMap2 = new HashMap();
//                        pMap2.put("createTitle",ObjectUtils.trimSpace(arr2[0]));
//                        pMap2.put("workOrderCreateUserName", ObjectUtils.trimSpace(arr2[3]));
//                        pMap2.put("projectName",ObjectUtils.trimSpace(arr2[5]));
//                        List<Map<String,Object>>  plist = workOrdeSV.queryWorkOrderByMap(pMap2);
//                        //判断已归档的工单中是否有相同的周期，相同的上报人，相同的项目名称
//                        // plist为空需要新建工单
//                        User createUser = (User)resultMap.get("createUser");  //上报人
//                        String projectGroupId = resultMap.get("projectGroupId")+""; //公司id
//                        List<User> userList = (List<User>)resultMap.get("userList");  //用户列表
//                        List<Map<String, Object>> proList =  (List<Map<String, Object>>)resultMap.get("proList");
//                        Map<String,Object> m2 = new HashMap<>();
//                        //工单形成规则(同一个周期  同一个上报人 同一个项目 生成同一个工单)
//                        if(!workOrderMap.containsKey(ObjectUtils.trimSpace(arr2[0]) + ObjectUtils.trimSpace(arr2[3]) + ObjectUtils.trimSpace(arr2[5]))){
//                            if(plist.isEmpty()){
//                                if(userList.size() == 1){
//                                    createUser = userList.get(0); //上报人
//                                }
//                                //设置工单的值
//                                WorkOrder workOrder = saveWorkOrder(arr2,createUser,projectGroupId,proList);
//                                Map<String, Object> map = new HashMap<>();
//                                map.put("workOrder", workOrder);
//                                workOrderMap.put(ObjectUtils.trimSpace(arr2[0]) +  ObjectUtils.trimSpace(arr2[3]) + ObjectUtils.trimSpace(arr2[5]), map);
//                            }else{//合并旧工单
//                                m2 = plist.get(0);
//                                Integer  workOrderId = (Integer) m2.get("workOrderId");
//                                WorkOrder workOrder =  workOrdeSV.queryByWorkOrderByWorkOrderId(workOrderId);
//                                Map<String, Object> map = new HashMap<>();
//                                map.put("workOrder", workOrder);
//                                workOrderMap.put(ObjectUtils.trimSpace(arr2[0]) +  ObjectUtils.trimSpace(arr2[3]) + ObjectUtils.trimSpace(arr2[5]), map);
//                            }
//                        }
//                        //设置订单详情值
//                        Workload workload = saveWorkload(arr2);
//                        Map<String, Object> map = workOrderMap.get(ObjectUtils.trimSpace(arr2[0]) +  ObjectUtils.trimSpace(arr2[3]) + ObjectUtils.trimSpace(arr2[5]));
//                        //设置工作量操作日志表的值
//                        WorkOrder orders = (WorkOrder)map.get("workOrder");
//                        Map<String,Object> workLoadLog = saveWorkLoadLog(arr2,workload,m2,request,orders);
//
//                        if(!map.containsKey("workloadList")){
//                            List<Workload> workloadList = new ArrayList<>();
//                            workloadList.add(workload);
//                            map.put("workloadList", workloadList);
//
//                            List<Map<String,Object>> workloadLogList = new ArrayList<>();
//                            workloadLogList.add(workLoadLog);
//                            map.put("workloadLogList", workloadLogList);
//                        }else{
//                            List<Workload> workloadList = new ArrayList();
//                            workloadList.addAll((ArrayList<Workload>)map.get("workloadList"));
//                            workloadList.add(workload);
//                            map.put("workloadList", workloadList);
//
//                            List<Map<String,Object>> workloadLogList = new ArrayList<>();
//                            workloadLogList.addAll((ArrayList<Map<String,Object>>)map.get("workloadLogList"));
//                            workloadLogList.add(workLoadLog);
//                            map.put("workloadLogList", workloadLogList);
//                        }
//                    }
//                }
//
//                if(listStr.size() != set.size()){
//                    json.put("status", "error");
//                    json.put("message", "同一周期内同一个人只能存在一条工作量数据");
//                    return json.toString();
//                }
//                //总工时计算
//                for(Map.Entry<String, Map<String, Object>> entry : workOrderMap.entrySet()){
//                    List<Workload> workloadList = new ArrayList();
//                    workloadList.addAll((ArrayList<Workload>) entry.getValue().get("workloadList"));
//                    float workOrderTotalHours = 0F;
//                    for (Workload workload : workloadList) {
//                        workOrderTotalHours += Float.parseFloat(workload.getTotalHours());
//                    }
//                    if(String.valueOf(workOrderTotalHours).length() > 11){
//                        WorkOrder order = ((WorkOrder) entry.getValue().get("workOrder"));
//                        json.put("status", "error");
//                        json.put("message", "【" + order.getWorkOrderCreateUserName() + "】上报工单" + order.getWorkOrderTitle() + "总工时字符长度大于11，请检查");
//                        return json.toString();
//                    }
//                    ((WorkOrder) entry.getValue().get("workOrder")).setWorkOrderTotalHours(String.valueOf(workOrderTotalHours));
//                    ((WorkOrder) entry.getValue().get("workOrder")).setWorkOrderTotalPerson(workloadList.size());// 投入人员数
//                }
//                workOrdeSV.importWorkOrder(workOrderMap);
//            }
//            json.put("status", "ok");
//            json.put("message", "成功导入" + resCount + "条工单数据.");
//        } catch (Exception e) {
//            json.put("status", "error");
//            json.put("message", "导入失败，失败原因：模板格式不对或者模板内容错误 请检查！");
//            log.error(e.getMessage(), e);
//        }
//        return json.toString();
//    }
//
//    /**
//     * 工作量明细设置工单
//     * @param arr2
//     * @return
//     */
//    private WorkOrder saveWorkOrder(String[] arr2,User createUser,String projectGroupId,List<Map<String,Object>> proList){
//        WorkOrder workOrder = new WorkOrder();
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            workOrder.setWorkOrderTitle(ObjectUtils.OperationStr(ObjectUtils.trimSpace(arr2[0])));
//            workOrder.setWorkOrderStartDate(sdf.parse(arr2[1]));
//            workOrder.setWorkOrderEndDate(sdf.parse(arr2[2]));
//            workOrder.setWorkOrderCreateUser(createUser.getUserId());
//            workOrder.setWorkOrderCreateUserName(ObjectUtils.OperationStr( ObjectUtils.trimSpace(arr2[3])));
//            workOrder.setWorkOrderCreateDate(new Date());
//            workOrder.setProjectGroup(projectGroupId);
//            workOrder.setProjectId((Integer) (proList.get(0).get("projectId")));
//            workOrder.setTechnicalManager(ObjectUtils.OperationStr(arr2[17]));
//            workOrder.setProjectManager(ObjectUtils.OperationStr(arr2[18]));
//            workOrder.setOrderSerialId("ITZC" + System.currentTimeMillis());
//            workOrder.setProjectHeaderOffice("1");
//            workOrder.setWorkOrderIsDelete(1);
//            workOrder.setWorkOrderState(7);
//            workOrder.setWorkOrderKind(1);
//        }catch (Exception e){
//            log.error(e.getMessage(), e);
//        }
//        return workOrder;
//    }
//
//    /**
//     * 工作量明细设置工单
//     * @param arr2
//     * @return
//     */
//    private Workload saveWorkload(String[] arr2){
//        float hjgs = Float.parseFloat(ObjectUtils.trimSpace(arr2[11]));
//        float jbgs = Float.parseFloat(ObjectUtils.trimSpace(arr2[12]));
//        float zgs = Float.parseFloat(ObjectUtils.trimSpace(arr2[14]));
//        String fhjgs = String .format("%.4f",hjgs);
//        String fjbgs = String .format("%.4f",jbgs);
//        String fzgs = String .format("%.4f",zgs);
//
//        Workload workload = new Workload();
//        workload.setUserName(ObjectUtils.OperationStr(ObjectUtils.trimSpace(arr2[7])));
//        workload.setUserPhone(ObjectUtils.OperationStr(ObjectUtils.trimSpace(arr2[8])));
//        workload.setWorkContent(ObjectUtils.OperationStr(ObjectUtils.trimSpace(arr2[10])));
//        workload.setNormalHours(fhjgs);
//        workload.setOvertimeHours(fjbgs);
//        workload.setWorkGrade(ObjectUtils.OperationStr(ObjectUtils.trimSpace(arr2[9])));
//        workload.setOvertimeRemarks(ObjectUtils.OperationStr(arr2[15]));
//        workload.setWorkDegree(ObjectUtils.OperationStr(ObjectUtils.trimSpace(arr2[13]))); //满意度系数
//        workload.setTotalHours(fzgs);
//        return workload;
//    }
//
//    /**
//     * 设置工作量明细操作日志的值
//     * @param
//     * @return
//     */
//    private Map<String, Object> saveWorkLoadLog(String[] arr2,Workload workload, Map<String,Object> m2,
//                                                HttpServletRequest request,WorkOrder orders) {
//        String batchNumber = "batch" +System.currentTimeMillis();
//        User userInfo = Common.getUser(request.getSession());
//        //添加日志表
//        Map<String, Object> workLoadLog = new HashMap<>();
//        workLoadLog.put("userName", workload.getUserName());
//        workLoadLog.put("userPhone", workload.getUserPhone());
//        workLoadLog.put("workContent", workload.getWorkContent());
//        workLoadLog.put("normalHours", workload.getNormalHours());
//        workLoadLog.put("overtimeHours", workload.getOvertimeHours());
//        workLoadLog.put("totalHours", workload.getTotalHours());
//        workLoadLog.put("overtimeRemarks", workload.getOvertimeRemarks());
//        //合并工单时，操作日志
//        workLoadLog.put("workOrderId", m2.get("workOrderId"));
//        workLoadLog.put("workloadId", m2.get("workOrderId"));
//        workLoadLog.put("handleType", 1); //操作类型 1 代表添加
//        workLoadLog.put("createUser", userInfo.getUserId());
//        workLoadLog.put("createUserName", userInfo.getRealName());
//        workLoadLog.put("workOrderTitle", orders.getWorkOrderTitle());
//        workLoadLog.put("workOrderStartDate", orders.getWorkOrderStartDate());
//        workLoadLog.put("workOrderEndDate", orders.getWorkOrderEndDate());
//        workLoadLog.put("workOrderCreateUserName", orders.getWorkOrderCreateUserName());
//        workLoadLog.put("workOrderCreateUser", orders.getWorkOrderCreateUser());
//        workLoadLog.put("projectManager", orders.getProjectManager());
//        workLoadLog.put("technicalManager", orders.getTechnicalManager());
//        workLoadLog.put("projectName", ObjectUtils.trimSpace(arr2[5]));
//        workLoadLog.put("batchNumber", batchNumber);
//        workLoadLog.put("factoryName", ObjectUtils.trimSpace(arr2[4]));
//        workLoadLog.put("workDegree", ObjectUtils.OperationStr(arr2[13]));
//        workLoadLog.put("workGrade", ObjectUtils.trimSpace(arr2[9]) == null ? "" : ObjectUtils.trimSpace(arr2[9]));
//        return workLoadLog;
//    }
//    /**
//     * 工作量明细导入基础验证
//     * @param arr2
//     * @return
//     */
//    private StringBuilder CheckWorkInfo(String[] arr2, Pattern pattern,StringBuilder paramCheckedinfo){
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            //必填字段空字符校验
//            if(StringUtils.isBlank(arr2[0]) ){
//                paramCheckedinfo.append("周期不能为空；" );
//            }
//            Date startDate = sdf.parse(arr2[1]);
//            Date endDate = sdf.parse(arr2[2]);
//            if(startDate.getTime() > endDate.getTime()){
//                paramCheckedinfo.append("上报周期结束日期必须大于开始日期！" );
//            }
//            if(arr2[0].length()>30){
//                paramCheckedinfo.append("周期最长为30个字符；" );
//            }
//            if(StringUtils.isBlank(arr2[1]) ){
//                paramCheckedinfo.append("上报周期开始日期不能为空；" );
//            }
//            if(StringUtils.isBlank(arr2[2]) ){
//                paramCheckedinfo.append("上报周期结束日期不能为空；" );
//            }
//            if(StringUtils.isBlank(arr2[3])){
//                paramCheckedinfo.append("上报人不能为空；" );
//            }
//            if(StringUtils.isBlank(arr2[4]) ){
//                paramCheckedinfo.append("厂商不能为空；" );
//            }
//            if(StringUtils.isBlank(arr2[5])){
//                paramCheckedinfo.append("项目名称不能为空；" );
//            }
//            if(StringUtils.isBlank(arr2[6])){
//                paramCheckedinfo.append("所属部门不能为空；" );
//            }
//            if(StringUtils.isBlank(arr2[7]) || ObjectUtils.trimSpace(arr2[7]).length() > 30){
//                paramCheckedinfo.append("姓名不能为空,且字符长度最大为30；" );
//            }
//            if(StringUtils.isBlank(arr2[8])  || ObjectUtils.trimSpace(arr2[8]).length() > 11){
//                paramCheckedinfo.append("手机号码最大为11；" );
//            }
//            if(StringUtils.isBlank(arr2[10])){
//                paramCheckedinfo.append("工作内容不能为空；" );
//            }
//            if(StringUtils.isBlank(arr2[11])
//                    || ObjectUtils.trimSpace(arr2[11]).length() > 10){
//                paramCheckedinfo.append("正常工时不能为空" );
//            }
//            if(!StringUtils.isBlank(arr2[11]) && ObjectUtils.trimSpace(arr2[11]).length() > 10){
//                paramCheckedinfo.append("正常工时字符长度最大为10；" );
//            }
//            if(StringUtils.isBlank(arr2[12])
//                    || ObjectUtils.trimSpace(arr2[12]).length() > 10){
//                paramCheckedinfo.append("加班工时不能为空" );
//            }
//            if(!StringUtils.isBlank(arr2[12]) && ObjectUtils.trimSpace(arr2[12]).length() > 10){
//                paramCheckedinfo.append("加班工时字符长度最大为10；" );
//            }
//            //满意度系数
//            if(!StringUtils.isBlank(arr2[13]) && ObjectUtils.trimSpace(arr2[13]).length() > 10){
//                paramCheckedinfo.append("满意度系数字符长度最大为10；" );
//            }
//            //|| !pattern.matcher(ObjectUtils.trimSpace(arr2[14])).matches()
//            if(StringUtils.isBlank(arr2[14]) ){
//                paramCheckedinfo.append("合计工时不能为空,且不能为其他字符；" );
//            }
//            if(!StringUtils.isBlank(arr2[15]) && ObjectUtils.trimSpace(arr2[15]).length() > 2000){
//                paramCheckedinfo.append("加班备注符长度最大为2000；" );
//            }
//            if(!StringUtils.isBlank(arr2[17]) && ObjectUtils.trimSpace(arr2[17]).length() > 20){
//                paramCheckedinfo.append("技术经理最大长度20；" );
//            }
//            if(!StringUtils.isBlank(arr2[18]) && ObjectUtils.trimSpace(arr2[18]).length() > 20){
//                paramCheckedinfo.append("产品经理最大长度20；" );
//            }
//        } catch (ParseException e) {
//            log.error("日期格式不正确",e);
//        }
//        return paramCheckedinfo;
//    }
//    /**
//     * 工作量明细导入业务逻辑判断
//     * @param arr2
//     * @return
//     */
//    private Map<String,Object> CheckTotalWorkInfo(String[] arr2,StringBuilder paramCheckedinfo,HttpServletRequest request) {
//        //得到发布人信息
//        List<User> userList = userSV.getUserListByRealName(ObjectUtils.trimSpace(arr2[3]));
//        //得到厂商信息
//        Company createUserCompany = companySV.getCompanyByCompanyName(ObjectUtils.trimSpace(arr2[4]));
//        //获取项目信息
//        List<Map<String, Object>> proList = projectSV.getProjectByName(ObjectUtils.trimSpace(arr2[5]));
//        User createUser = new User(); //上报人
//
//        Map<String,Object> resultMap = new HashMap<>();
//        //判断总工时
//        Float normalHour = Float.parseFloat(ObjectUtils.trimSpace(arr2[11]));
//        Float overHour = Float.parseFloat(ObjectUtils.trimSpace(arr2[12]));
//        String xss = ObjectUtils.trimSpace(arr2[13]);
//        if (StringUtils.isBlank(xss)) { // 系数不填，默认为1
//            xss = "1";
//        }
//        Float xs = Float.parseFloat(xss);
//        Float totalHour = Float.parseFloat(ObjectUtils.trimSpace(arr2[14]));
//        // 合计工时=（正常+加班）*满意度系数
//        totalHour = totalHour * 10000;
//        int th = Math.round(totalHour); // 转int
//        Float js = (normalHour + overHour) * 10000;
//        int jsh = Math.round(js);// 转int后
//        if (th != (jsh * xs)) {
//            paramCheckedinfo.append("合计工时有误，请核对后导入正确数据");
//        }
//
//        //判断项目的所属厂商(判断数据库中该项目是否和所属厂商一致)
//        if(!ObjectUtils.isNull(createUserCompany)){
//            List<Map<String,Object>> listP = projectSV.getProjectByCooperativeId(Integer.valueOf(createUserCompany.getDepartmentId()));
//            int intval = 0;
//            for(Map pMap : listP){
//                if(pMap.get("projectName").equals(ObjectUtils.trimSpace(arr2[5]))){
//                    ++intval;
//                }
//            }
//            if(intval == 0){
//                paramCheckedinfo.append("名称为【"+ObjectUtils.trimSpace(arr2[4])+"】的厂商不存在项目【"+ObjectUtils.trimSpace(arr2[5])+"】；");
//            }
//        }
//
//        if(userList.isEmpty()){
//            paramCheckedinfo.append("上报人【" +  ObjectUtils.trimSpace(arr2[3]) + "】不存在；");
//        }
//        if(ObjectUtils.isNull(createUserCompany) || ObjectUtils.isNull(createUserCompany.getCompanyId())){
//            paramCheckedinfo.append("名称为【"+ObjectUtils.trimSpace(arr2[4])+"】的厂商不存在；");
//        }
//        if(proList.isEmpty() || ObjectUtils.isNull(proList.get(0).get("projectId"))){
//            paramCheckedinfo.append("名称为【"+ObjectUtils.trimSpace(arr2[5])+"】的项目不存在；");
//        }
//        //根据上报人查询多条用户信息，根据厂商定位上报人信息
//        if(!userList.isEmpty() && !ObjectUtils.isNull(createUserCompany)){
//            for(User userTemp : userList){
//                if(createUserCompany.getCompanyId().equals(userTemp.getCompanyId())){
//                    createUser = userTemp;
//                    break;
//                }
//            }
//            if(ObjectUtils.isNull(createUser.getUserId())){
//                paramCheckedinfo.append("名称为【"+ObjectUtils.trimSpace(arr2[4])+"】的厂商不存在用户为【"+ ObjectUtils.trimSpace(arr2[3])+"】的上报人；");
//            }
//        }
//        String projectGroupId = null;
//        //判断项目是否在所属部门中(项目的所属部门是否与数据库一致)
//        if(!proList.isEmpty()){
//            Map mapPro = (Map)proList.get(0);
//            projectGroupId = mapPro.get("projectGroup").toString();
//            if(!mapPro.get("projectGroupName").toString().equals(ObjectUtils.trimSpace(arr2[6]))){
//                paramCheckedinfo.append("名称为【"+ObjectUtils.trimSpace(arr2[5])+"】的项目所属部门不存在，请检查；");
//            }
//        }
//
//        Map<String, Object> maps = new HashMap();
//        //判断权限
//        Map<String, Object> parMap = authorityJudgmentWorkload(request, maps, null);
//        parMap.put("projectGroup", parMap.get("banchDepartmentId") == null ? "" : parMap.get("banchDepartmentId"));
//        List<Map<String, Object>> listProject = projectSV.getProjectsByMap(parMap);
//        //判断是否有权限上报
//        boolean isSuper = Common.isSuperNameOrRole(request);
//        if (!listProject.isEmpty() && !isSuper) {
//            int countProject = 0;
//            for (Map<String, Object> mapPros : listProject) {
//                if (mapPros.get("projectName").equals(ObjectUtils.trimSpace(arr2[5]))) {
//                    ++countProject;
//                }
//            }
//            if (countProject < 1) {
//                paramCheckedinfo.append("您没有权限上报项目为【" + ObjectUtils.trimSpace(arr2[5]) + "】的工作量；");
//            }
//        }
//        resultMap.put("paramCheckedinfo",paramCheckedinfo);
//        resultMap.put("createUser",createUser);
//        resultMap.put("projectGroupId",projectGroupId);
//        resultMap.put("userList",userList);
//        resultMap.put("proList",proList);
//        return resultMap;
//    }
//
//    /**
//     * 循环获取当前登录用户 分公司ID
//     */
//    private String loopBranchCompanyId(Company currentCompanys){
//        Company gsfgsCompany = companySV.getCompanyByCorrelationId(Common.getBRANCH_OFFICE_CORRELATION_ID());
//        if(currentCompanys.getParentId().equals(gsfgsCompany.getDepartmentId())){
//            return currentCompanys.getDepartmentId();
//        }
//        Integer parentId = Integer.parseInt(currentCompanys.getParentId());
//        for(int i = 0 ; i < 20 ; i ++){
//            Company currentCompany = companySV.getCompanyById(parentId);
//            if(currentCompany == null){
//                return null;
//            }
//            if(currentCompany.getParentId().equals(gsfgsCompany.getDepartmentId())){
//                return currentCompany.getDepartmentId();
//            }
//            parentId = Integer.parseInt(currentCompany.getParentId());
//        }
//        return null;
//    }
//
//
//    /**
//     * 判断工作量明细导入权限
//     * @param request
//     * @param paramMap
//     * @param cooperativeId
//     * @return
//     */
//    private Map<String,Object> authorityJudgmentWorkload(HttpServletRequest request, Map<String,Object> paramMap, Integer cooperativeId){
//         //如果是超级管理员或是总部工时管理员查询所有
//        if(Common.isSuperNameOrRole(request) || getTotalAdmin(request)){
//            //这里不用传任何条件就是查询所有的工作量数据
//            //如果是分公司领导 分公司工时管理员 那么查询分公司的工作量数据
//        }else if(getBanchLeader(request) || getBanchAdmin(request)){
//            String banchDepartmentId = loopBranchCompanyId(Common.getCompany(request.getSession()));
//            if(banchDepartmentId != null){
//                paramMap.put("banchDepartmentId", banchDepartmentId);
//            }
//            //下面的两个判断是判断  项目负责人或者是判断  普通厂商用户
//        }else {
//            List<Integer> projectLists = new ArrayList<>();
//            //查询用户负责的项目列表
//            List<Map<String,Object>> userProjectList = projectSV.getProjectByUserId(Common.getUserId(request.getSession()));
//            for(Map<String,Object> mmm : userProjectList){
//                projectLists.add(Integer.parseInt(mmm.get("projectId").toString()));
//                Integer projectLeaderId = ObjectUtils.parseInt(mmm.containsKey("projectLeaderId") ? mmm.get("projectLeaderId").toString() : null);
//                if(projectLeaderId == null){
//                    continue;
//                }
//            }
//            if(!projectLists.isEmpty()){
//                List<Map<String,Object>> childProjectList = projectSV.getProjectByParentId(projectLists);
//                for(Map<String,Object> mmm : childProjectList){
//                    Integer projectLeaderId = ObjectUtils.parseInt(mmm.containsKey("projectLeaderId") ? mmm.get("projectLeaderId").toString() : null);
//                    if(projectLeaderId == null){
//                        continue;
//                    }
//                    projectLists.add(Integer.parseInt(mmm.get("projectId").toString()));
//                }
//            }
//
//            if(!projectLists.isEmpty()){//项目负责人 查询项目负责人所负责项目<子项目父项目都需要查询出来>的工作量数据
//                //项目id集合 中间，隔开
//                paramMap.put("projectListIds", projectLists);
//            }
//            if(projectLists.isEmpty()){
//                //判断当前用户是否是普通厂商
//                int createCompanyType = userSV.getUserCompanyType(Common.getUserId(request.getSession()), Common.getCOMPANY_ID(), Common.getBRANCH_OFFICE_CORRELATION_ID(), Common.getMANUFACTURER_ID_2(), Common.getMANUFACTURER_ID());
//                if(createCompanyType == 2 || createCompanyType == 3){//当前是厂商用户登录 让他查询本厂商的
//                    paramMap.put("cooperativeId", Common.getCompany(request.getSession()).getDepartmentId());
//                }else {
//                    paramMap.put("cooperativeId", -1);
//                }
//            }
//        }
//        if(!ObjectUtils.isNull(cooperativeId) && !paramMap.containsKey("cooperativeId")){
//            paramMap.put("cooperativeId", cooperativeId);
//        }
//        return paramMap;
//    }
//
//    /**
//     * 单项合同的导入
//     * @param request
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    @RequestMapping(value="/upload-import-order", produces={"text/html;charset=UTF-8"}, method= RequestMethod.POST)
//    @ResponseBody
//    public String importProjectCompactExcel(HttpServletRequest request){
////        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
//        //读取导入excel
//        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
//        User currUser = Common.getUser(request.getSession());
//        JSONObject json = new JSONObject();
//        try {
//            //校验文件类型
//            String rsMessage = checkExcelFileTypes(files);
//            if(!StringUtils.isEmpty(rsMessage)){
//                json.put("status", "error");
//                json.put("message", rsMessage);
//                return json.toString();
//            }
//            int resCount = 0 ;
//            List<ProjectCompact> projectCompactList = new ArrayList<>();
//            Set<String> set = new HashSet<>();
//            for (int i = 0; i < files.size(); ++i) {
//                MultipartFile file = files.get(i);
//                if (file.isEmpty()) {
//                    json.put("status", "error");
//                    json.put("message", "对不起文件内容不能为空");
//                    return json.toString();
//                }
//                List<String[][]> list = ExcelUtil.parseExcelData(file.getOriginalFilename(), file.getInputStream(), 1);
//                if(ObjectUtils.isNull(list) || list.isEmpty()){
//                    json.put("status", "error");
//                    json.put("message", "对不起，合同管理不能为空，请检查");
//                    return json.toString();
//                }
//                for (String[][] s : list) {
//                    for (int j = 0; j < s.length; j++) {
//                        ++resCount;
//                        String[] arr2 = s[j];
//                        StringBuilder paramCheckedinfo = new StringBuilder();
//                        //必填字段空字符校验
//                        if(ObjectUtils.isNull(ObjectUtils.trimSpace(arr2[0])) || ObjectUtils.trimSpace(arr2[0]).length() > 30){
//                            paramCheckedinfo.append("合同编号不能为空,且字符长度最大为30;" );
//                        }
//                        if(!ObjectUtils.isNull(ObjectUtils.trimSpace(arr2[0]))){
//                            //单项合同判重，编号唯一
//                            Map<String,Object> pMap = new HashMap<>();
//                            pMap.put("compactNumber",ObjectUtils.trimSpace(arr2[0]));
//                            List<Map<String,Object>> listCompact = projectCompactSV.selectListByMap(pMap);
//                            if(!listCompact.isEmpty()){
//                                paramCheckedinfo.append("合同编号数据库中已存在，请重新输入;" );
//                            }
//                        }
//                        set.add(ObjectUtils.trimSpace(arr2[0]));
//                        if(ObjectUtils.isNull(arr2[1]) || arr2[1].length() > 100){
//                            paramCheckedinfo.append("合同名称不能为空,且字符长度最大为100;" );
//                        }
//                        if(ObjectUtils.isNull(arr2[2]) || arr2[2].length() > 200){
//                            paramCheckedinfo.append("立项项目编号不能为空,且字符长度最大为200;" );
//                        }
//                        if(ObjectUtils.isNull(arr2[3]) || arr2[3].length() > 200){
//                            paramCheckedinfo.append("立项项目名称不能为空,且字符长度最大为200;" );
//                        }
//                        if(ObjectUtils.isNull(arr2[4]) || arr2[4].length() > 15){
//                            paramCheckedinfo.append("合同含税金额不能为空,且长度最大为15;" );
//                        }
//                        if(ObjectUtils.isNull(arr2[5]) || arr2[5].length() > 15){
//                            paramCheckedinfo.append("合同不含税金额不能为空,且长度最大为15;" );
//                        }
//                        if(ObjectUtils.isNull(arr2[6]) || arr2[6].length() > 200){
//                            paramCheckedinfo.append("税率不能为空,且字符长度最大为200;" );
//                        }
//                        if(ObjectUtils.isNull(arr2[7]) || arr2[7].length() > 200){
//                            paramCheckedinfo.append("资金类型不能为空,且必须填写：投资,成本,零购,研发;" );
//                        }
//                        if(ObjectUtils.isNull(arr2[8])){
//                            paramCheckedinfo.append("合同生效时间不能为空;" );
//                        }
//                        if(ObjectUtils.isNull(arr2[9])){
//                            paramCheckedinfo.append("合同有效期不能为空;" );
//                        }
////                        if(ObjectUtils.isNull(arr2[10]) || arr2[10].length() > 200){
////                            paramCheckedinfo.append("所属部门/分公司不能为空,且字符长度最大为200;" );
////                        }
//                        if(ObjectUtils.isNull(arr2[11]) || arr2[11].length() > 200){
//                            paramCheckedinfo.append("承办部门不能为空,且字符长度最大为200;" );
//                        }
//                        if(ObjectUtils.isNull(arr2[12]) || arr2[12].length() > 200){
//                            paramCheckedinfo.append("供应商不能为空,且字符长度最大为200;" );
//                        }
//                        if(ObjectUtils.isNull(arr2[13]) || arr2[13].length() > 200){
//                            paramCheckedinfo.append("执行人姓名不能为空,且字符长度最大为200;" );
//                        }
//                        if(paramCheckedinfo.length() > 0){
//                            json.put("status", "error");
//                            json.put("message", "第【" + resCount + "】行：" + paramCheckedinfo);
//                            return json.toString();
//                        }
//
//
//                        //相关数据ID查询校验
//                        paramCheckedinfo = new StringBuilder();
//                        String executeUserName = arr2[13].trim();  //执行人
//                        String compactCompanyName = arr2[12].trim(); //供应商名称
////                        String currDept = arr2[10].trim(); // 所属部门
//                        String doDept = arr2[11].trim(); //承办部门
//                        String compactPaymentMoney = arr2[4].trim();
//                        String compactRealityMoney = arr2[5].trim();
//                        //查询供应商
//                        Company company = companySV.getCompanyByCompanyName(compactCompanyName);
//                        //查询所属部门
////                        Company currCompany = companySV.getCompanyByCompanyName(currDept);
//                        //查询承办部门
//                        Company doCompany = companySV.getCompanyByCompanyName(doDept);
//                        //执行人
//                        User user = userSV.getUserByRealName(executeUserName);
//                        //判断金额
//                        if(compactPaymentMoney.indexOf(".") != -1){
//                            String last1 = compactPaymentMoney.substring(compactPaymentMoney.indexOf(".") + 1);
//                            if (last1.length() > 2) {
//                                paramCheckedinfo.append("合同含税金额最多保留两位小数；");
//                            }
//                        }
//                        if(compactRealityMoney.indexOf(".") != -1){
//                            String last2 = compactRealityMoney.substring(compactRealityMoney.indexOf(".") + 1);
//                            if (last2.length() > 2) {
//                                paramCheckedinfo.append("合同不含税金额最多保留两位小数；");
//                            }
//                        }
////                        if(ObjectUtils.isNull(currCompany) ||  ObjectUtils.isNull(currCompany.getCompanyId())){
////                            paramCheckedinfo.append("所属部门不存在；");
////                        }
//                        if(ObjectUtils.isNull(doCompany) ||  ObjectUtils.isNull(doCompany.getCompanyId())){
//                            paramCheckedinfo.append("承办部门不存在；");
//                        }
//                        if(ObjectUtils.isNull(company) ||  ObjectUtils.isNull(company.getCompanyId())){
//                            paramCheckedinfo.append("供应商不存在；");
//                        }
//                        if(ObjectUtils.isNull(user) ||  ObjectUtils.isNull(user.getUserId())){
//                            paramCheckedinfo.append("执行人不存在；");
//                        }
//                        if(paramCheckedinfo.length() > 0){
//                            json.put("status", "error");
//                            json.put("message", "第【" + resCount + "】行：" + paramCheckedinfo);
//                            return json.toString();
//                        }
//                        ProjectCompact projectCompact = new ProjectCompact();
//                        projectCompact.setProjectApprovalNumber(ObjectUtils.OperationStr(arr2[2]));
//                        projectCompact.setProjectApprovalName(ObjectUtils.OperationStr(arr2[3]));
//                        projectCompact.setCompactNumber(ObjectUtils.OperationStr(arr2[0]));
//                        projectCompact.setCompactName(ObjectUtils.OperationStr(arr2[1]));
//                        projectCompact.setCompactCompanyId(company.getCompanyId());  //供应商id
//                        projectCompact.setCompactCompanyName(compactCompanyName);
//                        projectCompact.setCompactPaymentMoney(new BigDecimal(compactPaymentMoney));
//                        projectCompact.setCompactRealityMoney(new BigDecimal(compactRealityMoney));
//                        projectCompact.setCompactTaxRate(ObjectUtils.OperationStr(arr2[6]));
//                        String[] split = arr2[7].split(",");
//                        StringBuilder sb = new StringBuilder();
//                        for (String sr : split) {
//                            sb.append(ProjectResourceTypes.getCodeByLabel(sr)).append(",");
//                        }
//                        projectCompact.setCapitalNature(sb.deleteCharAt(sb.length() - 1).toString());
//                        projectCompact.setExecuteUserName(executeUserName);
//
//                        projectCompact.setCompactSignDate(DateUtils.stringToDate(arr2[8], "yyyy-MM-dd"));
//                        projectCompact.setExecuteEndDate(DateUtils.stringToDate(arr2[9], "yyyy-MM-dd"));
//                        projectCompact.setCompactExecuteCompanyName(ObjectUtils.OperationStr(arr2[11]));
//                        projectCompact.setCompactExecuteCompanyId(doCompany.getCompanyId());
//                        projectCompact.setIfDelete(0);
//                        projectCompact.setCreateDate(new Date());
//                        projectCompact.setCreateUser(currUser.getUserId());
//                        projectCompact.setExecuteUserId(user.getUserId());
//                        Company userComp = companySV.getCompanyByUserId(user.getUserId());
//                        projectCompact.setCompactTheCompanyId(userComp.getCompanyId());
//                        projectCompact.setCompactTheCompanyName(getSsbm(userComp.getDepartmentId()));
//
//
//                        projectCompactList.add(projectCompact);
//                    }
//                }
//                if(set.size() != projectCompactList.size()){
//                    json.put("status", "error");
//                    json.put("message", "模板中有相同的合同编号，请检查！");
//                    return json.toString();
//                }
//                projectCompactSV.importProjectCompact(projectCompactList);
//            }
//            json.put("status", "ok");
//            json.put("message", "成功导入" + resCount + "条工单数据.");
//        } catch (Exception e) {
//            json.put("status", "error");
//            json.put("message", "导入失败，失败原因：模板格式不对或者模板内容错误 请检查！");
//            log.error(e.getMessage(), e);
//        }
//        return json.toString();
//    }
//
//    /**
//     * 判断当前用户是否是总部工时管理员
//     * @param request
//     * @return
//     */
//    public boolean getTotalAdmin(HttpServletRequest request){
//        List<Role> listRole = Common.getRoleList(request.getSession());
//        Boolean totalAdmin = false;
//        String totalAdminRoleName = ITRoleType.totalAdmin.getLabel();
//        if(!ObjectUtils.isNull(listRole) && !CollectionUtils.isEmpty(listRole)){
//            for (Role role : listRole) {
//                if(totalAdminRoleName.equals(role.getRoleName())){
//                    totalAdmin = true;
//                    break;
//                }
//            }
//        }
//        return totalAdmin;
//    }
//
//    /**
//     * 判断当前用户是否是分公司工时管理员
//     * @param request
//     * @return
//     */
//    public boolean getBanchAdmin(HttpServletRequest request){
//        List<Role> listRole = Common.getRoleList(request.getSession());
//        Boolean banchAdmin = false;
//        short banchAdminRoleId = ITRoleType.branchAdmin.getShort();
//        if(!ObjectUtils.isNull(listRole) && !CollectionUtils.isEmpty(listRole)){
//            for (Role role : listRole) {
//                if(banchAdminRoleId == role.getRoleId().shortValue()){
//                    banchAdmin = true;
//                    break;
//                }
//            }
//        }
//        return banchAdmin;
//    }
//
//    /**
//     * 判断当前用户是否是分公司领导
//     * @param request
//     * @return
//     */
//    public boolean getBanchLeader(HttpServletRequest request){
//        List<Role> listRole = Common.getRoleList(request.getSession());
//        Boolean banchAdmin = false;
//        short banchAdminRoleId = ITRoleType.branchLeader.getShort();
//        if(!ObjectUtils.isNull(listRole) && !CollectionUtils.isEmpty(listRole)){
//            for (Role role : listRole) {
//                if(banchAdminRoleId == role.getRoleId().shortValue()){
//                    banchAdmin = true;
//                    break;
//                }
//            }
//        }
//        return banchAdmin;
//    }
//
//    /**
//     * 是否是项目负责人
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/check-leader-type", method = RequestMethod.POST)
//    public Result<Object> checkLeaderType(HttpServletRequest request){
//        Result<Object> r = new Result<>();
//        r.setReturnCode("-1");
//        Map<String,Object> paramMap = new HashMap<>();
//        paramMap.put("projectLeaderId",Common.getUserId(request.getSession()));
//        List<Map<String,Object>> listProject = projectSV.getProjectList(paramMap);
//        if(!listProject.isEmpty()) {
//            r.setReturnCode("0");
//            r.setReturnMessage(String.valueOf(listProject.size()));
//        }
//        return r;
//    }
//
//    /**
//     * 框架合同导入
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/compact-frame-import-data", produces = {"text/html;charset=UTF-8"}, method = RequestMethod.POST)
//    @ResponseBody
//    public String importCompactFrameData(HttpServletRequest request) {
//        JSONObject json = new JSONObject();
//        User loginUser = Common.getUser(request.getSession());
//        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
//        try {
//            String rsMessage = checkExcelFileTypes(files);
//            if (!StringUtils.isEmpty(rsMessage)) {
//                json.put("status", "error");
//                json.put("message", rsMessage);
//                return json.toString();
//            }
//            int count = 0;
//            for (MultipartFile file : files) {
//                if (file.isEmpty()) {
//                    json.put("status", "error");
//                    json.put("message", "对不起文件内容不能为空");
//                    return json.toString();
//                }
//                List<String[][]> list = ExcelUtil.parseExcelData(file.getOriginalFilename(), file.getInputStream(), 1);
//                if (ObjectUtils.isNull(list) || list.isEmpty()) {
//                    json.put("status", "error");
//                    json.put("message", "对不起，框架合同管理不能为空，请检查");
//                    return json.toString();
//                }
//                List<Map<String, Object>> addList = new ArrayList<>();
//                Set<String> set = new HashSet<>();
//                for (int i = 0; i < list.size(); i++) {
//                    String[][] rows = list.get(i);
//                    for (int m = 0; m < rows.length; m++) {
//                        String[] cols = rows[m];
//                        StringBuilder paramCheckedinfo = new StringBuilder();
//                        if (ObjectUtils.isNull(ObjectUtils.trimSpace(cols[0])) || ObjectUtils.trimSpace(cols[0]).length() > 60) {
//                            paramCheckedinfo.append("框架合同编号不能为空,且字符长度最大为60;");
//                        }
//                        if (!ObjectUtils.isNull(ObjectUtils.trimSpace(cols[0]))){
//                            Map<String, Object> pMap =new HashMap<>();
//                            pMap.put("frameNo",ObjectUtils.trimSpace(cols[0]));
//                            List<Map<String, Object>> list2=compactFrameSV.selectListAll(pMap);
//                            if(!list2.isEmpty()){
//                                paramCheckedinfo.append("框架合同编号在数据库中已存在，请重新输入;");
//                            }
//                        }
//                        set.add(ObjectUtils.trimSpace(cols[0]));
//                        if (ObjectUtils.isNull(cols[1]) || cols[1].length() > 100) {
//                            paramCheckedinfo.append("框架合同名称不能为空,且字符长度最大为100;");
//                        }
//                        if (ObjectUtils.isNull(cols[2]) || cols[2].length() > 60) {
//                            paramCheckedinfo.append("立项项目编号不能为空,且字符长度最大为60;");
//                        }
//                        if (ObjectUtils.isNull(cols[3]) || cols[3].length() > 100) {
//                            paramCheckedinfo.append("立项项目名称不能为空,且字符长度最大为100;");
//                        }
//                        if (ObjectUtils.isNull(cols[4]) || cols[4].length() > 15) {
//                            paramCheckedinfo.append("合同含税金额不能为空,且长度最大为15;");
//                        }
//                        if (ObjectUtils.isNull(cols[5]) || cols[5].length() > 15) {
//                            paramCheckedinfo.append("合同不含税金额不能为空,且长度最大为15;");
//                        }
//                        if (ObjectUtils.isNull(cols[6]) || cols[6].length() > 13) {
//                            paramCheckedinfo.append("税率不能为空,且字符长度最大为13;");
//                        }
//                        if (ObjectUtils.isNull(cols[7]) || cols[7].length() > 200) {
//                            paramCheckedinfo.append("资金类型不能为空,且填写：投资,成本,零购,研发；");
//                        }
//                        if (ObjectUtils.isNull(cols[8])) {
//                            paramCheckedinfo.append("合同生效时间不能为空;");
//                        }
//                        if (ObjectUtils.isNull(cols[9])) {
//                            paramCheckedinfo.append("合同失效时间不能为空;");
//                        }
////                        if (ObjectUtils.isNull(cols[10]) || cols[10].length() > 200) {
////                            paramCheckedinfo.append("所属部门不能为空,且字符长度最大为200;");
////                        }
//                        if (ObjectUtils.isNull(cols[11]) || cols[11].length() > 200) {
//                            paramCheckedinfo.append("承办部门不能为空,且字符长度最大为200;");
//                        }
//                        if (ObjectUtils.isNull(cols[12]) || cols[12].length() > 200) {
//                            paramCheckedinfo.append("供应商不能为空,且字符长度最大为200;");
//                        }
//                        if (ObjectUtils.isNull(cols[13]) || cols[13].length() > 200) {
//                            paramCheckedinfo.append("执行人姓名不能为空,且字符长度最大为200;");
//                        }
//                        if (paramCheckedinfo.length() > 0) {
//                            json.put("status", "error");
//                            json.put("message", "第【" + (m + 2) + "】行：" + paramCheckedinfo);
//                            return json.toString();
//                        }
//                        //相关数据ID查询校验
//                        String executeUserName = cols[13].trim();// 执行人
////                        String compactCompanyName = cols[10].trim();// 所属部门
//                        String executeCompanyName = cols[11].trim();// 承办部门
//                        String supplierCompanyName = cols[12].trim();// 供应商
//                        String compactPaymentMoney = cols[4].trim();// 含税金额
//                        String compactRealityMoney = cols[5].trim();// 不含税金额
////                        Company company = companySV.getCompanyByCompanyName(compactCompanyName);
//                        Company executeCompany = companySV.getCompanyByCompanyName(executeCompanyName);
//                        Company supplierCompany = companySV.getCompanyByCompanyName(supplierCompanyName);
//                        User user = userSV.getUserByRealName(executeUserName);
//                        if (compactPaymentMoney.indexOf(".") != -1) {
//                            String last1 = compactPaymentMoney.substring(compactPaymentMoney.indexOf(".") + 1);
//                            if (last1.length() > 2) {
//                                paramCheckedinfo.append("合同含税金额最多保留两位小数；");
//                            }
//                        }
//                        if (compactRealityMoney.indexOf(".") != -1) {
//                            String last2 = compactRealityMoney.substring(compactRealityMoney.indexOf(".") + 1);
//                            if (last2.length() > 2) {
//                                paramCheckedinfo.append("合同不含税金额最多保留两位小数；");
//                            }
//                        }
////                        if (ObjectUtils.isNull(company) || ObjectUtils.isNull(company.getCompanyId())) {
////                            paramCheckedinfo.append("所属部门不存在；");
////                        }
//                        if (ObjectUtils.isNull(executeCompany) || ObjectUtils.isNull(executeCompany.getCompanyId())) {
//                            paramCheckedinfo.append("承办部门不存在；");
//                        }
//                        if (ObjectUtils.isNull(supplierCompany) || ObjectUtils.isNull(supplierCompany.getCompanyId())) {
//                            paramCheckedinfo.append("供应商不存在；");
//                        }
////                        if (ObjectUtils.isNull(company) || ObjectUtils.isNull(user.getUserId())) {
//                        if (ObjectUtils.isNull(user.getUserId())) {
//                            paramCheckedinfo.append("执行人不存在；");
//                        }
//                        if (paramCheckedinfo.length() > 0) {
//                            json.put("status", "error");
//                            json.put("message", "第【" + (m + 2) + "】行：" + paramCheckedinfo);
//                            return json.toString();
//                        }
//
//                        Map<String, Object> map = new HashMap<>();
//                        map.put("frameNo", cols[0]);
//                        map.put("frameName", cols[1]);
//                        map.put("projectNo", cols[2]);
//                        map.put("projectName", cols[3]);
//                        map.put("createUserId", loginUser.getUserId());
//                        map.put("createTime", new Date());
//                        map.put("isDelete", 0);
//                        map.put("framePaymentMoney", cols[4]);
//                        map.put("frameRealityMoney", cols[5]);
//                        map.put("frameTaxRate", cols[6]);
//                        String[] split = cols[7].split(",");
//                        StringBuilder sb = new StringBuilder();
//                        for (String s : split) {
//                            sb.append(ProjectResourceTypes.getCodeByLabel(s)).append(",");
//                        }
//                        map.put("frameCapitalNature", sb.deleteCharAt(sb.length() - 1).toString());
//                        map.put("frameEffectTime", DateUtils.stringToDate(cols[8], "yyyy-MM-dd"));
//                        map.put("frameValidityTime", DateUtils.stringToDate(cols[9], "yyyy-MM-dd"));
//                        map.put("frameExecuteUserId", user.getUserId());
//                        map.put("frameExecuteUserName", executeUserName);
//                        Company userComp = companySV.getCompanyByUserId(user.getUserId());
//                        map.put("frameCompanyId", userComp.getCompanyId());
//                        map.put("frameCompanyName", getSsbm(userComp.getDepartmentId()));
//                        map.put("frameExecuteCompanyId", executeCompany.getCompanyId());
//                        map.put("frameExecuteCompanyName", executeCompanyName);
//                        map.put("frameSupplierId", supplierCompany.getCompanyId());
//                        map.put("frameSupplierName", supplierCompanyName);
//                        addList.add(map);
//                        count += list.size();
//                    }
//                }
//                if(set.size() != addList.size()){
//                    json.put("status", "error");
//                    json.put("message", "模板中有相同的框架合同编号，请检查！");
//                    return json.toString();
//                }
//                compactFrameSV.saveBatch(addList);
//            }
//            json.put("status", "ok");
//            json.put("message", "成功导入" + count + "条工单数据.");
//        } catch (Exception e) {
//            json.put("status", "error");
//            json.put("message", "导入失败，失败原因：模板格式不对或者模板内容错误 请检查！");
//            log.error(e.getMessage(), e);
//        }
//
//        return json.toString();
//    }
//
//    /**
//     * 获取所属部门
//     * @param cid
//     * @return
//     */
//    private String getSsbm(String cid){
//        String result = "";
//        int companyType = userSV.getCompanyType(cid,
//                Common.getCOMPANY_ID(), Common.getBRANCH_OFFICE_CORRELATION_ID(),
//                Common.getMANUFACTURER_ID_2(), Common.getMANUFACTURER_ID());
//        Company userComp = companySV.getCompanyById(ObjectUtils.parseInt(cid));
//        if(companyType == 1){//代表是总部公司  分公司
//            Company branchCompany = companySV.getCompanyByCorrelationId(Common.getBRANCH_OFFICE_CORRELATION_ID());
//            if (userComp.getParentId().equals(branchCompany.getCompanyId().toString())) {
//                result = userComp.getCompanyName();
//            }else{
//                List<String> cnl = new ArrayList<>();
//                cnl.add(userComp.getCompanyName());
//                for(int i = 0; i < 10; i ++) { // 不知道该公司下有多少部门，暂定10级
//                    Company comp = companySV.getCompanyById(ObjectUtils.parseInt(userComp.getParentId()));
//                    if (comp == null) {
//                        break;
//                    }
//                    userComp = comp;
//                    cnl.add(userComp.getCompanyName());
//                    if (branchCompany.getCompanyId().toString().equals(userComp.getParentId())){
//                        break;
//                    }
//                }
//                StringBuffer sb = new StringBuffer();
//                for (int i = cnl.size() -1 ; i >= 0; i--) {
//                    sb.append(cnl.get(i)).append("&");
//                }
//                result = sb.deleteCharAt(sb.length() - 1).toString();
//            }
//        }else {
//            result = userComp.getCompanyName();
//        }
//        return result;
//    }
//
//    /**
//     * 订单合同导入
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/compact-order-import-data", produces = {"text/html;charset=UTF-8"}, method = RequestMethod.POST)
//    @ResponseBody
//    public String importCompactOrderData(HttpServletRequest request) {
//        JSONObject json = new JSONObject();
//        User loginUser = Common.getUser(request.getSession());
//        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
//        try {
//            String rsMessage = checkExcelFileTypes(files);
//            if (!StringUtils.isEmpty(rsMessage)) {
//                json.put("status", "error");
//                json.put("message", rsMessage);
//                return json.toString();
//            }
//            int count = 0;
//            for (MultipartFile file : files) {
//                if (file.isEmpty()) {
//                    json.put("status", "error");
//                    json.put("message", "对不起文件内容不能为空");
//                    return json.toString();
//                }
//                List<String[][]> list = ExcelUtil.parseExcelData(file.getOriginalFilename(), file.getInputStream(), 1);
//                if (ObjectUtils.isNull(list) || list.isEmpty()) {
//                    json.put("status", "error");
//                    json.put("message", "对不起，订单合同管理不能为空，请检查");
//                    return json.toString();
//                }
//                Set<String> set = new HashSet<>();
//                List<Map<String, Object>> addList = new ArrayList<>();
//                for (int i = 0; i < list.size(); i++) {
//                    String[][] rows = list.get(i);
//                    for (int m = 0; m < rows.length; m++) {
//                        String[] cols = rows[m];
//                        StringBuilder paramCheckedinfo = new StringBuilder();
//                        if (ObjectUtils.isNull(ObjectUtils.trimSpace(cols[0])) || ObjectUtils.trimSpace(cols[0]).length() > 60) {
//                            paramCheckedinfo.append("订单合同编号不能为空,且字符长度最大为60;");
//                        }
//                        if (!ObjectUtils.isNull(ObjectUtils.trimSpace(cols[0]))){
//                            //判断订单合同编号唯一
//                            Map<String, Object> pMap =new HashMap<>();
//                            pMap.put("orderNo",ObjectUtils.trimSpace(cols[0]));
//                            List<Map<String, Object>> list2=compactOrderSV.selectListAll(pMap);
//                            set.add(ObjectUtils.trimSpace(cols[0]));
//                            if(!list2.isEmpty()){
//                                paramCheckedinfo.append("订单合同编号已存在，请重新输入;");
//                            }
//                        }
//                        if (ObjectUtils.isNull(cols[1]) || cols[1].length() > 100) {
//                            paramCheckedinfo.append("订单合同名称不能为空,且字符长度最大为100;");
//                        }
//                        if (cols[2].length() > 60) {
//                            paramCheckedinfo.append("框架合同编号字符长度最大为60;");
//                        }
//                        if (cols[3].length() > 100) {
//                            paramCheckedinfo.append("框架合同编号字符长度最大为100;");
//                        }
//                        if (ObjectUtils.isNull(cols[4]) || cols[4].length() > 60) {
//                            paramCheckedinfo.append("订单项目编号不能为空,且字符长度最大为60;");
//                        }
//                        if (ObjectUtils.isNull(cols[5]) || cols[5].length() > 100) {
//                            paramCheckedinfo.append("立项项目名称不能为空,且字符长度最大为100;");
//                        }
//                        if (ObjectUtils.isNull(cols[6]) || cols[6].length() > 15) {
//                            paramCheckedinfo.append("合同含税金额不能为空,且长度最大为15;");
//                        }
//                        if (ObjectUtils.isNull(cols[7]) || cols[7].length() > 15) {
//                            paramCheckedinfo.append("合同不含税金额不能为空,且长度最大为15;");
//                        }
//                        if (ObjectUtils.isNull(cols[8]) || cols[8].length() > 13) {
//                            paramCheckedinfo.append("税率不能为空,且字符长度最大为13;");
//                        }
//                        if (ObjectUtils.isNull(cols[9]) || cols[9].length() > 200) {
//                            paramCheckedinfo.append("资金类型不能为空,且填写：投资,成本,零购,研发；");
//                        }
//                        if (ObjectUtils.isNull(cols[10])) {
//                            paramCheckedinfo.append("合同生效时间不能为空;");
//                        }
//                        if (ObjectUtils.isNull(cols[11])) {
//                            paramCheckedinfo.append("合同失效时间不能为空;");
//                        }
////                        if (ObjectUtils.isNull(cols[12]) || cols[12].length() > 200) {
////                            paramCheckedinfo.append("所属部门不能为空,且字符长度最大为200;");
////                        }
//                        if (ObjectUtils.isNull(cols[13]) || cols[13].length() > 200) {
//                            paramCheckedinfo.append("承办部门不能为空,且字符长度最大为200;");
//                        }
//                        if (ObjectUtils.isNull(cols[14]) || cols[14].length() > 200) {
//                            paramCheckedinfo.append("供应商不能为空,且字符长度最大为200;");
//                        }
//                        if (ObjectUtils.isNull(cols[15]) || cols[15].length() > 200) {
//                            paramCheckedinfo.append("执行人姓名不能为空,且字符长度最大为200;");
//                        }
//                        if (paramCheckedinfo.length() > 0) {
//                            json.put("status", "error");
//                            json.put("message", "第【" + (m + 2) + "】行：" + paramCheckedinfo);
//                            return json.toString();
//                        }
//                        //相关数据ID查询校验
//                        String executeUserName = cols[15].trim();// 执行人
////                        String compactCompanyName = cols[12].trim();// 所属部门
//                        String executeCompanyName = cols[13].trim();// 承办部门
//                        String supplierCompanyName = cols[14].trim();// 供应商
//                        String compactPaymentMoney = cols[6].trim();// 含税金额
//                        String compactRealityMoney = cols[7].trim();// 不含税金额
////                        Company company = companySV.getCompanyByCompanyName(compactCompanyName);
//                        Company executeCompany = companySV.getCompanyByCompanyName(executeCompanyName);
//                        Company supplierCompany = companySV.getCompanyByCompanyName(supplierCompanyName);
//                        //模板中框架编号，框架名称查询框架合同是否存在
//                        Map<String,Object> frameMap = new HashMap<>();
//                        if(!ObjectUtils.isNull(cols[2].trim())){
//                            frameMap.put("frameNo",cols[2].trim());
//                        }
//                        List<Map<String,Object>> listFrame = new ArrayList<>();
//                        if(!frameMap.isEmpty()){
//                            listFrame = compactFrameSV.selectListAll(frameMap);
//                        }
//                        User user = userSV.getUserByRealName(executeUserName);
//                        if(!ObjectUtils.isNull(cols[2].trim())){
//                            if(listFrame == null || listFrame.isEmpty()){
//                                paramCheckedinfo.append("框架合同编号不正确；");
//                            }
//                            if(listFrame != null && !listFrame.isEmpty()){
//                                if(!listFrame.get(0).get("frameName").equals(cols[3].trim())){
//                                    paramCheckedinfo.append("框架合同名称不正确；");
//                                }
//                            }
//                        }
//                        if (compactPaymentMoney.indexOf(".") != -1) {
//                            String last1 = compactPaymentMoney.substring(compactPaymentMoney.indexOf(".") + 1);
//                            if (last1.length() > 2) {
//                                paramCheckedinfo.append("合同含税金额最多保留两位小数；");
//                            }
//                        }
//                        if (compactRealityMoney.indexOf(".") != -1) {
//                            String last2 = compactRealityMoney.substring(compactRealityMoney.indexOf(".") + 1);
//                            if (last2.length() > 2) {
//                                paramCheckedinfo.append("合同不含税金额最多保留两位小数；");
//                            }
//                        }
////                        if (ObjectUtils.isNull(company) || ObjectUtils.isNull(company.getCompanyId())) {
////                            paramCheckedinfo.append("所属部门不存在；");
////                        }
//                        if (ObjectUtils.isNull(executeCompany) || ObjectUtils.isNull(executeCompany.getCompanyId())) {
//                            paramCheckedinfo.append("承办部门不存在；");
//                        }
//                        if (ObjectUtils.isNull(supplierCompany) || ObjectUtils.isNull(supplierCompany.getCompanyId())) {
//                            paramCheckedinfo.append("供应商不存在；");
//                        }
////                        if (ObjectUtils.isNull(company) || ObjectUtils.isNull(user.getUserId())) {
//                        if (ObjectUtils.isNull(user.getUserId())) {
//                            paramCheckedinfo.append("执行人不存在；");
//                        }
//                        if (paramCheckedinfo.length() > 0) {
//                            json.put("status", "error");
//                            json.put("message", "第【" + (m + 2) + "】行：" + paramCheckedinfo);
//                            return json.toString();
//                        }
//
//                        Map<String, Object> map = new HashMap<>();
//                        map.put("orderNo", cols[0]);
//                        map.put("orderName", cols[1]);
//                        if(!listFrame.isEmpty()){
//                            map.put("frameId", listFrame.get(0).get("id"));
//                        }
//                        map.put("projectNo", cols[4]);
//                        map.put("projectName", cols[5]);
//                        map.put("createUserId", loginUser.getUserId());
//                        map.put("createTime", new Date());
//                        map.put("isDelete", 0);
//                        map.put("orderPaymentMoney", cols[6]);
//                        map.put("orderRealityMoney", cols[7]);
//                        map.put("orderTaxRate", cols[8]);
//                        String[] split = cols[9].split(",");
//                        StringBuilder sb = new StringBuilder();
//                        for (String s : split) {
//                            sb.append(ProjectResourceTypes.getCodeByLabel(s)).append(",");
//                        }
//                        map.put("orderCapitalNature", sb.deleteCharAt(sb.length() - 1).toString());
//                        map.put("orderEffectTime", DateUtils.stringToDate(cols[10], "yyyy-MM-dd"));
//                        map.put("orderValidityTime", DateUtils.stringToDate(cols[11], "yyyy-MM-dd"));
//                        map.put("orderExecuteUserId", user.getUserId());
//                        map.put("orderExecuteUserName", executeUserName);
//                        Company userComp = companySV.getCompanyByUserId(user.getUserId());
//                        map.put("orderCompanyId", userComp.getCompanyId());
//                        map.put("orderCompanyName", getSsbm(userComp.getDepartmentId()));
//                        map.put("orderExecuteCompanyId", executeCompany.getCompanyId());
//                        map.put("orderExecuteCompanyName", executeCompanyName);
//                        map.put("orderSupplierId", supplierCompany.getCompanyId());
//                        map.put("orderSupplierName", supplierCompanyName);
//                        addList.add(map);
//                        count += list.size();
//                    }
//                }
//                if(set.size() != addList.size()){
//                    json.put("status", "error");
//                    json.put("message", "模板中有相同的订单合同编号，请检查！");
//                    return json.toString();
//                }
//                compactOrderSV.saveBatch(addList);
//            }
//            json.put("status", "ok");
//            json.put("message", "成功导入" + count + "条工单数据.");
//        } catch (Exception e) {
//            json.put("status", "error");
//            json.put("message", "导入失败，失败原因：模板格式不对或者模板内容错误 请检查！");
//            log.error(e.getMessage(), e);
//        }
//
//        return json.toString();
//    }
//
//
//    /**
//     * 机房出入管理人员导入
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/import-computerlab-order", produces = {"text/html;charset=UTF-8"}, method = RequestMethod.POST)
//    @ResponseBody
//    public String importComputerlabOrder(HttpServletRequest request) {
//        JSONObject json = new JSONObject();
//        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
//        try {
//            String rsMessage = checkExcelFileTypes(files);
//            if (!StringUtils.isEmpty(rsMessage)) {
//                json.put("status", "error");
//                json.put("message", rsMessage);
//                return json.toString();
//            }
//            int count = 0;
//            for (MultipartFile file : files) {
//                if (file.isEmpty()) {
//                    json.put("status", "error");
//                    json.put("message", "对不起文件内容不能为空");
//                    return json.toString();
//                }
//                List<String[][]> list = ExcelUtil.parseExcelData(file.getOriginalFilename(), file.getInputStream(), 1);
//                if (ObjectUtils.isNull(list) || list.isEmpty()) {
//                    json.put("status", "error");
//                    json.put("message", "对不起，机房进入人员不能为空，请检查");
//                    return json.toString();
//                }
//                int resCount = 0;
//                List<Map<String, Object>> addList = new ArrayList<>();
//                List personnelList = new ArrayList();
//                for (int i = 0; i < list.size(); i++) {
//                    String[][] rows = list.get(i);
//                    for (int m = 0; m < rows.length; m++) {
//                        resCount++;
//                        String[] cols = rows[m];
//                        ComputerlabUsers computerlabUsers = new ComputerlabUsers();
//                        StringBuilder paramCheckedinfo = new StringBuilder();
//                        if (ObjectUtils.isNull(ObjectUtils.trimSpace(cols[0])) || ObjectUtils.trimSpace(cols[0]).length() > 20) {
//                            paramCheckedinfo.append("excel文档中的姓名不能为空，且长度不能大于20位字符");
//                        }
//                        if (ObjectUtils.isNull(ObjectUtils.trimSpace(cols[1])) || ObjectUtils.trimSpace(cols[1]).length() > 18) {
//                            paramCheckedinfo.append("excel文档中的身份证号码不能为空，且长度不能大于18位字符");
//                        }
//                        if (ObjectUtils.isNull(ObjectUtils.trimSpace(cols[1])) || ObjectUtils.trimSpace(cols[2]).length() > 11) {
//                            paramCheckedinfo.append("excel文档中的手机号码不能为空，且长度不能大于11位字符");
//                        }
//                        if(paramCheckedinfo.length() > 0){
//                            json.put("status", "error");
//                            json.put("message", "第【" + resCount + "】行：" + paramCheckedinfo);
//                            return json.toString();
//                        }
//                        computerlabUsers.setEnterComputerlabUserName(ObjectUtils.trimSpace(cols[0]));
//                        computerlabUsers.setEnterComputerlabUserCard(ObjectUtils.trimSpace(cols[1]));
//                        computerlabUsers.setEnterComputerlabUserPhone(ObjectUtils.trimSpace(cols[2]));
//                        personnelList.add(computerlabUsers);
//                    }
//                }
//                json.put("personnelList", personnelList);
//            }
//            json.put("status", "ok");
//            json.put("message", "成功导入" + count + "条工单数据.");
//        } catch (Exception e) {
//            json.put("status", "error");
//            json.put("message", "导入失败，失败原因：模板格式不对或者模板内容错误 请检查！");
//            log.error(e.getMessage(), e);
//        }
//        return json.toString();
//    }
//
//
//
//    //这段代码不要删除( 这个判断用于后期跟合作伙伴关联时开放)
////                        List<Personnel> workloadUserList = personnelSV.getPersonnelListByPersonnelName(arr2[7].trim());
////                        if(workloadUserList.isEmpty() && ObjectUtils.isNull(userPhone)){
////                            paramCheckedinfo.append("[" + arr2[7].trim() + "]数据库中手机不存在；");
////                        }
////                        if(!workloadUserList.isEmpty() && workloadUserList.size() > 1 && ObjectUtils.isNull(userPhone)){
////                            paramCheckedinfo.append("根据工作量姓名[" + arr2[7].trim() + "]得到多条用户信息，请核实手机；");
////                        }
////                        //判断查询人员手机是否存在，若不存在 则取excel中手机，若excel中手机也为空，提示信息
////                        if(!workloadUserList.isEmpty() && workloadUserList.size() == 1){
////                            Personnel workloadUser = workloadUserList.get(0);
////                            String phoneNum = ObjectUtils.isNull(workloadUser.getPersonnelPhone()) ? "" : workloadUser.getPersonnelPhone();
////                            if(!ObjectUtils.isNull(phoneNum)){
////                                userPhone = phoneNum;
////                            }
////                            if(ObjectUtils.isNull(phoneNum) && ObjectUtils.isNull(userPhone)){
////                                paramCheckedinfo.append("[" + arr2[7].trim() + "]手机为空；");
////                            }
////                        }
//}
