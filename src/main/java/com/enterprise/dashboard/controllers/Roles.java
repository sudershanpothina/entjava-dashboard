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
import java.util.Date;
import java.util.Set;

@WebServlet(
        urlPatterns = {"/roles"}
)
public class Roles extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao userDao;
    private GenericDao roleDao;

    public void init() {
        userDao = new GenericDao(User.class);
        roleDao = new GenericDao(Role.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) userDao.getByProperty("userName", req.getUserPrincipal().getName()).get(0);
        if(req.getUserPrincipal().toString().contains("admin")) {
            req.setAttribute("isAdmin", req.getUserPrincipal().toString().contains("admin"));
        }
        Set<Role> roles = user.getRoles();
        if (req.getAttribute("insert") != null) {
            Role role = new Role();
            role.setName(req.getParameter("name"));
            roles.add(role);
            user.setRoles(roles);
            if (req.getParameter("id") != null) {
                role = (Role) roleDao.getById(Integer.parseInt(req.getParameter("id")));
                role.setName(req.getParameter("name"));
                roles.add(role);
                user.setRoles(roles);
                logger.info("updating role");
            }
            userDao.saveOrUpdate(user);
        } else if (req.getParameter("action") != null && req.getParameter("action").equalsIgnoreCase("add")) {
            req.setAttribute("action", "add");
        }else if(req.getParameter("action") != null && req.getParameter("action").equalsIgnoreCase("delete") ) {
            roleDao.delete(roleDao.getById(Integer.parseInt(req.getParameter("role_id"))));
        }
        else if(req.getParameter("action") != null && req.getParameter("action").equalsIgnoreCase("edit") ) {
            req.setAttribute("action", "add");
            req.setAttribute("role",roleDao.getById(Integer.parseInt(req.getParameter("role_id"))));
            RequestDispatcher dispatcher = req.getRequestDispatcher("/roles_display.jsp");
            dispatcher.forward(req, resp);
        }
        user = (User) userDao.getByProperty("userName", req.getUserPrincipal().getName()).get(0);
        req.setAttribute("roles", user.getRoles());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/roles_display.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("insert", "insert");
        doGet(req, resp);
    }
}
