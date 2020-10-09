import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Recipes;
import util.Info;
import util.UtilDBMarquis;

@WebServlet("/SearchPage")
public class SearchPage extends HttpServlet implements Info {
	private static final long serialVersionUID = 1L;

	public SearchPage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String keyword = request.getParameter("keyword").trim();

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Database Result";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
		out.println(docType + //
				"<html>\n" + //
				"<head><title>" + title + "</title></head>\n" + //
				"<body bgcolor=\"#f0f0f0\">\n" + //
				"<h1 align=\"center\">" + title + "</h1>\n");
		out.println("<ul>");

		List<Recipes> listRecipes = null;
		if (keyword != null && !keyword.isEmpty()) {
			listRecipes = UtilDBMarquis.listRecipes(keyword);
		} else {
			listRecipes = UtilDBMarquis.listRecipes();
		}
		display(listRecipes, out);
		out.println("</ul>");
		out.println("<a href=/" + projectName + "/" + searchWebName + ">Search Data</a> <br>");
		out.println("</body></html>");
	}

	void display(List<Recipes> listRecipes, PrintWriter out) {
		for (Recipes recipes : listRecipes) {
			System.out.println(recipes.getId() + ", " //
		               + recipes.getName() + ", " //
		               + recipes.getUrl());
						
			out.println("<li><b>Id:</b> " + recipes.getId() + "<br><b>Recipe: </b>" //
					+ recipes.getName() + "<br> " //
					+ "<a href=" + recipes.getUrl() + ">Link</a></li>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
