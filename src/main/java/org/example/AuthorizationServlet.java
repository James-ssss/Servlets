package org.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/files/login")
public class AuthorizationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false); //
        boolean isLoggedIn = false;
        if (session != null) {
            Object login = session.getAttribute("login");
            Object password = session.getAttribute("password");
            if (login != null && password != null) {
                UserService userService = new UserService();
                isLoggedIn = userService.validUser(login.toString(), password.toString());
            }
        }
        String URI = req.getContextPath() + "/files";
        String loginURI = URI + "/login";
        boolean isLoginRequest = req.getRequestURI().equals(loginURI);
        if (isLoggedIn && isLoginRequest) {
            resp.sendRedirect(URI);
        }

        String param = req.getParameter("error");
        boolean isError = param != null && param.equals("true");
        if (isError) {
            req.setAttribute("visibilityError", "block");
            req.setAttribute("errorText", "Error. The user information isn't correct.");
        }

        req.getRequestDispatcher("../authorizationServlet.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login == null || password == null) {
            resp.sendRedirect(req.getContextPath() + req.getServletPath() + "?error=true");
        } else {
            UserService userService = new UserService();
            if (userService.validUser(login, password)) {
                HttpSession session = req.getSession(true);
                session.setMaxInactiveInterval(1800); // 1800 секунд == 30 минут
                session.setAttribute("login", login);
                session.setAttribute("password", password);
                resp.sendRedirect(req.getContextPath() + "/files");
            } else {
                resp.sendRedirect(req.getContextPath() + req.getServletPath() + "?error=true");
            }
        }
    }
}
