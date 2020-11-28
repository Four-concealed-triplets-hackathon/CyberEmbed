package controller;
import service.SpiderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

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
        System.out.println(url);
        String retUrl = SpiderService.getEmbed(url);
        String json = "{\"data\":[";
        json += "{\"code\":\"200\"},";
        json += "{\"url\":\""+retUrl+"\"";
        json += "},";
        json = json.substring(0, json.length()-1);
        json += "]}";
        System.out.println(retUrl);
        response.getWriter().write(json);
    }
}
