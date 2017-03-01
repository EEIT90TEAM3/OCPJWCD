package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import java.util.Calendar;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>會員登入</title>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
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
      out.write("  \n");
      out.write("        <script>\n");
      out.write("            function refreshImage(){\n");
      out.write("                var myImg = document.getElementById(\"check_img\");\n");
      out.write("                myImg.src = \"images/check.jpg?get=\" + new Date();\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/subviews/header.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("subtitle", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("Login", request.getCharacterEncoding()), out, false);
      out.write("\n");
      out.write("        <div id=\"article\">\n");
      out.write("        ");

            List<String> errors = (List<String>)request.getAttribute("errors");
            if(errors!=null && errors.size()>0){
        
      out.write("\n");
      out.write("        <ul>\n");
      out.write("            ");
 for(int i=0;i<errors.size();i++) { 
      out.write("\n");
      out.write("            <li>");
      out.print( errors.get(i) );
      out.write("</li>\n");
      out.write("            ");
 } 
      out.write("\n");
      out.write("        </ul>\n");
      out.write("        ");
}
      out.write("\n");
      out.write("        ");

            //以下為讀取Cookie的示範
            String auto = "";
            String uid =  "";
            
            Cookie[] cookiesArray = request.getCookies();
            if(cookiesArray!=null && cookiesArray.length>0){
                for(Cookie c: cookiesArray){
                    if(c.getName().equals("uid")){
                      uid = c.getValue();                              
                    }else if(c.getName().equals("auto")){
                        auto = "checked";
                    }
                }
            }//以上為讀取Cookie的示範
        
      out.write("\n");
      out.write("        <form action='login.do' method='POST'>\n");
      out.write("            <!--<div name='hello'>Hello</div>--> \n");
      out.write("            <p>\n");
      out.write("                <label for='uid'>會員帳號:</label>\n");
      out.write("                <input type='text' name='userid' \n");
      out.write("                       value=\"");
      out.print( request.getParameter("userid")==null?uid:request.getParameter("userid") );
      out.write("\" \n");
      out.write("                       id='uid' required>\n");
      out.write("                <input type=\"checkbox\" name=\"auto\" id=\"auto\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${cookie.auto.value}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("><label for=\"auto\">記住帳號</label>\n");
      out.write("            </p>\n");
      out.write("            <p>\n");
      out.write("                <label for='pwd'>會員密碼:</label>\n");
      out.write("                <input type='password' name='password' id='pwd' required>\n");
      out.write("            </p>\n");
      out.write("            <p>\n");
      out.write("                <a href=\"javascript:refreshImage()\"  title='點選即可更新圖片'>\n");
      out.write("                    <img src='images/check.jpg' id='check_img'>\n");
      out.write("                </a>\n");
      out.write("                <label for='check_code'>驗證碼:</label>\n");
      out.write("                <input type='text' name='checkCode' id='check_code' \n");
      out.write("                       value=\"");
      out.print( request.getParameter("checkCode")==null?"":request.getParameter("checkCode") );
      out.write("\"\n");
      out.write("                       required>\n");
      out.write("            </p>\n");
      out.write("            <input type='submit' value='確定登入'>\n");
      out.write("        </form>\n");
      out.write("        </div>\n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("        <div id=\"footer\">\n");
      out.write("            <hr>\n");
      out.write("            版權所有 &copy; 非常好書 2015~");
      out.print( Calendar.getInstance().get(Calendar.YEAR) );
      out.write(" \n");
      out.write("            <span style=\"float:right\">\n");
      out.write("                拜訪人次: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ applicationScope[\"app.visitors.counter\"]}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\n");
      out.write("            </span>\n");
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
