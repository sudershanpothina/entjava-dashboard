package com.enterprise.dashboard.controllers;

import com.enterprise.dashboard.dao.GenericDao;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.enterprise.dashboard.impl.TeamInfo.getTeamId;
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
        String teamId = getTeamId(req, userDao);
        if (req.getAttribute("insert") != null) {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date sqlDate = getSqlDate(req.getParameter("dob"), logger);
//            try {
//                java.util.Date utilDate = sdf.parse(req.getParameter("dob"));
//                sqlDate = new java.sql.Date(utilDate.getTime());
//            } catch (ParseException e) {
//                logger.error(e);
//            }
            User user = new User(req.getParameter("firstName"),
                    req.getParameter("lastName"),
                    req.getParameter("userName"),
                    req.getParameter("password"),
                    sqlDate,
                    teamId);
            user.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJJHxTYMlVcuxOjif7Nu6LGy4Amfa8NXYhmS0MfBf0Ilnbm12j&s");

            Role role = new Role();
            role.setUserName(user.getUserName());
            role.setName(req.getParameter("role"));
            role.setUser(user);

            if (req.getParameter("id") != null) {
                user = (User) userDao.getById(Integer.parseInt(
                        req.getParameter("id")));
                user.setFirstName(req.getParameter("firstName"));
                user.setLastName(req.getParameter("lastName"));
                user.setUserName(req.getParameter("userName"));
                user.setPassword(req.getParameter("password"));
                user.setDob(sqlDate);
                user.setImageUrl(req.getParameter("imageUrl"));

                if(user.getRole() != null) {
                    role = user.getRole();
                } else {
                    role = new Role();
                }
                role.setUser(user);
                role.setName(req.getParameter("role"));
                role.setUserName(user.getUserName());
                role.setUser(user);

                logger.info("updating user");
            }

            userDao.saveOrUpdate(user);
            roleDao.saveOrUpdate(role);
        } else if (req.getParameter("action") != null && req.getParameter("action").equalsIgnoreCase("add")) {
            req.setAttribute("action", "add");
        }else if(req.getParameter("action") != null && req.getParameter("action").equalsIgnoreCase("delete") ) {
            userDao.delete(userDao.getById(Integer.parseInt(req.getParameter("user_id"))));
        }
        else if(req.getParameter("action") != null && req.getParameter("action").equalsIgnoreCase("edit") ) {
            req.setAttribute("action", "add");
            req.setAttribute("user",userDao.getById(Integer.parseInt(req.getParameter("user_id"))));
            RequestDispatcher dispatcher = req.getRequestDispatcher("/users_display.jsp");
            dispatcher.forward(req, resp);
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
