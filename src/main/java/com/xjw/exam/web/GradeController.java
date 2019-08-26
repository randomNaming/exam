package com.xjw.exam.web;

import com.github.pagehelper.PageInfo;
import com.xjw.exam.entity.GradeView;
import com.xjw.exam.entity.LoginHistory;
import com.xjw.exam.entity.Student;
import com.xjw.exam.entity.TestHistory;
import com.xjw.exam.service.GradeViewService;
import com.xjw.exam.service.LoginHistoryService;
import com.xjw.exam.service.TestHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 学生成绩 - 控制网关
 * @author xiajingwei - S.H.Xjw@outlook.com
 * @date 2019-08-18
 */
@RestController     // RestController = @Controller + @ResponBody
@RequestMapping("/teacher/stuGrade")
// @CrossOrigin  局部跨域注解
public class GradeController {

    @Autowired
    private GradeViewService gradeViewService;
    @Autowired
    private TestHistoryService testHistoryService;
    @Autowired
    private LoginHistoryService loginHistoryService;

    @RequestMapping(value = "gradeDetail", method = RequestMethod.POST)
    public Map<String, Object> gradeDetail(HttpServletRequest request,int pageNum, int pageSize, Integer paperId){
        Map<String, Object> paperDetail = new HashMap<>();
        HttpSession session = request.getSession();
        Student student = (Student)session.getAttribute("user");
        if (paperId == -1){
            paperId = null;
        }
        int questionCount = testHistoryService.hasDoneTotal(student.getId(),paperId);
        paperDetail.put("count", questionCount);

        LoginHistory temp =loginHistoryService.findByUserId( student.getId());

        Date loginTime = ( temp ).getLoginTime();
        // TODO:转换问题
        System.out.println("temp==" + loginTime.toString());
        paperDetail.put("lastTime", loginTime.toString());

        int paperScore = gradeViewService.getPaperScore(student.getId());
        paperDetail.put("total", paperScore);

        PageInfo<TestHistory> page = testHistoryService.testLog(pageNum, pageSize,student, paperId);
        paperDetail.put("datas", page);

        return paperDetail;
    }

    @RequestMapping(value = "searchStuScore", method = RequestMethod.POST)
    public PageInfo<GradeView> searchStuScore(HttpServletRequest request
            , Integer leftScoresRange, Integer rightScoresRange
            , int pageNum, int pageSize){

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        GradeView searchParam = new GradeView();
        searchParam.setStuId(id);
        searchParam.setStuName(name);
        PageInfo<GradeView> page = gradeViewService.searchInfo(searchParam,leftScoresRange,rightScoresRange,pageNum,pageSize);

        return page;
    }

}
