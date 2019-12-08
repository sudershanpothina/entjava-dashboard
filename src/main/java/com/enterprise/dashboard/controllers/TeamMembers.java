package com.enterprise.dashboard.controllers;

import com.enterprise.dashboard.dao.GenericDao;
import com.enterprise.dashboard.model.Application;
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
        GenericDao userDAO = new GenericDao(User.class);
        GenericDao applicationDAO = new GenericDao(Application.class);
        req.setAttribute("users", userDAO.getAll());
        req.setAttribute("applications", applicationDAO.getAll());
        req.setAttribute("teamName", "Team Avengers");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/userList.jsp");
        dispatcher.forward(req, resp);
    }
}

