//package edu.aam.app.web;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import edu.aam.app.service.TodoService;
//
//@WebServlet(urlPatterns = "/list-todos.do")
//public class ListTodoServlet extends HttpServlet {
//
//	private static final long serialVersionUID = 1L;
//
//	private TodoService todoService = new TodoService();
//
//	protected void doGet(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		request.setAttribute("todos", todoService.retrieveTodos());
//		request.getRequestDispatcher("/WEB-INF/views/list-todos.jsp").forward(
//				request, response);
//	}
//}