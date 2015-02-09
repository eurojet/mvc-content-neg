<HTML>
<%-- /*

	TestServlet - This servlet shows how a servlet is initialized,
	              how it can retrieve info from the request and servlet context
	              and how it is destroyed.

*/ --%>
<%@ page import="java.io.*,java.util.*" %><%!

	// This method is called when the servlet is initialized.
	// It is used to initialize any resources which are needed by the servlet.
    public void jspInit() {
		log( "TestServlet init() method called." );
		
		// Retrieve and display the init arguments for this servlet
		Enumeration names = getServletConfig().getInitParameterNames();		
		if ( names != null )
		{
			while ( names.hasMoreElements() )
			{
				String name  = (String)names.nextElement();
				String value = getServletConfig().getInitParameter( name );
				
				log( "TestServlet init argument: [" + name + "] = [" + value + "]" );
				
			}
		}
		else
		{
			log( "TestServlet has no init arguments" );
		}
	}

	public String getServletInfo() {
		return "TestServlet returns info from the request and servlet context.";
	}	
	
	// This method is called when the servlet is destroyed.
    public void jspDestroy() {
		log( "TestServlet destroy() method called." );
	}	

%><%
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", 
                            "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", 
//"x-requested-with");
"origin, content-type, x-requested-with");
%>


<HEAD><TITLE> Properties JSP Output</TITLE></HEAD><BODY>
<h1> Properties Java Server Pages Output: </h1>
<%
    String[] propertyNames = {"file.separator",
                              "line.separator",
                              "path.separator",
                              "java.class.version",
                              "java.vendor",
                              "java.vendor.url",
                              "java.version",
                              "os.name",
                              "os.arch",
                              "os.version",
                              "java.class.path",
                              "java.home",
                              "user.dir",
                              "user.home",
                              "user.name",
                              "file.encoding",
                              "user.language", 
                              "user.region",
                              "user.timezone",};  

      for (int i = 0; i < propertyNames.length; i++) {
          out.println("<br>"+propertyNames[i]+" = "+System.getProperty(propertyNames[i])); 
        } 
%>


<h1>Request Output</h1>
<p><b>Here is the request information:</b>
<p><table border=1 cellpadding=3>
<tr><td><b>Method</b></td><td><%= request.getMethod() %></td></tr>
<tr><td><b>URI</b></td><td><%= request.getRequestURI() %></td></tr>
<tr><td><b>Protocol</b></td><td><%= request.getProtocol() %></td></tr>
<tr><td><b>Servlet Path</b></td><td><%= request.getServletPath() %></td></tr>
<tr><td><b>Path Info</b></td><td><%= request.getPathInfo() %></td></tr>
<tr><td><b>Path Translated</b></td><td><%= request.getPathTranslated() %></td></tr>
<tr><td><b>Query String</b></td><td><%= request.getQueryString() %></td></tr>
<tr><td><b>Scheme</b></td><td><%= request.getScheme() %></td></tr>
<tr><td><b>Content Length</b></td><td><%= request.getContentLength() %></td></tr>
<tr><td><b>Content Type</b></td><td><%= request.getContentType() %></td></tr>
<tr><td><b>Char Encoding</b></td><td><%= request.getCharacterEncoding() %></td></tr>
<tr><td><b>Server Name</b></td><td><%= request.getServerName() %></td></tr>
<tr><td><b>Server Port</b></td><td><%= request.getServerPort() %></td></tr>
<tr><td><b>Remote Addr</b></td><td><%= request.getRemoteAddr() %></td></tr>
<tr><td><b>Remote User</b></td><td><%= request.getRemoteUser() %></td></tr>
<tr><td><b>Auth Type</b></td><td><%= request.getAuthType() %></td></tr>
</table>


<%
	// Display the HTTP request headers
	Enumeration enumera = request.getHeaderNames();
	if ( ( enumera != null ) && ( enumera.hasMoreElements() ) )
	{		
		out.println( "<p><b>Here are the request headers:</b><p><table border=1 cellpadding=3>" );
		while ( enumera.hasMoreElements() )
		{
			String name = (String)enumera.nextElement();
			out.println( "<tr><td><b>" + name + "</b></td><td>" + request.getHeader( name ) + "</td></tr>" );
		}
		out.println( "</table>" );
	} else {
		out.println( "<p><b>There are no request headers.</b>" );
	}

		// Display the Parameters
                enumera = request.getParameterNames();
		if ( enumera.hasMoreElements() )
		{	
PrintWriter pw = new PrintWriter(
                new OutputStreamWriter(System.out, "8859_15"),  true);	
			out.println( "<p><b>Los parámetros son:</b><p><table border=1 cellpadding=3>" );
			while ( enumera.hasMoreElements() )
			{
				String name = (String)enumera.nextElement();			
				out.println( "<tr><td><b>" + name + "</b></td><td>" + request.getParameter( name ) + "</td></tr>" );
pw.println(request.getCharacterEncoding()+" "+request.getParameter( name ));
			}
			out.println( "</table>" );
		} else {
			out.println( "<p><b>NO HAY parametros.</b>" );
		}

		// Display the Attributes
                enumera = request.getAttributeNames();
		if ( enumera.hasMoreElements() )
		{		
			out.println( "<p><b>Los atributos son:</b><p><table border=1 cellpadding=3>" );
			while ( enumera.hasMoreElements() )
			{
				String name = (String)enumera.nextElement();			
				out.println( "<tr><td><b>" + name + "</b></td><td>" + request.getParameter( name ) + "</td></tr>" );
			}
			out.println( "</table>" );
		} else {
			out.println( "<p><b>NO HAY atributos.</b>" );
		}


                ServletContext co = application;
//getServletConfig().getServletContext();
		// Display information from the Servlet Context 
		out.println( "<p><b>Here is information from the Servlet Context:</b><p><table border=1 cellpadding=3>" ); 
		out.println( "<tr><td><b>Servlet API Major Version</b></td><td>" 	+ co.getMajorVersion()			+ "</td></tr>" ); 
		out.println( "<tr><td><b>Servlet API Minor Version</b></td><td>" 	+ co.getMinorVersion()			+ "</td></tr>" ); 
		out.println( "<tr><td><b>Mime Type for *.hqx Files</b></td><td>" 	+ co.getMimeType( "file.hqx" )	+ "</td></tr>" ); 
		out.println( "<tr><td><b>Root Document Directory</b></td><td>"		+ co.getRealPath( "/" ) 		+ "</td></tr>" ); 
		out.println( "<tr><td><b>Server Info</b></td><td>" 			+ co.getServerInfo() 			+ "</td></tr>" ); 
		out.println( "</table>" ); 
		

%>


</BODY></HTML>

