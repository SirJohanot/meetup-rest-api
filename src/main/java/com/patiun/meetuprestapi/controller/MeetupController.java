package com.patiun.meetuprestapi.controller;

import com.patiun.meetuprestapi.command.Command;
import com.patiun.meetuprestapi.command.factory.CommandFactory;
import com.patiun.meetuprestapi.exception.ElementNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MeetupController extends HttpServlet {

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CommandFactory commandFactory = new CommandFactory();
        String requestMethod = request.getMethod();
        int statusCode = 200;
        String message = "";
        try {
            Command command = commandFactory.createCommand(requestMethod);
            message = command.execute(request);
        } catch (ElementNotFoundException e) {
            statusCode = 404;
            message = "{\n\t\"error\": \"" + e.getMessage() + "\"\n}";
        } catch (Exception e) {
            statusCode = 400;
//            CharArrayWriter cw = new CharArrayWriter();
//            PrintWriter w = new PrintWriter(cw);
//            e.printStackTrace(w);
//            w.close();
//            String trace = cw.toString();
            message = "{\n\t\"error\": \"" + e.getMessage() + "\"\n}";
        } finally {
            response.setStatus(statusCode);
            writeJsonToResponse(message, response);
        }
    }

    private void writeJsonToResponse(String json, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();
    }

}
