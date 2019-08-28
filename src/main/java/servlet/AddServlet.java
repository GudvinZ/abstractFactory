package servlet;

import model.User;
import service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/add/*")
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");

        if (login.isEmpty() || password.isEmpty() || name.isEmpty()) {
            req.setAttribute("isEmptyForm", true);
        } else {
            if (!UserService.getInstance().addUser(
                    new User(
                            login,
                            password,
                            name
                    )
            )) {
                req.setAttribute("isNotValidate", true);
            }
        }
        System.out.println(UserService.getInstance().getAllUsers());
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
