/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.servlet;

import ict.bean.GymBean;
import ict.bean.TrainerBean;
import ict.bean.bookingBean;
import ict.db.staff_DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebServlet(name = "customerHandleRecord", urlPatterns = {"/customerHandleRecord"})
public class customerHandleRecord extends HttpServlet {

    private staff_DB db;
    String listTrainer = "customerHandleRecord?action=search&name=&InformationType=trainer&keyword=";
    String listGym = "customerHandleRecord?action=search&name=&InformationType=gym";

    @Override
    public void init() {
        //1.  obtain the context-param, dbUser, dbPassword and dbUrl which defined in web.xml
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

        //2.  create a new db object  with the parameter
        db = new staff_DB(dbUrl, dbUser, dbPassword);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("search".equalsIgnoreCase(action)) {
            // call the query db to get retrieve for all customer 
            String name = request.getParameter("name");
            String InformationType = request.getParameter("InformationType");
            String keyword = request.getParameter("keyword");
            if ("trainer".equals(InformationType)) {
                if (!"".equals(name)) {
                    ArrayList<TrainerBean> trainers = db.queryTrainerByName(name);
                    // set the result into the attribute	 
                    request.setAttribute("trainers", trainers);
                } else if (!"".equals(keyword)) {
                    ArrayList<TrainerBean> trainers = db.queryTrainerBykeyword(keyword);
                    // set the result into the attribute	 
                    request.setAttribute("trainers", trainers);
                } else {
                    ArrayList<TrainerBean> trainers = db.queryTrainer();
                    // set the result into the attribute	 
                    request.setAttribute("trainers", trainers);
                }
                // redirect the result to the listCustomers.jsp
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/customer_listTrainers.jsp?action=listTrainers");
                rd.forward(request, response);
                System.out.print("InformationType is = " + InformationType);
                System.out.print("name is" + name);
            } else if ("gym".equals(InformationType)) {
                if (!"".equals(name)) {
                    ArrayList<GymBean> gym = db.queryGymByName(name);
                    // set the result into the attribute	 
                    request.setAttribute("gym", gym);
                } else {
                    ArrayList<GymBean> gym = db.queryGym();
                    // set the result into the attribute	 
                    request.setAttribute("gym", gym);
                }
                // redirect the result to the listCustomers.jsp
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/customer_listTrainers.jsp?action=listGym");
                rd.forward(request, response);
            }

        } else if ("searchBooking".equalsIgnoreCase(action)) {
            // call the query db to get retrieve for all customer 
            String BookingType = request.getParameter("BookingType");
            ArrayList<bookingBean> booking = db.queryBookingByType(BookingType);
            request.setAttribute("booking", booking);
            RequestDispatcher rd;
            if (BookingType.equals("trainer")) {
                rd = getServletContext().getRequestDispatcher("/staff_bookingRequest.jsp?action=listTrainers");
            } else {
                rd = getServletContext().getRequestDispatcher("/staff_bookingRequest.jsp?action=listGym");
            }
            rd.forward(request, response);

        } else if ("delete".equalsIgnoreCase(action)) {
            // call the query db to get retrieve for all customer 
            String type = request.getParameter("type");

            if ("trainer".equals(type)) {
                String id = request.getParameter("id");
                if (id != null) {
                    // call delete record method in the database
                    db.delRecord(id, type);
                    // redirect the result to list action 
                    response.sendRedirect(listTrainer);
                }
            } else if ("center".equals(type)) {
                String id = request.getParameter("id");
                if (id != null) {
                    // call delete record method in the database
                    db.delRecord(id, type);
                    // redirect the result to list action 
                    response.sendRedirect(listGym);
                }

            } else {
                response.sendRedirect("staff_listTrainers.jsp?action=search");
            }
        } else if ("addTrainer".equalsIgnoreCase(action)) {
            // call the query db to get retrieve for all customer 
            String TrainerName = request.getParameter("name");
            String description = request.getParameter("description");
            String hrRate = request.getParameter("hrRate");
            String[] sportType = request.getParameterValues("sportType");
            if (sportType != null) {
                String result = "";
                for (int i = 0; i < sportType.length; i++) {
//                    out.print(getPhones(Phone[i]));
                    result = result + sportType[i] + ",";
                }
                // call delete record method in the database
                db.addTrainerRecord(TrainerName, description, Integer.parseInt(hrRate), result);
                // redirect the result to list action 
            }
            response.sendRedirect(listTrainer);
        } else if ("addCenter".equalsIgnoreCase(action)) {
            // call the query db to get retrieve for all customer 
            String centerName = request.getParameter("name");
            String description = request.getParameter("description");
            String hrRate = request.getParameter("hrRate");
            boolean succss = db.addCenterRecord(centerName, description, Integer.parseInt(hrRate));

            response.sendRedirect(listGym);
        } else if ("getEditTrainer".equalsIgnoreCase(action)) {
            // obtain the parameter id;
            int id = 0;
            id = Integer.parseInt(request.getParameter("id"));
            if (id != 0) {
                // call  query db to get retrieve for a customer with the given id
                TrainerBean trainer = db.queryTrainerByID(id);
                // set the customer as attribute in request scope
                request.setAttribute("t", trainer);
                // forward the result to the editCustomer.jsp
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/staff_editTrainer.jsp");
                rd.forward(request, response);
            }
        } else if ("editTrainer".equalsIgnoreCase(action)) {

            // obtain the parameters from request
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String states = request.getParameter("states");
            String description = request.getParameter("description");
            int hrRate = Integer.parseInt(request.getParameter("hrRate"));
            String[] sportType = request.getParameterValues("sportType");
            int imageID = Integer.parseInt(request.getParameter("imageID"));
            // call  editCustomer to update the database record
            if (sportType != null) {
                String result = "";
                for (int i = 0; i < sportType.length; i++) {
//                    out.print(getPhones(Phone[i]));
                    result = result + sportType[i] + ",";
                }
                // call delete record method in the database
                TrainerBean tb = db.queryTrainerByID(id);
                db.editTrainerRecord(new TrainerBean(id, name, states, hrRate, description, result, imageID));
                // redirect the result to list action 
            }
            // redirect the result to “list” action again
            response.sendRedirect(listTrainer);
        } else if ("getEditCenter".equalsIgnoreCase(action)) {
            // obtain the parameter id;
            int id = 0;
            id = Integer.parseInt(request.getParameter("id"));
            if (id != 0) {
                // call  query db to get retrieve for a customer with the given id
                GymBean center = db.queryCenterByID(id);
                // set the customer as attribute in request scope
                request.setAttribute("g", center);
                // forward the result to the editCustomer.jsp
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/staff_editCenter.jsp");
                rd.forward(request, response);
            }
        } else if ("editCenter".equalsIgnoreCase(action)) {

            // obtain the parameters from request
            int id = Integer.parseInt(request.getParameter("id"));
            String centerName = request.getParameter("centerName");
            String[] states = request.getParameterValues("states");
            String states1 = "";
            String description = request.getParameter("description");
            int hrRate = Integer.parseInt(request.getParameter("hrRate"));
            int imageID = Integer.parseInt(request.getParameter("imageID"));
            if (states != null) {
                states1 = "enable";
            } else {
                states1 = "disable";
            }
            GymBean gb = db.queryCenterByID(id);
            db.editCenterRecord(new GymBean(id, centerName, description, states1, hrRate, imageID));
            // redirect the result to “list” action again
            response.sendRedirect(listGym);
        } else if ("confirmBooking".equalsIgnoreCase(action)) {

            // obtain the parameters from request
            String id = request.getParameter("id");
            bookingBean Bb = new bookingBean();
            System.out.print("id is " + id);
            Bb.setId(id);
            Bb.setStatus("confirm");
            db.editBookingRecord(Bb);
            // redirect the result to “list” action again
            response.sendRedirect("handleRecord?action=searchBooking&BookingType=trainer");
        } else if ("declineBooking".equalsIgnoreCase(action)) {

            // obtain the parameters from request
            String id = request.getParameter("id");
            bookingBean Bb = new bookingBean();
            System.out.print("id is " + id);
            Bb.setId(id);
            Bb.setStatus("decline");
            db.editBookingRecord(Bb);

            // redirect the result to “list” action again
            response.sendRedirect("handleRecord?action=searchBooking&BookingType=trainer");
        } else if ("addTrainerRequest".equalsIgnoreCase(action)) {
            // call the query db to get retrieve for all customer 
            String id = request.getParameter("id");
            String date = request.getParameter("date");
            String time = request.getParameter("time");
            String userId = request.getParameter("userId");
            if (db.countData(date, time) < 9) {
                db.addTrainerRequest(date, time, id, userId);
                System.out.println("insert success");
                String message = "The Booking is create success";
                request.setAttribute("title", "Booking Success");
                request.setAttribute("message", message);
                request.setAttribute("url", listTrainer);
            } else {
                String message = "The timeslot have been full booking";
                request.setAttribute("title", "Booking Error");
                request.setAttribute("message", message);
                request.setAttribute("url", listTrainer);
            }
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/customer_message.jsp");
            rd.forward(request, response);
            response.sendRedirect(listTrainer);
        } else if ("addCenterRequest".equalsIgnoreCase(action)) {
            // call the query db to get retrieve for all customer 
            String id = request.getParameter("id");
            String date = request.getParameter("date");
            String time = request.getParameter("time");
            String userId = request.getParameter("userId");
            if (db.countData(date, time) < 10) {
                db.addCenterRequest(date, time, id, userId);
                System.out.println("insert success");
                String message = "The Booking is create success";
                request.setAttribute("title", "Booking Success");
                request.setAttribute("message", message);
                request.setAttribute("url", listGym);
            } else {
                String message = "The timeslot have been full booking";
                request.setAttribute("title", "Booking Error");
                request.setAttribute("message", message);
                request.setAttribute("url", listGym);
            }
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/customer_message.jsp");
            rd.forward(request, response);
        } else if ("listBooking".equalsIgnoreCase(action)) {
            // call the query db to get retrieve for all customer 
            String id = request.getParameter("id");

            ArrayList<bookingBean> booking = db.queryBookingByCustID(id);
            request.setAttribute("booking", booking);
            System.out.println("....." + booking.size());

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/customer_checkUpdate.jsp");
            rd.forward(request, response);

//            response.sendRedirect("customer_checkUpdate.jsp");
        } else if ("BookingStates".equalsIgnoreCase(action)) {
            // call the query db to get retrieve for all customer 
            String id = request.getParameter("id");

            ArrayList<bookingBean> booking = db.queryBookingByCustID1(id);
            request.setAttribute("booking", booking);

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/customer_checkStatus.jsp");
            rd.forward(request, response);

//            response.sendRedirect("customer_checkUpdate.jsp");
        } else if ("bookingReminder".equalsIgnoreCase(action)) {
            // call the query db to get retrieve for all customer 
            String id = request.getParameter("id");
            bookingBean Bb = db.queryBookingByDateTime(id);
            if (Bb != null) {
                String message = "The nearest booking is in " + Bb.getDate() + " " + Bb.getTime() + "<br>Please remember to come gym center to do training.";
                request.setAttribute("title", "Booking Reminder");
                request.setAttribute("message", message);
                request.setAttribute("url", listTrainer);
            }else{
                String message = "You have no any nearest booking";
                request.setAttribute("title", "Booking Reminder");
                request.setAttribute("message", message);
                request.setAttribute("url", listTrainer);
            }

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/customer_message.jsp");
            rd.forward(request, response);
        }else if ("getEditRecord".equalsIgnoreCase(action)) {
            // obtain the parameter id;
            int id = 0;
            id = Integer.parseInt(request.getParameter("id"));
            if (id != 0) {
                // call  query db to get retrieve for a customer with the given id
                bookingBean booking = db.queryBookingByID(id);
                // set the customer as attribute in request scope
                request.setAttribute("b", booking);
                // forward the result to the editCustomer.jsp
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/customer_updateRecord.jsp");
                rd.forward(request, response);
            }
        } else if ("EditRecord".equalsIgnoreCase(action)) {

            // obtain the parameters from request
            String id = request.getParameter("id");
            String date = request.getParameter("date");
            String time = request.getParameter("time");
            String custId = request.getParameter("custId");
            System.out.println("infor is "+id +" "+date+" "+time+" "+custId);
            bookingBean bb = new bookingBean();
            bb.setId(id);
            bb.setDate(date);
            bb.setTime(time+":00:00");        
            bb.setStatus("waiting");
            db.editBookingRecord1(bb);
            String message = "The record has been update";
            request.setAttribute("title", "Update Success");
            request.setAttribute("message", message);
            request.setAttribute("url", "customerHandleRecord?action=BookingStates&id="+custId);
            // redirect the result to “list” action again
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/customer_message.jsp");
            rd.forward(request, response);
        } else {
            PrintWriter out = response.getWriter();
            out.println("No such action!!!");
        }

    }
}
