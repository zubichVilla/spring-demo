package com.zube.myfancypdfinvoices.web;

import com.zube.myfancypdfinvoices.context.Application;
import com.zube.myfancypdfinvoices.model.Invoice;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MyFancyPdfInvoicesServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

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
			List<Invoice> invoices = Application.invoiceService.findAll();
			response.getWriter().print(Application.objectMapper.writeValueAsString(invoices));
		}

	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {


		if (request.getRequestURI().equalsIgnoreCase("/invoices")) {

			String userId = request.getParameter("user_id");
			// string to integer conversion
			Integer amount = Integer.valueOf(request.getParameter("amount"));

			// new invoice object
			Invoice invoice = Application.invoiceService.create(userId, amount);

			response.setContentType("application/json; charset=UTF-8");

			// convertion invoice object to json with jackson ObjectMapper
			String json = Application.objectMapper.writeValueAsString(invoice);
			response.getWriter().print(json);

		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}

	}

}
