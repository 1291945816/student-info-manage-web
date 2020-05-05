package controller.servlet;

import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.servlet.CaptchaServlet;
import com.wf.captcha.utils.CaptchaUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author: Hps
 * @date: 2020/4/19 12:11
 * @description: 这个类是用于生成验证码的
 */

@WebServlet("/checkcode")
public class checkedCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArithmeticCaptcha captcha=new ArithmeticCaptcha(200,50);
        captcha.setLen(3);
        req.getSession().setAttribute("check_result",captcha.text());
        captcha.out(resp.getOutputStream());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
