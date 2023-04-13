/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ict.servlet;

import ict.bean.GymBeanWithID;
import ict.bean.bookingBean;
import ict.bean.incomeBean;
import ict.db.bookingDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author User
 */
@WebServlet(name = "HandleBookingSearch", urlPatterns = {"/handleBookingSearch"})
public class HandleBookingSearch extends HttpServlet {

    private bookingDB db;

    @Override
    public void init() {
        //1.  obtain the context-param, dbUser, dbPassword and dbUrl which defined in web.xml
        //2.  create a new db object  with the parameter
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

        db = new bookingDB(dbUrl, dbUser, dbPassword);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");

        if ("list".equalsIgnoreCase(action)) {
            //check id exist or not
            String type = request.getParameter("type");
            String id = request.getParameter("id");
            boolean haveRecord = db.haveBooking(id, type);
            if (!haveRecord) {
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/noBooking.jsp");
                rd.forward(request, response);
            } else if (id != null) {
                ArrayList<bookingBean> booking = db.queryBookingByID(id, type);
                request.setAttribute("booking", booking);
                request.setAttribute("type", type);
                request.setAttribute("id", id);

                int year = Calendar.getInstance().get(Calendar.YEAR); // current year
                System.out.println("year " + year);

                Date date = new Date();
                LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                int month = localDate.getMonthValue();
                System.out.println("month " + month);  // current month

                int daysInYear = Year.of(year).length();
                System.out.println("daysInYear " + daysInYear);  // day of this year

                YearMonth yearMonthObject = YearMonth.of(year, month);
                int daysInMonth = yearMonthObject.lengthOfMonth();
                System.out.println("daysInMonth " + daysInMonth);  // day of this year

                int yRate;
                int mRate;

                int mBooking = db.countBookingMonth(year, month, type, id);
                int yBooking = db.countBookingYear(year, type, id);

                String mb = Integer.toString(mBooking);
                String yb = Integer.toString(yBooking);

                //month rate = record found of this month /(total day of month * 12)
                double mr = (double) mBooking / (((double) daysInMonth));
                //year rate record found of this year/(total day of year * 12)
                double yr = (double) yBooking / (((double) daysInYear));

                System.out.println("mBooking:" + mBooking);
                System.out.println("yBooking:" + yBooking);
                System.out.println("mb:" + mb);
                System.out.println("yb:" + yb);
                System.out.println("mr:" + mr);
                System.out.println("yr:" + yr);
                request.setAttribute("mb", mb);
                request.setAttribute("yb", yb);

                ArrayList<GymBeanWithID> gym = db.queryGYM();
                request.setAttribute("gym", gym);

                request.setAttribute("mr", (double) Math.round(mr * 1000d) / 1000d);
                request.setAttribute("yr", (double) Math.round(yr * 1000d) / 1000d);

                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/bookingSearch.jsp");
                rd.forward(request, response);
            }

        } else if ("income".equalsIgnoreCase(action)) {
            String year = request.getParameter("year");
            String month = request.getParameter("month");

            if (month.equalsIgnoreCase("0")) {
                ArrayList<incomeBean> t = db.queryTrainerYearIncome(year);
                request.setAttribute("t", t);

                ArrayList<incomeBean> c = db.queryCenterYearIncome(year);
                request.setAttribute("c", c);
            System.out.print("year is "+c.size());
            System.out.print("month is "+month);

            } else {
                ArrayList<incomeBean> t = db.queryTrainerMonthIncome(year, month);
                request.setAttribute("t", t);

                ArrayList<incomeBean> c = db.queryCenterMonthIncome(year, month);
                request.setAttribute("c", c);
            }
            request.setAttribute("year", year);
            request.setAttribute("month", month);

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/incomeData.jsp");
            rd.forward(request, response);

        } else if ("search".equalsIgnoreCase(action)) {
            ArrayList<GymBeanWithID> gym = db.queryGYM();
            request.setAttribute("gym", gym);

            ArrayList<bookingBean> allRecords = db.queryRecords();
            request.setAttribute("allRecords", allRecords);


            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/ar-search.jsp");
            rd.forward(request, response);

        } else {
            PrintWriter out = response.getWriter();
            out.println("No such action!!!");
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
