package team.ljm.secw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import team.ljm.secw.dto.VerificationDTO;
import team.ljm.secw.entity.Student;
import team.ljm.secw.entity.Teacher;
import team.ljm.secw.service.IChangePwdService;
import team.ljm.secw.utils.SendMailUtil;
import team.ljm.secw.vo.ResponseVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

@Controller
public class ChangePwdController {

    @Autowired
    IChangePwdService changePwdService;

    @RequestMapping(value = "/captcha", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO sendCaptchaEmail(@RequestBody VerificationDTO verificationDTO, HttpSession session) {
        String captcha = String.valueOf(new Random().nextInt(899999) + 100000);
        System.out.println(verificationDTO);
        if ("0".equals(verificationDTO.getType())) {
            String email = changePwdService.findStudentEmailByAccount(verificationDTO.getAccount());
            return getResponseVO(verificationDTO, session, captcha, email);
        } else if("1".equals(verificationDTO.getType())){
            String email = changePwdService.findTeacherEmailByAccount(verificationDTO.getAccount());
            return getResponseVO(verificationDTO, session, captcha, email);
        } else {
            return new ResponseVO("401", "错误");
        }
    }

    private ResponseVO getResponseVO(VerificationDTO verificationDTO, HttpSession session, String captcha, String email) {
        if (email != null && email.equals(verificationDTO.getEmail())) {
            SendMailUtil.sendMail(email, captcha);
            session.setAttribute("account", verificationDTO.getAccount());
            session.setAttribute("email", verificationDTO.getEmail());
            session.setAttribute("captcha", captcha);
            String account1 = (String) session.getAttribute("account");
            String email1 = (String) session.getAttribute("email");
            String captcha1 = (String) session.getAttribute("captcha");
            System.out.println("1:"+account1);
            System.out.println("1:"+email1);
            System.out.println("1:"+captcha1);
            return new ResponseVO("200", "验证码已发送");
        } else {
            return new ResponseVO("401", "账号或邮箱错误");
        }
    }

    @RequestMapping(value = "/chg_pwd", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO changePassword(@RequestBody VerificationDTO verificationDTO, HttpSession session) {
        String account = (String) session.getAttribute("account");
        String email = (String) session.getAttribute("email");
        String captcha = (String) session.getAttribute("captcha");
        System.out.println(verificationDTO);
        System.out.println(account);
        System.out.println(email);
        System.out.println(captcha);

        if (account.equals(verificationDTO.getAccount()) && email.equals(verificationDTO.getEmail())) {
            if (captcha.equals(verificationDTO.getCaptcha())) {
                session.removeAttribute("account");
                session.removeAttribute("email");
                session.removeAttribute("captcha");
                return new ResponseVO("200", "修改密码成功");
            } else {
                return new ResponseVO("401", "验证码错误");
            }
        } else {
            return new ResponseVO("401", "账号或邮箱错误");
        }
    }

}
