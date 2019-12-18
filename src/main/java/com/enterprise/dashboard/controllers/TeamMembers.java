package com.enterprise.dashboard.controllers;

import com.enterprise.dashboard.dao.GenericDao;
import com.enterprise.dashboard.impl.TeamInfo;
import com.enterprise.dashboard.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Team members.
 */
@WebServlet(
        urlPatterns = {"/teamPage"}
)
public class TeamMembers extends HttpServlet {
    private GenericDao userDao;

    @Override
    public void init() {
        userDao  = new GenericDao(User.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TeamInfo teamInfo = new TeamInfo();
        String teamId = teamInfo.getTeamId(req, userDao);
        req.setAttribute("users", userDao.getByProperty("teamId", teamId));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/userList.jsp");
        dispatcher.forward(req, resp);
    }

}

