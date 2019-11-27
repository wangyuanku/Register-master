package com.lcz.register.web;

import com.lcz.register.entity.User;
import com.lcz.register.service.UserService;
import com.lcz.register.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegisterServlet2")
public class RegisterServlet2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //获取session中的验证码
        String sessionCode = (String) req.getSession().getAttribute("code");
        if(sessionCode != null) {
            //  获取页面提交的验证码
            String inputCode = req.getParameter("code");
            System.out.println("页面提交的验证码:" + inputCode);
            if (sessionCode.toLowerCase().equals(inputCode.toLowerCase())) {
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                String email = req.getParameter("email");
//                System.out.println("页面提交:" + username+password);
                //封装数据
                User user=new User();
                user.setUserName(username);
                user.setPassword(password);
                user.setEmail(email);
                user.setCode(inputCode);
                //调用业务层处理数据
                UserService userService=new UserServiceImpl();
                userService.register(user);
                //  验证成功，跳转成功页面
                req.setAttribute("msg", "您已注册成功！");
                req.getRequestDispatcher("/msg.jsp").forward(req, resp);
            }else {
                //  验证失败
                req.getRequestDispatcher("fail.jsp").forward(req, resp);
            }
        }else {
            //  验证失败
            req.getRequestDispatcher("fail.jsp").forward(req, resp);
        }
        //  移除session中的验证码
        req.removeAttribute("code");
    }
}
