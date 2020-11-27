package controller;
import service.SpiderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class CyberServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private SpiderService spiderService = new SpiderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String requestURI = request.getRequestURI();
        String methodName = requestURI.substring(requestURI.lastIndexOf("/") + 1);

        try {
            Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generatePic(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String url = request.getParameter("url");

    }
}
