//package com.enterprise.dashboard.controllers;
//
//import com.enterprise.dashboard.dao.GenericDao;
//import com.enterprise.dashboard.impl.TeamInfo;
//import com.enterprise.dashboard.model.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.StringWriter;
//
//
//
//public class TestServlet extends Mockito {
//    GenericDao userDao;
//
//    @Test
//    public void testServlet() throws ServletException, IOException {
//        userDao = new GenericDao(User.class);
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        TeamInfo teamInfo = mock(TeamInfo.class);
//        when(teamInfo.getTeamId(request,userDao)).thenReturn("1");
////        when(request.getUserPrincipal().toString()).thenReturn("team-admin");
//
//        StringWriter stringWriter = new StringWriter();
//        PrintWriter printWriter = new PrintWriter(stringWriter);
//        when(response.getWriter()).thenReturn(printWriter);
//
//        new TeamMembers().doGet(request,response);
//        printWriter.flush();
//        System.out.println(stringWriter);
//    }
//}
