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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        HttpSession session = request.getSession();
        //1.讀取請求傳來的表單資料:userid,password,checkCode
        request.setCharacterEncoding("UTF-8");/*!重要,為了處理編碼問題*/
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");

        //2.檢查資料
        List<String> errors = new ArrayList<>();
        // (userid=userid.trim())，要加小括號才是去空白
        if (userid == null || (userid=userid.trim()).length() == 0) {
            errors.add("必須輸入帳號");
        }
        if (password == null || (password=password.trim()).length() == 0) {
            errors.add("必須輸入密碼");
        }
        if (checkCode == null || (checkCode=checkCode.trim()).length() == 0) {
            errors.add("必須輸入驗證碼");
        }else{
            String rand =(String)session.getAttribute("ImageCheckServlet");
            //equalsIgnoreCase:大小寫都OK
            if(!checkCode.equalsIgnoreCase(rand)){
                
                errors.add("驗證碼不正確");
            }
        }
        
        if (errors.isEmpty()) {
            //不管登入成不成功都刪掉驗證碼
            session.removeAttribute("ImageCheckServlet");
            //3.呼叫執行商業邏輯CustomerService login
            CustomerService service = new CustomerService();
            try {
                Customer c = service.login(userid, password);
                /*登入成功驗證碼才刪掉*/
                session.removeAttribute("ImageCheckServlet");
                //4.1 forward畫面控制權交給login_ok.jsp
//                RequestDispatcher dispatcher=request.getRequestDispatcher("index.jsp");
                //把客戶資料塞到請求中!
                session.setAttribute("member", c);
                
                //forward轉交程式(把畫面控制權完全轉交)
//                dispatcher.forward(request, response);
                  //轉交給首頁  
                  response.sendRedirect(request.getContextPath());
                return;
            } catch (VGBException ex) {
                errors.add(ex.toString());
            }

        }

        //4.2產生錯誤回應
        RequestDispatcher dispatcher=request.getRequestDispatcher("login.jsp");
//        RequestDispatcher dispatcher=request.getRequestDispatcher("login_fail.jsp");
        request.setAttribute("errors", errors);
        dispatcher.forward(request, response);
        //78~102:原來Servlet自行輸出畫面
//        response.setContentType("text/html;charset=UTF-8");/*(!)重要，一定要在getWtrite之前*/
// /*同上一行，但拆成兩個*/
////        response.setContentType("text/html");
////        response.setCharacterEncoding("UTF-8");
//        PrintWriter out=null;       
//        try {
//            /* TODO output your page here. You may use following sample code. */
//            out = response.getWriter();
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>登入失敗</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>登入失敗</h1>");
//            out.println("<p>"+errors+"</p>");
//            out.println("<input type='button' value='back' onclick='history.back()'>");            
//            out.println("</body>");
//            out.println("</html>");
//        }finally{
//            if(out !=null){
//                out.close();
//            }
//        }
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
