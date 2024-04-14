package nl.kuba.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/hello-world")
public class MainServlet extends HttpServlet {
    public MainServlet() {
        super();
    }

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        response.getOutputStream().print("Hello from MainServlet4. ");
    }
}
