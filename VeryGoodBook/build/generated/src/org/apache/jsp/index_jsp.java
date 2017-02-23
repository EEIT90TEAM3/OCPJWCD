package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.verygoodbook.entity.Customer;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>首頁</title>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <script src='https://code.jquery.com/jquery-1.12.4.js'></script>\n");
      out.write("        <script>\n");
      out.write("            function  logout() {\n");
      out.write("                $.ajax({\n");
      out.write("                    url: \"");
      out.print( request.getContextPath());
      out.write("/logout.do\",\n");
      out.write("                    method: \"POST\"\n");
      out.write("                }).done(doneHandler);\n");
      out.write("            }\n");
      out.write("            function doneHandler(result) {\n");
      out.write("                alert(\"已登出成功!\");\n");
      out.write("                $(\"#member-span\").text(\"您好!\");\n");
      out.write("                $(\"#member-span\").html('<a href=\"");
      out.print( request.getContextPath());
      out.write("/register.jsp\">會員註冊</a>|' +\n");
      out.write("                        ' <a href=\"");
      out.print( request.getContextPath());
      out.write("/login.jsp\">會員登入</a>|');\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div id=\"header\">\n");
      out.write("            <h1>非常好書</h1>\n");
      out.write("            <hr>\n");
      out.write("        </div>\n");
      out.write("        <div id=\"nav\">\n");
      out.write("            <a ");
      out.print( request.getContextPath());
      out.write(" >Home</a>\n");
      out.write("            <a href=\"");
      out.print( request.getContextPath());
      out.write("/product_list.jsp\">產品清單</a> \n");
      out.write("            <a href=\"");
      out.print( request.getContextPath());
      out.write("/cart.jsp\">購物車</a> \n");
      out.write("            <span id=\"member-span\">\n");
      out.write("                ");

                    Customer member = (Customer) session.getAttribute("member");
                    if (member == null) {
                
      out.write("\n");
      out.write("                <a href=\"");
      out.print( request.getContextPath());
      out.write("/register.jsp\">會員註冊</a>|\n");
      out.write("                <a href=\"");
      out.print( request.getContextPath());
      out.write("/login.jsp\">會員登入</a>|\n");
      out.write("                ");
 } else {
      out.write("\n");
      out.write("                <a href=\"");
      out.print( request.getContextPath());
      out.write("/member/update.jsp\">會員修改</a>|\n");
      out.write("                <a href=\"");
      out.print( request.getContextPath());
      out.write("/member/order_history.jsp\">歷史訂單</a>|\n");
      out.write("                <a href=\"");
      out.print( request.getContextPath());
      out.write("/logout.do\">會員登出</a>|\n");
      out.write("                <a href=\"javascript:logout()\">會員登出(ajax)</a>\n");
      out.write("                ");
 }
      out.write("\n");
      out.write("            </span>\n");
      out.write("            <span style=\"font-size: smaller;float:right\" id=\"welcome-span\">\n");
      out.write("                您好!");
      out.print( (member != null) ? member.getName() : "");
      out.write("\n");
      out.write("            </span>\n");
      out.write("            <hr>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <!--<img src=\"http://www.santabarbararen.com/wp-content/uploads/2016/08/2.jpg\">-->\n");
      out.write("        <div id=\"article\" style=\"height: 75vh\">\n");
      out.write("            <img src=\"https://tse2.mm.bing.net/th?id=OIP.5cyw4YEtFwgG1MmgqBIKnQEsDA&pid=15.1&P=0&w=276&h=178\"><br>\n");
      out.write("            <audio controls>\n");
      out.write("\n");
      out.write("            </audio>\n");
      out.write("        </div>\n");
      out.write("        <div id=\"footer\">\n");
      out.write("            <address>版權所有 &copy; 非常好書</address>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
