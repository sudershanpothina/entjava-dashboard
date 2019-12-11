package com.enterprise.dashboard.controllers;

import com.enterprise.dashboard.dao.GenericDao;
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
        urlPatterns = {"/teamPage"}
)
public class TeamMembers extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("------" + req.getUserPrincipal().getName());
        logger.info("------" + req.getUserPrincipal());
        logger.info("------" + req.getUserPrincipal().toString());
        GenericDao teamDao = new GenericDao(Team.class);
        GenericDao userDao = new GenericDao(User.class);
        String teamId = ((User) userDao.getByProperty("userName", req.getUserPrincipal().getName()).get(0)).getTeamId();
        Team team = (Team) teamDao.getById(Integer.parseInt(teamId));
        req.setAttribute("teamName", team.getName());
        req.setAttribute("teamDescription", team.getDescription());
        req.setAttribute("users", userDao.getByProperty("teamId", teamId));
        if(req.getUserPrincipal().toString().contains("admin")) {
            req.setAttribute("isAdmin", req.getUserPrincipal().toString().contains("admin"));
        }
        logger.info("-----" + req.getUserPrincipal().toString().contains("admin"));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/userList.jsp");
        dispatcher.forward(req, resp);
    }

}

