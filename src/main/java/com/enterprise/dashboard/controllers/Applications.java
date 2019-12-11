package com.enterprise.dashboard.controllers;

import com.enterprise.dashboard.dao.GenericDao;
import com.enterprise.dashboard.model.Application;
import com.enterprise.dashboard.model.Team;
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

@WebServlet(
        urlPatterns = {"/applications"}
)
public class Applications extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao userDao;
    private GenericDao applicationDao;

    public void init() {
        userDao  = new GenericDao(User.class);;
        applicationDao = new GenericDao(Application.class);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String teamId = ((User) userDao.getByProperty("userName", req.getUserPrincipal().getName()).get(0)).getTeamId();
        if(req.getUserPrincipal().toString().contains("admin")) {
            req.setAttribute("isAdmin", req.getUserPrincipal().toString().contains("admin"));
        }

        if(req.getAttribute("insert") != null) {
            Application application = new Application(req.getParameter("name"),
                    req.getParameter("description"), teamId);
            if(req.getParameter("id") != null) {
                application = (Application) applicationDao.getById(Integer.parseInt(
                        req.getParameter("id")));
                application.setName(req.getParameter("name"));
                application.setDescription(req.getParameter("description"));
                logger.info("updating application");
            }
            logger.error(application);
            applicationDao.saveOrUpdate(application);
        }else if(req.getParameter("action") != null && req.getParameter("action").equalsIgnoreCase("add") ) {
            req.setAttribute("action", "add");
        }else if(req.getParameter("action") != null && req.getParameter("action").equalsIgnoreCase("delete") ) {
            applicationDao.delete(applicationDao.getById(Integer.parseInt(req.getParameter("application_id"))));
        }
        else if(req.getParameter("action") != null && req.getParameter("action").equalsIgnoreCase("edit") ) {
            req.setAttribute("action", "add");
            req.setAttribute("application",applicationDao.getById(Integer.parseInt(req.getParameter("application_id"))));
            RequestDispatcher dispatcher = req.getRequestDispatcher("/applications_display.jsp");
            dispatcher.forward(req, resp);
        }
        req.setAttribute("applications", applicationDao.getByProperty("teamId", teamId));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/applications_display.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("insert", "insert");
        doGet(req,resp);
    }
}

