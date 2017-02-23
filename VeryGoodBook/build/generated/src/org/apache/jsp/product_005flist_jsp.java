package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.verygoodbook.entity.Book;
import com.verygoodbook.entity.Product;
import java.util.List;
import com.verygoodbook.service.ProductService;

public final class product_005flist_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<!--\n");
      out.write("To change this license header, choose License Headers in Project Properties.\n");
      out.write("To change this template file, choose Tools | Templates\n");
      out.write("and open the template in the editor.\n");
      out.write("-->\n");

    request.setCharacterEncoding("UTF-8");
    String search = request.getParameter("search");
    ProductService service = new ProductService();
    List<Product> list = null;
    if(search!=null){
        list = service.getProductsByName(search);
    }else{
        list = service.getProdcutsByType("Book");
        search="";
    }

      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>產品清單</title>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>非常好書<sub>產品清單</sub></h1>\n");
      out.write("        <hr>\n");
      out.write("        <a href=\"");
      out.print( request.getContextPath());
      out.write("/\" >Home</a>\n");
      out.write("        <a href=\"");
      out.print( request.getContextPath());
      out.write("/product_list.jsp\">產品清單</a> \n");
      out.write("         <a href=\"");
      out.print( request.getContextPath());
      out.write("/cart.jsp\">購物車</a> \n");
      out.write("        <hr>\n");
      out.write("        <form method=\"POST\" >\n");
      out.write("            <input type='search' placeholder=\"請輸入完整產品編號或部分名稱...\" style='width:20em' \n");
      out.write("                   name='search' value=\"");
      out.print( search );
      out.write("\">\n");
      out.write("            <input type=\"submit\" value=\"查詢\">\n");
      out.write("        </form> \n");
      out.write("        <!--看看上面有沒有撈出結果-->\n");
      out.write("        ");
      out.write("\n");
      out.write("        <ul>            \n");
      out.write("            ");
 
                if(list!=null && list.size()>0){
                for(Product p:list){ 
                    
            
      out.write("\n");
      out.write("            <li style='display: inline-block;border-style: dotted dashed solid double;padding: 1ex;box-shadow: 1px 1px 3px gray'>\n");
      out.write("                <img src='");
      out.print( p.getPhotoUrl() );
      out.write("'>\n");
      out.write("                <h4>");
      out.print( p.getName() );
      out.write("</h4>\n");
      out.write("                ");
 if(p instanceof Book){ 
      out.write("\n");
      out.write("                    <span>作者：");
      out.print( ((Book)p).getAutherName() );
      out.write("</span><br>\n");
      out.write("                    <span>優惠價：");
      out.print( 100-((Book)p).getDiscount() );
      out.write("折,<span>\n");
      out.write("                ");
 } 
      out.write("\n");
      out.write("                <span>");
      out.print( p.getUnitPrice() );
      out.write("元</span>\n");
      out.write("            </li>\n");
      out.write("            ");
 }} 
      out.write("\n");
      out.write("\n");
      out.write("        </ul>\n");
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
