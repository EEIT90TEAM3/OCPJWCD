package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Calendar;

public final class cart_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>購物車</title>\n");
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
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/subviews/header.jsp", out, false);
      out.write("\n");
      out.write("        <div id=\"article\">\n");
      out.write("            \n");
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
