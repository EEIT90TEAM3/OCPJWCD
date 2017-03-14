/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.controller;

import com.verygoodbook.entity.Customer;
import com.verygoodbook.entity.Order;
import com.verygoodbook.entity.PaymentType;
import com.verygoodbook.entity.ShippingType;
import com.verygoodbook.entity.ShoppingCart;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/member/check_out.do"})
public class CheckOutServlet extends HttpServlet {

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
        //0. 取出session中的member, cart
        HttpSession session = request.getSession();
        Customer member = (Customer) session.getAttribute("member");
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (member == null || cart == null) {
            response.sendRedirect(request.getContextPath() + "/cart.jsp");
            return;
        }

        List<String> errors = new ArrayList<>();
        //1. 取得表單的資料: //paymentType, shippingType, name, email, phone, address
        //2. 檢查
        //request.setCharacterEncoding("UTF-8"); //已由CharSetFilter完成
        String paymentType = request.getParameter("paymentType");
        if(paymentType==null || 
            !paymentType.matches("[0-"+(PaymentType.values().length-1)+ "]")){
            errors.add("必須選擇[付款方式]");
        }
        
        String shippingType = request.getParameter("shippingType");
        if(shippingType==null || 
            !shippingType.matches("[0-"+(ShippingType.values().length-1)+ "]")){
            System.out.println("貨運Test"+shippingType);
            errors.add("必須選擇[貨運方式]");
        }
        
        String name = request.getParameter("name");
        if(name==null || (name=name.trim()).length()==0){
            errors.add("必須輸入[收件人姓名]");
        }
        
        String email = request.getParameter("email");
        if(email==null || (email=email.trim()).length()==0){
            errors.add("必須輸入[收件人電郵]");
        }
        
        String phone = request.getParameter("phone");
        if(phone==null || (phone=phone.trim()).length()==0){
            errors.add("必須輸入[收件人電話]");
        }
        
        String address = request.getParameter("address");
        if(address==null || (address=address.trim()).length()==0){
            errors.add("必須輸入[收件地址]");
        }       
        
        if (errors.isEmpty()) {
            try {
                //3. 呼叫商業邏輯
                Order order = new Order();
                order.setCustomer(member);
                order.add(cart);
                
                PaymentType pType = PaymentType.values()[Integer.parseInt(paymentType)];
                order.setPaymentType(pType);
                order.setPaymentFee(pType.getFee());
                
                ShippingType shType = ShippingType.values()[Integer.parseInt(shippingType)];
                order.setShippingType(shType);
                order.setShippingFee(shType.getFee());

                order.setReceiverName(name);
                order.setReceiverEmail(email);
                order.setReceiverPhone(phone);
                order.setReceiverAddress(address);
                
                OrderService service = new OrderService();
                service.insert(order);
                
                session.removeAttribute("cart");
                
                //4.1 forward to success page: /member/check_out_ok.jsp
                request.setAttribute("order", order);
                RequestDispatcher dispatcher = 
                    request.getRequestDispatcher("/member/check_out_ok.jsp");
                dispatcher.forward(request, response);
                return;
            } catch (Exception ex) {
                errors.add("建立訂單失敗:" + ex);
            }
        }

        //4.2 forward to /member/check_out.jsp
            request.setAttribute("errors", errors);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/member/check_out.jsp");
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
