package com.enterprise.dashboard.controllers;

import com.enterprise.dashboard.dao.GenericDao;
import com.enterprise.dashboard.impl.TeamInfo;
import com.enterprise.dashboard.impl.UserInfo;
import com.enterprise.dashboard.model.Role;
import com.enterprise.dashboard.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

import static com.enterprise.dashboard.util.DateConvert.getSqlDate;

@WebServlet(
        urlPatterns = {"/users"}
)
public class Users extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao userDao;
    private GenericDao roleDao;

    public void init() {
        userDao = new GenericDao(User.class);
        roleDao = new GenericDao(Role.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TeamInfo teamInfo = new TeamInfo();
        String teamId = teamInfo.getTeamId(req, userDao);
        java.sql.Date sqlDate = getSqlDate(req.getParameter("dob"), logger);
        final String firstName = req.getParameter("firstName");
        final String id = req.getParameter("id");
        final String lastName = req.getParameter("lastName");
        final String userName = req.getParameter("userName");
        final String password = req.getParameter("password");
        final String roleName = req.getParameter("role");
        final String imageUrl = req.getParameter("imageUrl");
        final String action = req.getParameter("action");

        UserInfo userinfo = new UserInfo();

        if (req.getAttribute("insert") != null) {
            if (id != null) {
                userinfo.updateUser(id, firstName, lastName, userName,
                        password, sqlDate, imageUrl, roleName, userDao, roleDao);
                logger.info("updating user");
            } else {
                userinfo.createUser(firstName, lastName, userName, password,
                        sqlDate, teamId, roleName, userDao, roleDao);
                logger.info("inserting user");
            }

        } else {

            if (action != null && action.equalsIgnoreCase("add")) {
                req.setAttribute("action", "add");
            }else if(action != null && action.equalsIgnoreCase("delete") ) {
                userDao.delete(userDao.getById(Integer.parseInt(req.getParameter("user_id"))));
            } else if(action != null && action.equalsIgnoreCase("edit") ) {
                req.setAttribute("action", "add");
                req.setAttribute("user",userDao.getById(Integer.parseInt(req.getParameter("user_id"))));
                RequestDispatcher dispatcher = req.getRequestDispatcher("/users_display.jsp");
                dispatcher.forward(req, resp);
            }
        }

        req.setAttribute("users", userDao.getByProperty("teamId", teamId));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/users_display.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("insert", "insert");
        doGet(req, resp);
    }


}
