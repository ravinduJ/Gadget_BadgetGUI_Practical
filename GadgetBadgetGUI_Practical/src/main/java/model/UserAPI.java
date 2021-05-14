package model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@WebServlet("/UserAPI")
public class UserAPI extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	UserServlet userobj = new UserServlet();
	
	public UserAPI() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String output  = userobj.createPost(request.getParameter("username"),
				request.getParameter("email"),
				request.getParameter("dob"),
				request.getParameter("type")
				);
		response.getWriter().write(output);
	}
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map<String, String> paras = getParasMap(request);

		String outputString = userobj.updatePost(
				paras.get("userid").toString(),
				paras.get("username").toString(),
				paras.get("password").toString(), 
				paras.get("email").toString(),
				paras.get("type").toString());
				

		response.getWriter().write(outputString);
	}
	
	
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		Map<String, String> paras = getParasMap(request);
		String output = userobj.deletePost(paras.get("userid").toString());
		response.getWriter().write(output); 		
	}

	// Convert request parameters to a Map
		private static Map<String, String> getParasMap(HttpServletRequest request) {
			
			Map<String, String> map = new HashMap<String, String>();
			
			try {			
				Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
				String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
				scanner.close();
				
				String[] params = queryString.split("&");
				for (String param : params) {
					String[] p = param.split("=");
					map.put(p[0], p[1]);
				}
				
			} catch (Exception e) {
			  }
			
			return map;
		}
	
	
	
	
}
