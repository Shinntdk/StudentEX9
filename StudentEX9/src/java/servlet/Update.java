/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import database.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author natthidak
 */
public class Update extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("name");
        double gpa = Double.parseDouble(request.getParameter("gpa"));
        int id = Integer.parseInt(request.getParameter("id"));
        Student stu = StudentTable.findStudentById(id);
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ArrayList<Integer> tmp;
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Update</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Update Student</h1>");
            if (stu != null) {
                synchronized(getServletContext()) {
                //synchronized(session) {
                    //getServletContext().setAttribute("id", ids);
                    tmp = (ArrayList<Integer>) getServletContext().getAttribute("id");
                    if (tmp == null) {
                        ids.add(id);
                        getServletContext().setAttribute("id", ids);
                        //Random rand = new Random();
                        int num = 10;
                        Thread.sleep(num * 1000);
                        tmp = (ArrayList<Integer>) getServletContext().getAttribute("id");
                        stu.setName(name);
                        stu.setGpa(gpa);
                        StudentTable.updateStudent(stu);
                        out.println("Update Success.");
                        tmp.remove(id);
                    }
                    else {
                        if (tmp.indexOf(id) == -1){
                            stu.setName(name);
                            stu.setGpa(gpa);
                            StudentTable.updateStudent(stu);
                            out.println("Update Success.");
                            tmp.remove(id);
                        }
                        else {
                            out.print("Some User has been update this ID, Please try again.");
                        }
                    }
                }
            }
            else {
                out.println("Information not found.");
            }
            out.println("</body>");
            out.println("</html>");
        }
        catch (InterruptedException e) {
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}