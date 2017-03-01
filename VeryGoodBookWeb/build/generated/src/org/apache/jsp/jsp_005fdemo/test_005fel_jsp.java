package org.apache.jsp.jsp_005fdemo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class test_005fel_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>EL Demo</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>EL Operators</h1>\n");
      out.write("        <p>2+3: ");
      out.print( 2+3 );
      out.write("</p>\n");
      out.write("        <p>2+3: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ 2+3 }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</p>\n");
      out.write("\n");
      out.write("        <p>'2' + '3': ");
      out.print( "2.1" + '3' );
      out.write("</p>\n");
      out.write("        <p>'a' + 'b': ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ '2.1' < '3' }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</p>\n");
      out.write("        <p>2<1 && 2>3: ");
      out.print( '2'<'1' && '2'>3 );
      out.write("</p>\n");
      out.write("        <p>'2' > '1' && '2.5' < '3': ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${'2' > '1' && '2.5' < '3'}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</p>\n");
      out.write("        <p>'張': ");
      out.print( '張' );
      out.write("</p>\n");
      out.write("        <p>'張': ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${'張'}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</p>\n");
      out.write("        \n");
      out.write("        <p>2<3?'very'+'good':'bad': ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${2<3?'good':'bad'}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</p>\n");
      out.write("        \n");
      out.write("        <hr>\n");
      out.write("        <h1>EL Implicit Variable- param</h1>\n");
      out.write("        <p>param.name: ");
      out.print( request.getParameter("name")==null?"":request.getParameter("name") );
      out.write("</p>\n");
      out.write("        <p>param.name: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.name}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</p>\n");
      out.write("        <p>param[\"name\"]: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param[\"name\"]}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</p>\n");
      out.write("        <hr>\n");
      out.write("        <h1>EL Implicit Variable- header</h1>\n");
      out.write("        <p>user-agent: ");
      out.print( request.getHeader("user-agent") );
      out.write("</p>\n");
      out.write("        <p>user-agent: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${header[\"user-agent\"]}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</p>        \n");
      out.write("        <hr>\n");
      out.write("        <h1>EL Implicit Variable- pageContext</h1>\n");
      out.write("        <p>param.name: ");
      out.print( pageContext.getRequest().getParameter("name")==null?"":request.getParameter("name") );
      out.write("</p>\n");
      out.write("        <p>Context Path: ");
      out.print( request.getContextPath() );
      out.write("</p>\n");
      out.write("        <p>Context Path: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</p>\n");
      out.write("        <hr>\n");
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
