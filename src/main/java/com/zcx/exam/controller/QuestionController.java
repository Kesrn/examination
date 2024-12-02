package com.zcx.exam.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zcx.exam.common.ApiConst;
import com.zcx.exam.common.Consts;
import com.zcx.exam.common.ResultBody;
import com.zcx.exam.entity.Question;
import com.zcx.exam.entity.User;
import com.zcx.exam.entityResult.QuestionBackResult;
import com.zcx.exam.service.QuestionService;
import com.zcx.exam.utils.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

//意见反馈模块
@Controller
@RequestMapping(ApiConst.API_QUESTION)
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping()
    public String toList(){
        return "question/list";
    }

    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     *
     * @param currPage
     * @param pageSize
     * @param createBy
     * @param questionType
     * @param questionStatus
     * @param startDate
     * @param endDate
     * @return ResultBody
     */
    @GetMapping(ApiConst.API_QUESTION_LIST)
    @ResponseBody
    public ResultBody questionList(@RequestParam(name = "page") Integer currPage,
                                   @RequestParam("limit") Integer pageSize,
                                   @RequestParam(value = "createBy",required = false)String createBy,
                                   @RequestParam(value = "questionType",required = false)String questionType,
                                   @RequestParam(value = "questionStatus",required = false)String questionStatus,
                                   @RequestParam(value = "startDate",required = false)String startDate,
                                   @RequestParam(value = "endDate",required = false)String endDate){
        ResultBody resultBody = new ResultBody();
        try {
            Map<String,Object> map = new HashMap<>();
            map.put("createBy",createBy);
            map.put("questionType",questionType);
            map.put("questionStatus",questionStatus);
            map.put("startDate",startDate);
            map.put("endDate",endDate);
            PageHelper.startPage(currPage,pageSize);
            List<Question> questions = questionService.selectAll(map);

            PageInfo<Question> pageInfo = new PageInfo<>(questions);
            resultBody.setCode(Consts.SUCCESS);
            resultBody.setMsg("查询成功！");
            resultBody.setData(questions);
            resultBody.setCount(Long.valueOf(pageInfo.getTotal()));
            return  resultBody;
        }catch (Exception e){
            e.printStackTrace();
            return resultBody.failure("查询失败！");
        }
    }

    /**
     *
     * @param id
     * @return ResultBody
     */
    @PostMapping(ApiConst.API_QUESTION_UPDATE)
    @ResponseBody
    public ResultBody questionUpDate(@PathVariable("id")int id){
        ResultBody resultBody = new ResultBody();
        try {
            questionService.updateStatus(id);
            return resultBody.success(new ArrayList<>(),"更新成功！");
        }catch (Exception e){
            e.printStackTrace();
            return resultBody.failure("更新失败，请重试！");
        }
    }
    @GetMapping(ApiConst.API_QUESTION_DETAIL)
    public String toDetail(@PathVariable("id")Integer id, Model model){
        QuestionBackResult question = questionService.selectByOne(id);
        model.addAttribute("question",question);
        String path = question.getQuestionPic();
        if(null==path){
            model.addAttribute("path","");
        }
            model.addAttribute("path",question.getQuestionPic());
            return "question/detail";
    }

    @GetMapping(ApiConst.API_QUESTION_CREATE)
    public String toAdd(){
        return "question/questionPaper";
    }
    @PostMapping(ApiConst.API_QUESTION_CREATE)
    @ResponseBody
    public ResultBody questionAdd(Question question,HttpServletRequest request){
        ResultBody resultBody = new ResultBody();
        try {
            String filePath = request.getParameter("path1");
            question.setQuestionPic(filePath);
            questionService.addQuestionPaper(question);

            return resultBody.success(new ArrayList<>(),"已收到您的反馈信息！");
        }catch (Exception e){
            e.printStackTrace();
            return resultBody.failure("系统正忙，请稍后再试！");
        }
    }

    @RequestMapping("/upload")
    @ResponseBody
    public ResultBody onUpload(@RequestParam("file") MultipartFile uploadFile, HttpServletRequest request) {
        ResultBody resultBody = new ResultBody();
        String suffix = uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf("."));
        String savePath = "userfiles/pic/" + DateUtils.getYear() + "/" + DateUtils.getMonth() + "/";
        String path = System.getProperty("user.dir") + "/profile/" + savePath;
        String saveName = (int) ((Math.random() * 9 + 1) * 100000) + suffix;
        //新文件存储路径
        File targetFile = new File(path);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        //保存
        try {
            uploadFile.transferTo(new File(path + saveName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<Object, Object> map = new HashMap<>();
        map.put("filepath", savePath + saveName);
        return resultBody.success(map, "上传成功");
    }

    @GetMapping("/exportQuestionData")
    public void exportMemberData(
            @RequestParam(value = "createBy",required = false)String createBy,
            @RequestParam(value = "questionType",required = false)String questionType,
            @RequestParam(value = "questionStatus",required = false)String questionStatus,
            @RequestParam(value = "startDate",required = false)String startDate,
            @RequestParam(value = "endDate",required = false)String endDate,
            HttpServletResponse response
    ) throws IOException {
        Map<String,Object> map = new HashMap<>();
        map.put("createBy",createBy);
        map.put("questionType",questionType);
        map.put("questionStatus",questionStatus);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        List<Question> questions = questionService.selectAll(map);


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        InputStream inputStream = this.getClass().getResourceAsStream("/static/excel_template/意见反馈情况统计表.xls");
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheetAt(0);

        for(int i = 0;i < questions.size(); i++){
            HSSFRow row = sheet.createRow(i + 2);
            HSSFCell cell1 = row.createCell(0);
            cell1.setCellValue(questions.get(i).getCreateBy());
            HSSFCell cell2 = row.createCell(1);
            cell2.setCellValue(questions.get(i).getQuestionType());
            HSSFCell cell3 = row.createCell(2);
            cell3.setCellValue(questions.get(i).getContactInfo());
            HSSFCell cell4 = row.createCell(3);
            cell4.setCellValue(dateFormat.format(questions.get(i).getCreateTime()));
            HSSFCell cell5 = row.createCell(4);
            cell5.setCellValue(questions.get(i).getQuestionStatus());
        }
        OutputStream outputStream = response.getOutputStream();
        //清空缓存
        response.reset();
        // 定义浏览器响应表头，并定义下载名
        String fileName = URLEncoder.encode("意见反馈情况统计表.xls", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename="+fileName);
        //定义下载的类型，标明是excel文件
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        //把创建好的excel写入到输出流
        workbook.write(outputStream);
        //随手关门
        outputStream.close();

    }
}
