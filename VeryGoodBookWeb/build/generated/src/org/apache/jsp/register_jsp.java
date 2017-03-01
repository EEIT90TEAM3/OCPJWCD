package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.verygoodbook.entity.Customer;
import java.util.List;
import java.util.Calendar;

public final class register_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/WEB-INF/subviews/global_js.jsp");
    _jspx_dependants.add("/WEB-INF/subviews/footer.jsp");
  }

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
      out.write("<!DOCTYPE html>\n");
      out.write("<!--\n");
      out.write("To change this license header, choose License Headers in Project Properties.\n");
      out.write("To change this template file, choose Tools | Templates\n");
      out.write("and open the template in the editor.\n");
      out.write("-->\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>會員註冊</title>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css\">\n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<script src='https://code.jquery.com/jquery-1.12.4.js'></script>\n");
      out.write("<script>\n");
      out.write("    function logout() {\n");
      out.write("        $.ajax({\n");
      out.write("            url: '");
      out.print( request.getContextPath());
      out.write("/logout.do',\n");
      out.write("            method: 'POST'\n");
      out.write("        }).done(doneHandler);\n");
      out.write("    }\n");
      out.write("    function doneHandler(result) {\n");
      out.write("        alert(\"已登出成功!\");\n");
      out.write("        $(\"#welcome-span\").text(\"你好! \");\n");
      out.write("        $(\"#member-span\").html('<a href=\"");
      out.print(request.getContextPath());
      out.write("/register.jsp\">會員註冊</a> | ' +\n");
      out.write("                '<a href=\"");
      out.print(request.getContextPath());
      out.write("/login.jsp\">會員登入</a> | ');\n");
      out.write("    }\n");
      out.write("</script>");
      out.write("    \n");
      out.write("        <script src=\"https://code.jquery.com/ui/1.12.1/jquery-ui.js\"></script>\n");
      out.write("        <script src=\"https://jqueryui.com/resources/demos/datepicker/i18n/datepicker-zh-TW.js\"></script>\n");
      out.write("        <script>\n");
      out.write("            ");
 if (request.getHeader("user-agent").indexOf("Chrome") < 0) { 
      out.write("\n");
      out.write("            $(function () {\n");
      out.write("                $(\"#birthday\").datepicker({\n");
      out.write("                    changeMonth: true,\n");
      out.write("                    changeYear: true,\n");
      out.write("                    dateFormat: \"yy-mm-dd\"\n");
      out.write("                });\n");
      out.write("            });\n");
      out.write("            ");
 } 
      out.write("\n");
      out.write("        </script>        \n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/subviews/header.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("subtitle", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("會員註冊", request.getCharacterEncoding()), out, false);
      out.write("\n");
      out.write("        <div id=\"article\">\n");
      out.write("            ");

                List<String> errors = (List<String>) request.getAttribute("errors");
                if (errors != null) {
            
      out.write("\n");
      out.write("            ");
      out.print( errors);
      out.write("\n");
      out.write("            ");
}
      out.write("\n");
      out.write("            <form action='register.do' method='POST'>\n");
      out.write("                <!--<div name='hello'>Hello</div>--> \n");
      out.write("                <p>\n");
      out.write("                    <label for='uid'>會員帳號:</label>\n");
      out.write("                    <input type='text' name='userid' id='uid' placeholder=\"請輸入身分證號\" required \n");
      out.write("                           value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.userid}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" pattern='[A-Z][12][0-9]{8}'>\n");
      out.write("                </p>\n");
      out.write("                <p> \n");
      out.write("                    <label for='pwd1'>會員密碼:</label>\n");
      out.write("                    <input type='password' name='password1' id='pwd1' required minlength=\"6\" maxlength=\"20\"><br>\n");
      out.write("                    <label for='pwd2'>確認密碼:</label>\n");
      out.write("                    <input type='password' name='password2' id='pwd2' required minlength=\"6\" maxlength=\"20\">\n");
      out.write("                </p>\n");
      out.write("                <p>\n");
      out.write("                    <label for='name'>會員姓名:</label>\n");
      out.write("                    <input type='text' name='name' id='name' required value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.name}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("                </p>\n");
      out.write("                <p>\n");
      out.write("                    <label for='email'>電子郵件:</label>\n");
      out.write("                    <input type='email' name='email' id='email' required\n");
      out.write("                           value=\"");
      out.print( request.getParameter("email") == null ? "" : request.getParameter("email"));
      out.write("\">           \n");
      out.write("                </p>\n");
      out.write("                <p>\n");
      out.write("                    <label for='gender'>會員性別:</label>\n");
      out.write("                    <input type='radio' name='gender' value='M' id='male' required \n");
      out.write("                           ");
      out.print( request.getParameter("gender") == null
                                   || (request.getParameter("gender").charAt(0) == Customer.FEMALE) ? "" : "checked");
      out.write("><label for='male'>男</label>\n");
      out.write("                    <input type='radio' name='gender' value='F' id='female' required\n");
      out.write("                           ");
      out.print( request.getParameter("gender") == null
                                   || (request.getParameter("gender").charAt(0) == Customer.MALE) ? "" : "checked");
      out.write("\n");
      out.write("                           ><label for='female'>女</label>\n");
      out.write("                </p>\n");
      out.write("\n");
      out.write("                <p>\n");
      out.write("                    <label for='birthday'>出生日期:</label>\n");
      out.write("                    <input type='date' name='birthday' id='birthday'\n");
      out.write("                           value=\"");
      out.print( request.getParameter("birthday") == null ? "" : request.getParameter("birthday"));
      out.write("\">\n");
      out.write("                </p>\n");
      out.write("                <p>\n");
      out.write("                    <label for='phone'>連絡電話:</label>\n");
      out.write("                    <input type='tel' name='phone' id='phone'\n");
      out.write("                           value=\"");
      out.print( request.getParameter("phone") == null ? "" : request.getParameter("phone"));
      out.write("\">\n");
      out.write("                </p>            \n");
      out.write("                <p>\n");
      out.write("                    <label for='address'>連絡地址:</label>\n");
      out.write("                    <input type='text' name='address' id='address'\n");
      out.write("                           value=\"");
      out.print( request.getParameter("address") == null ? "" : request.getParameter("address"));
      out.write("\">\n");
      out.write("                </p>\n");
      out.write("                <p>\n");
      out.write("                    <label for='married'>婚姻狀況:</label>\n");
      out.write("                    <input type='checkbox' name='married' id='married' \n");
      out.write("                           ");
      out.print( request.getParameter("married") == null ? "" : "checked");
      out.write("><label for='married'>已婚</label>\n");
      out.write("                </p>\n");
      out.write("                <p>\n");
      out.write("                    <a href=\"javascript:refreshImage()\"  title='點選即可更新圖片'>\n");
      out.write("                        <img src='images/register_check.jpg' id='check_img'>\n");
      out.write("                    </a>\n");
      out.write("                    <label for='check_code'>驗證碼:</label>\n");
      out.write("                    <input type='text' name='checkCode' id='check_code' required\n");
      out.write("                           value=\"");
      out.print( request.getParameter("checkCode") == null ? "" : request.getParameter("checkCode"));
      out.write("\">\n");
      out.write("                    <script>\n");
      out.write("                        function refreshImage() {\n");
      out.write("                            var myImg = document.getElementById(\"check_img\");\n");
      out.write("                            myImg.src = \"images/register_check.jpg?get=\" + new Date();\n");
      out.write("                        }\n");
      out.write("                    </script>                  \n");
      out.write("                </p>\n");
      out.write("                <input type='submit' value='確定註冊' name='submit'>\n");
      out.write("            </form>   \n");
      out.write("        </div>\n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("        <div id=\"footer\">\n");
      out.write("            <hr>\n");
      out.write("            版權所有 &copy; 非常好書 2015~");
      out.print( Calendar.getInstance().get(Calendar.YEAR) );
      out.write(" <span style=\"float:right\">拜訪人次: ");
      out.print( application.getAttribute("app.visitors.counter"));
      out.write("</span>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
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
