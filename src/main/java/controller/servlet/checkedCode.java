package controller.servlet;

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
        int width=100;
        int height = 50;
        BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //美化图片
        //1.填充背景色 画笔
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.pink);
        graphics.fillRect(0,0,width,height);

        //画边框
        graphics.setColor(Color.BLUE);
        graphics.drawRect(0,0,width-1,height-1);

        //写验证码
        String str = "QWERTYUIOPASDFGHJKLMNBVCXZqwertyuioplkjhgfdsazxcvbnm0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= 4; i++) {
            int index = random.nextInt(str.length());
            char ch = str.charAt(index);
            sb.append(ch);
            graphics.drawString(ch+"",20*i,25);

        }
        String s = sb.toString();
        req.getSession().setAttribute("checkCode_session",s);
        //画干扰线
        graphics.setColor(Color.GREEN);

        for (int i = 1; i <=10; i++) {
            int x1=random.nextInt(width);
            int x2 = random.nextInt(width);
            int h1 = random.nextInt(height);
            int h2 = random.nextInt(height);
            graphics.drawLine(x1,x2,h1,h2);

        }
        //3输出到页面
        ImageIO.write(image,"jpg",resp.getOutputStream());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
