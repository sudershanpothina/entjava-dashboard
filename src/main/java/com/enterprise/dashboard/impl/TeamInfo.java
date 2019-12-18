package com.enterprise.dashboard.impl;

import com.enterprise.dashboard.dao.GenericDao;
import com.enterprise.dashboard.model.Team;
import com.enterprise.dashboard.model.User;

import javax.servlet.http.HttpServletRequest;


public class TeamInfo {
    public String getTeamId(HttpServletRequest req, GenericDao userDao) {
        String teamId = ((User) userDao.getByProperty("userName", req.getUserPrincipal().getName()).get(0)).getTeamId();
        if(req.getUserPrincipal().toString().contains("admin")) {
            req.setAttribute("isAdmin", req.getUserPrincipal().toString().contains("admin"));
        }
        GenericDao teamDao = new GenericDao(Team.class);
        Team team = (Team) teamDao.getById(Integer.parseInt(teamId));
        req.setAttribute("teamName", team.getName());
        req.setAttribute("teamDescription", team.getDescription());
        return teamId;
    }
}
