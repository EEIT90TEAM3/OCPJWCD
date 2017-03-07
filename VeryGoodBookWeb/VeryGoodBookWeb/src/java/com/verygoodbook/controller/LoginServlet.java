/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.controller;

import com.verygoodbook.entity.Customer;
import com.verygoodbook.exception.VGBException;
import com.verygoodbook.service.CustomerService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login.do"})
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1. 讀取請求傳來的表單資料: userid, password, checkCode
        //request.setCharacterEncoding("UTF-8");
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");

        //2. 檢查資料
        List<String> errors = new ArrayList<>();
        if (userid == null || userid.length() == 0) {
            errors.add("必須輸入帳號");
        }

        if (password == null || password.length() == 0) {
            errors.add("必須輸入密碼");
        }

        if (checkCode == null || checkCode.length() == 0) {
            errors.add("必須輸入驗證碼");
        }

        if (errors.isEmpty()) {
            //3. 呼叫執行商業邏輯CustomerService login        
            CustomerService service = new CustomerService();
            try {
                Customer c = service.login(userid, password);

                //4.1 產生成功回應
                response.setContentType("text/html");
                response.setCharacterEncoding("UTF-8");                
                try (PrintWriter out = response.getWriter();){
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>登入成功</title>");
                    out.println("<meta charset='UTF-8'>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>登入成功</h1>");
                    out.println("<p>歡迎光臨，"+c.getName()+"</p>");
                    out.println("<input type='button' value='Home' onclick='location.href=\"index.html\"'>");
                    out.println("</body>");
                    out.println("</html>");
                }
                return;
            } catch (VGBException ex) {
                errors.add(ex.getMessage());
            }
        }
        
        //4.2 產生錯誤回應
        //response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        try {
            /* TODO output your page here. You may use following sample code. */
            out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>登入失敗</title>");
            out.println("<meta charset='UTF-8'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>登入失敗</h1>");
            out.println("<p>"+ errors + "</p>");
            out.println("<input type='button' value='back' onclick='history.back();'>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            if (out != null) {
                out.close();
            }
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
