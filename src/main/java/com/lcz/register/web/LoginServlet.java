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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void service(HttpServletRequest res, HttpServletResponse resp) throws ServletException, IOException{
        String mail = res.getParameter("email");
        String password = res.getParameter("password");
        User user = new User();
        user.setEmail(mail);
        user.setPassword(password);
        UserService userService = new UserServiceImpl();
        User currentUser = userService.login(user);
        if(currentUser != null){
            res.setAttribute("msg","登录成功！");
        } else{
            res.setAttribute("msg","登录失败");
        }
        res.getRequestDispatcher("/msg.jsp").forward(res,resp);
    }
}
