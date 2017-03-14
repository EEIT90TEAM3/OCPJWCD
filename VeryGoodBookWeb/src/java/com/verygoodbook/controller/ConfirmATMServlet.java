/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.controller;

import com.verygoodbook.entity.Customer;
import com.verygoodbook.service.OrderService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "ConfirmATMServlet", urlPatterns = {"/member/confirmATM.do"})
public class ConfirmATMServlet extends HttpServlet {

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
        //0. 取得session中的member
        Customer member = (Customer)request.getSession().getAttribute("member");
        if(member==null){
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        
        List<String> errors = new ArrayList<>();
        //1. 取得form data: orderId, bank, last5Code, transferDate, transferTime, amount, //2. 檢查
        String orderId = request.getParameter("orderId");
        if (orderId == null || !orderId.matches("\\d+")) {
            errors.add("訂單編號格式不正確");
        }

        String bank = request.getParameter("bank");
        if (bank == null || bank.length() == 0) {
            errors.add("必須輸入轉帳銀行名稱");
        }

        String last5Code = request.getParameter("last5Code");
        if (last5Code == null || !last5Code.matches("\\d{5}")) {
            errors.add("必須輸入正確的轉帳帳號後5碼");
        }

        String transferDate = request.getParameter("transferDate");
        if (transferDate == null || transferDate.length() == 0) {
            errors.add("必須輸入轉帳日期");
        }
        String transferTime = request.getParameter("transferTime");
        if (transferTime == null || transferTime.length() == 0) {
            errors.add("必須選擇大約的轉帳時間");
        }

        String amount = request.getParameter("amount");
        if (amount == null || amount.length() == 0) {
            errors.add("必須輸入正確的轉帳金額");
        }

        if (errors.isEmpty()) {
            //3.執行商業邏輯
            try{
                int id = Integer.parseInt(orderId);
                OrderService service = new OrderService();
                service.updateNew2Paid(id, member.getId(), bank, last5Code, 
                                            transferDate, transferTime, amount);                
                
            //4.1 redirect order_history.jsp畫面
                response.sendRedirect( request.getContextPath() + "/member/order_history.jsp");
                return;            
            }catch(Exception ex){
                ex.printStackTrace();
                errors.add("[通知已付款]失敗"+ex);
            }
        }

        //4.2 forward /member/confirmATM.jsp畫面
        request.setAttribute("errors", errors);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/member/confirmATM.jsp");
        dispatcher.forward(request, response);
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
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
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
