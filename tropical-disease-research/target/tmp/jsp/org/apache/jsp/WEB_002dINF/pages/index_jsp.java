package org.apache.jsp.WEB_002dINF.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

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
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"zh-CN\">\n");
      out.write("<head>\n");
      out.write("  <meta charset=\"utf-8\">\n");
      out.write("  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("  <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->\n");
      out.write("  <title>SpringMVC Demo 首页</title>\n");
      out.write("\n");
      out.write("  <!-- 新 Bootstrap 核心 CSS 文件 -->\n");
      out.write("  <link rel=\"stylesheet\" href=\"//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css\">\n");
      out.write("\n");
      out.write("  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->\n");
      out.write("  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->\n");
      out.write("  <!--[if lt IE 9]>\n");
      out.write("  <script src=\"//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js\"></script>\n");
      out.write("  <script src=\"//cdn.bootcss.com/respond.js/1.4.2/respond.min.js\"></script>\n");
      out.write("  <![endif]-->\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<h1>这里是SpringMVC Demo首页</h1>\n");
      out.write("\n");
      out.write("<h3>出现此页面，说明配置成功。</h3>\n");
      out.write("\n");
      out.write("<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->\n");
      out.write("<script src=\"//cdn.bootcss.com/jquery/1.11.3/jquery.min.js\"></script>\n");
      out.write("\n");
      out.write("<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->\n");
      out.write("<script src=\"//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js\"></script>\n");
      out.write("</body>\n");
      out.write("</html>");
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
