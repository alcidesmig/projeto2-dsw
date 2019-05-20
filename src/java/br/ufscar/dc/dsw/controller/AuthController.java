/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.DAOTokenLogin;
import br.ufscar.dc.dsw.dao.DAOUsuario;
import br.ufscar.dc.dsw.pojo.Papel;
import br.ufscar.dc.dsw.pojo.TokenLogin;
import br.ufscar.dc.dsw.pojo.Usuario;
import java.io.IOException;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author igor
 */
@WebServlet(urlPatterns = "/auth/")
public class AuthController extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        String username = request.getParameter("username");
        String senha = request.getParameter("password");
        DAOUsuario daoUser = new DAOUsuario();
        Usuario user;
        String json;
        user = daoUser.get(username);
        if ( user == null ) {
            response.setStatus(422);
            json = "{\"error\": \"username or password incorrect\"}";
            System.out.println(json);
            return;
        }
        if ( !user.getSenha().equals(senha) ) {
            response.setStatus(422);
            json = "{\"error\": \"username or password incorrect\"}";
            System.out.println(json);
            return;
        }
        TokenLogin token = new TokenLogin(user); 
        DAOTokenLogin daoToken = new DAOTokenLogin();
        daoToken.save(token);
        Cookie loginToken = new Cookie("token", token.getToken());
        loginToken.setPath("/");
        loginToken.setMaxAge(86400);
        response.setStatus(200);
        response.addCookie(loginToken);
        json = "{\"success\": \"success\"}";
        System.out.println(json);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie cookie = new Cookie("token","0");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
//        Cookie cookies[] = request.getCookies();
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equalsIgnoreCase("token")) {
//                cookie.setMaxAge(0);
//                cookie.setValue("0");
//                response.addCookie(cookie);
//            }
//        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/templates_auth/logout.jsp");
        dispatcher.forward(request, response);
    }
    
    public static boolean hasRole(HttpServletRequest request, String role) {
        Usuario user = AuthController.getUser(request);
        if ( user == null ) {
            return false;
        }
        Set<Papel> papeis = user.getPapeis();
        if (papeis.stream().anyMatch((a) -> (a.getNome().equalsIgnoreCase(role)))) {
            return true;
        }
        return false;
    }
    
    public static Usuario getUser(HttpServletRequest request) {
        DAOTokenLogin daoToken = new DAOTokenLogin();
        Cookie cookies[] =  request.getCookies();
        int i = 0;
        while (i < cookies.length - 1 && !cookies[i].getName().equalsIgnoreCase("token")) i++;
        if ( !cookies[i].getName().equalsIgnoreCase("token") ) {
            return null;
        }
        TokenLogin token = daoToken.get(cookies[i].getValue());
        if ( token == null ) {
            return null;
        }
        Usuario user = token.getUsuario();
        return user;
    }
}
