package com.enterprise.dashboard.controllers;

import com.enterprise.dashboard.dao.GenericDao;
import com.enterprise.dashboard.impl.TeamInfo;
import com.enterprise.dashboard.model.Application;
import com.enterprise.dashboard.model.ErrorData;
import com.enterprise.dashboard.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The type Errors.
 */
@WebServlet(
        urlPatterns = {"/errors"}
)
public class Errors extends HttpServlet {
    private GenericDao applicationDao;
    private GenericDao userDao;

    @Override
    public void init() {
        applicationDao  = new GenericDao(Application.class);
        userDao  = new GenericDao(User.class);;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TeamInfo teamInfo = new TeamInfo();
        String teamId = teamInfo.getTeamId(req, userDao);
        if(req.getParameter("application_id") != null) {
            req.setAttribute("errors" , ((Application) applicationDao.getById(
                    Integer.parseInt(req.getParameter("application_id")))).getErrorDataSet());
        } else {
            Set<ErrorData> errors = new HashSet<>();
            List<Application> applications =  applicationDao.getByProperty("teamId", teamId);
            for (Application application : applications) {
                errors.addAll(application.getErrorDataSet());
            }
            req.setAttribute("errors" , errors);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/errors_display.jsp");
        dispatcher.forward(req, resp);
    }
}
