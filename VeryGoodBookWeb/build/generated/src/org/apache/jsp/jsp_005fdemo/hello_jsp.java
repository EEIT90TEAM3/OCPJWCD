package org.apache.jsp.jsp_005fdemo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Date;
import java.text.DateFormat;

public final class hello_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  public String getServletInfo() {
    return "JSP示範程式";
  }

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
      			"/WEB-INF/error.jsp", true, 3072, true);
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
      out.write("<head>\n");
      out.write("<title>");
      out.print( this.getServletInfo() );
      out.write("</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");

    request.setCharacterEncoding("UTF-8");
    String welcome = "你好";
    DateFormat df;

      out.write("\n");
      out.write("<h1>");
      out.print( welcome );
      out.write("，HelloServlet at /vgb</h1>\n");
      out.write("<p>時間: ");
  out.print(new Date());       
      out.write("</p>\n");
      out.write("<hr>\n");
      out.write("<h2>Implicit Variables</h2>\n");
      out.write("<h3>request</h3>\n");
      out.write("<p> Context Path: ");
      out.print( request.getContextPath() );
      out.write("  </p>\n");
      out.write("<p> RequestURI: ");
      out.print( request.getRequestURI() );
      out.write("  </p>\n");
      out.write("<p> RequestURL: ");
      out.print( request.getRequestURL() );
      out.write("  </p>\n");
      out.write("<p> Method: ");
      out.print( request.getMethod() );
      out.write("  </p>\n");
      out.write("<p> Header('user-agent'): ");
      out.print( request.getHeader("user-agent") );
      out.write(" </p>\n");
      out.write("<p> Parameter('tesr'): ");
      out.print( request.getParameter("test") );
      out.write(" </p>\n");
      out.write("<form method='POST'>\n");
      out.write("    <fieldset>\n");
      out.write("    <input name='test'><br>\n");
      out.write("    <input type='submit'>\n");
      out.write("    </fieldset>\n");
      out.write("</form>\n");
      out.write("<hr>\n");
      out.write("<h3>response</h3>\n");
      out.write("<p>status code:  ");
      out.print( response.getStatus() );
      out.write(" </p>    \n");
      out.write("<p>contentType:  ");
      out.print( response.getContentType() );
      out.write(" </p>\n");
      out.write("<p>buffer size:  ");
      out.print( response.getBufferSize() );
      out.write(" </p>\n");
      out.write("<hr>\n");
      out.write("<h3>out</h3>\n");
      out.write("<p>Math.round(4.5):  ");
      out.print( Math.round(4.5) );
      out.write(" </p>\n");
      out.write("<p>println:  ");
 out.println("test"); 
      out.write(" </p>\n");
      out.write("<hr>\n");
      out.write("<h3>exception</h3>\n");
      out.write("\n");
      out.write("<hr><hr>\n");
      out.write("<h2>Implicit Variables</h2>\n");
      out.write("<h3>request</h3>\n");
 
    out.flush(); 
    Thread.sleep(1000);

      out.write("\n");
      out.write("<p> Context Path: ");
      out.print( request.getContextPath() );
      out.write("  </p>\n");
      out.write("<p> RequestURI: ");
      out.print( request.getRequestURI() );
      out.write("  </p>\n");
      out.write("<p> RequestURL: ");
      out.print( request.getRequestURL() );
      out.write("  </p>\n");
      out.write("<p> Method: ");
      out.print( request.getMethod() );
      out.write("  </p>\n");
      out.write("<p> Header('user-agent'): ");
      out.print( request.getHeader("user-agent") );
      out.write(" </p>\n");
      out.write("<p> Parameter('tesr'): ");
      out.print( request.getParameter("test") );
      out.write(" </p>\n");
      out.write("\n");
      out.write("<form method='POST'>\n");
      out.write("    <fieldset>\n");
      out.write("    <input name='test'><br>\n");
      out.write("    <input type='submit'>\n");
      out.write("    </fieldset>\n");
      out.write("</form>\n");
      out.write("<hr>\n");
      out.write("<h3>response</h3>\n");
      out.write("<p>status code:  ");
      out.print( response.getStatus() );
      out.write(" </p>    \n");
      out.write("<p>contentType:  ");
      out.print( response.getContentType() );
      out.write(" </p>\n");
      out.write("<p>buffer size:  ");
      out.print( response.getBufferSize() );
      out.write(" </p>\n");
      out.write("<hr>\n");
      out.write("<h3>out</h3>\n");
      out.write("<p>Math.round(4.5):  ");
      out.print( Math.round(4.5) );
      out.write(" </p>\n");
      out.write("<p>println:  ");
 out.println("test"); 
      out.write(" </p>\n");
      out.write("<hr>\n");
      out.write("</body>\n");
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
