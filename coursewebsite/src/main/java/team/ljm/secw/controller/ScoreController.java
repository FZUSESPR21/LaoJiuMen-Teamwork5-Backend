package team.ljm.secw.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.ljm.secw.dto.ExcelDataDTO;
import team.ljm.secw.dto.ExcelHeadDTO;
import team.ljm.secw.dto.ScoreDTO;
import team.ljm.secw.entity.Clazz;
import team.ljm.secw.entity.Student;
import team.ljm.secw.service.IClazzService;
import team.ljm.secw.service.IScoreService;
import team.ljm.secw.service.IStudentService;
import team.ljm.secw.utils.ScoreExcelUtil;
import team.ljm.secw.vo.ResponseVO;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class ScoreController {

    @Autowired
    IScoreService scoreService;

    @Autowired
    IStudentService studentService;

    @Autowired
    IClazzService clazzService;

    @RequiresRoles("teacher")
    @RequestMapping(value = "/teacher/score/all")
    @ResponseBody
    public ResponseVO showScoreList(@RequestParam("clazzId") int clazzId,
                                    @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 5);
        List<Student> studentScoreList = scoreService.findScoreListByClazzId(clazzId);
        PageInfo<Student> pageInfo = new PageInfo<>(studentScoreList, 5);
        return new ResponseVO("200", "", pageInfo);
    }

    @RequiresRoles("teacher")
    @RequestMapping(value = "/teacher/homework/title")
    @ResponseBody
    public ResponseVO showHomeworkIdAndTitleListByClazzId(@RequestParam("clazzId") int clazzId) {
        return new ResponseVO("200", "", scoreService.findHomeworkIdAndTitleListByClazzId(clazzId));
    }

    @RequiresRoles("teacher")
    @RequestMapping(value = "/teacher/score/homework")
    @ResponseBody
    public ResponseVO showHomeworkScoreList(@RequestParam("hid") int hid, @RequestParam("cid") int cid) {
        return new ResponseVO("200", "", scoreService.findHomeworkScoreByHomeworkIdAndClazzId(hid, cid));
    }

    @RequiresRoles("teacher")
    @RequestMapping(value = "/teacher/score/final")
    @ResponseBody
    public ResponseVO showFinalScoreList(@RequestParam("cid") int cid) {
        return new ResponseVO("200", "", scoreService.findFinalScoreByClazzId(cid));
    }

    //@RequiresRoles("teacher")
    @RequestMapping(value = "/teacher/score/down", method = RequestMethod.GET)
    public void downloadScoreFormat(@RequestParam("clazzId") int clazzId, HttpServletResponse response) {
        try {
            String sheetName = "成绩录入格式";
            List<ExcelHeadDTO> headList = new ArrayList<>();

            headList.add(new ExcelHeadDTO("学号", 0, 1, 0, 0, 3072));
            headList.add(new ExcelHeadDTO("课堂表现数据", 0, 0, 1, 2, 5991));
            headList.add(new ExcelHeadDTO("缺勤次数", 1, 1, 1, 1, 2431));
            headList.add(new ExcelHeadDTO("表现积极次数", 1, 1, 2, 2, 3560));
            headList.add(new ExcelHeadDTO("小测数据（/次）", 0, 0, 3, 7, 3840));
            headList.add(new ExcelHeadDTO("A", 1, 1, 3, 3, 768));
            headList.add(new ExcelHeadDTO("B", 1, 1, 4, 4, 768));
            headList.add(new ExcelHeadDTO("C", 1, 1, 5, 5, 768));
            headList.add(new ExcelHeadDTO("D", 1, 1, 6, 6, 768));
            headList.add(new ExcelHeadDTO("E", 1, 1, 7, 7, 768));
            headList.add(new ExcelHeadDTO("作业数据", 0, 0, 8, 9, 4862));
            headList.add(new ExcelHeadDTO("最终分数", 1, 1, 8, 8, 2431));
            headList.add(new ExcelHeadDTO("缺交次数", 1, 1, 9, 9, 2431));
            headList.add(new ExcelHeadDTO("PS：在本课程网站的考勤数据、作业数据", 0, 0, 10, 12, 19681));
            headList.add(new ExcelHeadDTO("缺勤次数", 1, 1, 10, 10, 2431));
            headList.add(new ExcelHeadDTO("作业缺交次数", 1, 1, 11, 11, 3439));
            headList.add(new ExcelHeadDTO("每次作业得分(发布时间早的在前，-2:缺交，-1:未评分)", 1, 1, 12, 12, 13811));
            headList.add(new ExcelHeadDTO("平时成绩", 0, 1, 13, 13, 2464));
            headList.add(new ExcelHeadDTO("卷面成绩", 0, 1, 14, 14, 2464));
            headList.add(new ExcelHeadDTO("总成绩", 0, 1, 15, 15, 2464));

            List<ExcelDataDTO> dataList = new ArrayList<>();

            List<Integer> absentNumList = scoreService.sumAbsentNumListOrderByAccount(clazzId);
            List<Integer> notSubmitNumList = scoreService.sumNotSubmitNumListOrderByAccount(clazzId);
            List<Integer> allHomeworkScoreList = scoreService.findHomeworkScoreListOrderByAccountAndStartAt(clazzId);
            List<String> studentAccountList = scoreService.findStudentAccountListByClazzId(clazzId);
            int studentNum = studentService.sum(clazzId);
            int homeworkNum = allHomeworkScoreList.size()/studentNum;
            List<String> homeworkScoreList = new ArrayList<>();

            for (int i = 0; i < allHomeworkScoreList.size();) {
                StringBuilder scoreText = new StringBuilder();
                for (int j = 0; j < homeworkNum ; j++) {
                    scoreText.append(allHomeworkScoreList.get(i++)).append(",");
                }
                homeworkScoreList.add(scoreText.substring(0, scoreText.length()-1));
            }

            Clazz clazz = clazzService.findScoreRuleByClazzId(clazzId);

            for (int i = 2, j = 0; i < studentNum + 2; i++, j++) {
                dataList.add(new ExcelDataDTO(studentAccountList.get(j), i, 0));
                dataList.add(new ExcelDataDTO(absentNumList.get(j), i, 10));
                dataList.add(new ExcelDataDTO(notSubmitNumList.get(j), i, 11));
                dataList.add(new ExcelDataDTO(homeworkScoreList.get(j), i, 12));
                StringBuilder s1 = new StringBuilder();
                s1.append("MIN(").append(clazz.getUsualScoreProportion()).append(",")
                        .append(clazz.getAbsentScore()).append("*B").append(i+1)
                        .append("+").append(clazz.getPerformanceScore()).append("*C").append(i+1)
                        .append("+").append(clazz.getQuizAScore()).append("*D").append(i+1)
                        .append("+").append(clazz.getQuizBScore()).append("*E").append(i+1)
                        .append("+").append(clazz.getQuizCScore()).append("*F").append(i+1)
                        .append("+").append(clazz.getQuizDScore()).append("*G").append(i+1)
                        .append("+").append(clazz.getQuizEScore()).append("*H").append(i+1)
                        .append("+").append("I").append(i+1)
                        .append("+").append(clazz.getNotSubmittedScore()).append("*J").append(i+1)
                        .append(")");
                dataList.add(new ExcelDataDTO(i, 13, s1.toString()));
                StringBuilder s2 = new StringBuilder();
                s2.append("ROUND(1*N").append(i+1).append("+")
                        .append(1.0-clazz.getUsualScoreProportion()/100.0).append("*O").append(i+1).append(",0)");
                dataList.add(new ExcelDataDTO(i, 15, s2.toString()));
            }

            XSSFWorkbook workbook = ScoreExcelUtil.createExcelFile(sheetName, headList, dataList);

            String filename = "成绩录入格式.xlsx";
            filename = URLEncoder.encode(filename, "UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + filename);
            response.setContentType("multipart/form-data");
            BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            outputStream.flush();
            workbook.write(outputStream);
            outputStream.close();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    //@RequiresRoles("teacher")
    @RequestMapping(value = "/teacher/score/excel")
    @ResponseBody
    public ResponseVO doExcel(@RequestParam(value = "file_excel") MultipartFile file) {
        return scoreService.readExcelFile(file);
    }

    @RequiresRoles("teacher")
    @RequestMapping(value = "/teacher/score/rules/search", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO showScoreRulesByClazzId(@RequestParam("cid") int cid) {
        return new ResponseVO("200", "", clazzService.findScoreRuleByClazzId(cid));
    }

    @RequiresRoles("teacher")
    @RequestMapping(value = "/teacher/score/rules/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO updateScoreRule(@RequestBody Clazz clazz) {
        return new ResponseVO("200", "", clazzService.modifyScoreRule(clazz));
    }

    @RequiresRoles("teacher")
    @RequestMapping(value = "/teacher/score/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO updateOneStudentScore(@RequestBody ScoreDTO scoreDTO) {
        return new ResponseVO("200", "", scoreService.modifyScore(scoreDTO));
    }

    @RequiresRoles("student")
    @RequestMapping(value = "/student/score/search", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO showScore(@RequestParam("sid") int sid) {
        return new ResponseVO("200", "", scoreService.findScoreByStudentId(sid));
    }

}
