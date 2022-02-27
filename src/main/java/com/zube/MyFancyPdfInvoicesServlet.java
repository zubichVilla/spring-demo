package com.zube;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyFancyPdfInvoicesServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		//	getRequestURI()
		//          Returns the part of this request's URL from the protocol name
		//          up to the query string in the first line of the HTTP request.
		if(request.getRequestURI().equalsIgnoreCase("/")){
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().print(
					"<html>\n" +
							"<body>\n" +
							"<h1>Hello World</h1>\n" +
							"<p>This is my very first, embedded Tomcat, HTML Page!</p>\n" +
							"</body>\n" +
							"</html>"
			);
		}else if(request.getRequestURI().equalsIgnoreCase("/invoices")){
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().print("[]"); // vracamo prazni kao test

			// ovo je za master
		}

	}

}
