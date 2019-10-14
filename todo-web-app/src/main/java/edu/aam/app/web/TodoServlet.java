package edu.aam.app.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.aam.app.model.Todo;
import edu.aam.app.service.TodoService;

@WebServlet(urlPatterns = "/t2odo.do")
public class TodoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private TodoService todoService = new TodoService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setAttribute("todos", todoService.retrieveTodos());
		request.getRequestDispatcher("/WEB-INF/views/todo.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String todo = request.getParameter("todo");
		String category = request.getParameter("category");
		if ("".equals(todo)) {
			request.setAttribute("errorMessage", "Enter a valid todo");
		} else {
			todoService.addTodo(new Todo(todo, category));
		}
		request.setAttribute("todos", todoService.retrieveTodos());
		request.getRequestDispatcher("/WEB-INF/views/todo.jsp").forward(request, response);
	}
}