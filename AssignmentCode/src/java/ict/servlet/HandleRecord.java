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
@WebServlet(name = "HandleRecord", urlPatterns = {"/handleRecord"})
public class HandleRecord extends HttpServlet {

    private staff_DB db;
    String listTrainer = "handleRecord?action=search&name=&InformationType=trainer";
    String listGym = "handleRecord?action=search&name=&InformationType=gym";

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
            if ("trainer".equals(InformationType)) {
                if (!"".equals(name)) {
                    ArrayList<TrainerBean> trainers = db.queryTrainerByName(name);
                    // set the result into the attribute	 
                    request.setAttribute("trainers", trainers);
                } else {
                    ArrayList<TrainerBean> trainers = db.queryTrainer();
                    // set the result into the attribute	 
                    request.setAttribute("trainers", trainers);
                }
                // redirect the result to the listCustomers.jsp
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/staff_listTrainers.jsp?action=listTrainers");
                rd.forward(request, response);
                System.out.print("InformationType is" + InformationType);
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
                rd = getServletContext().getRequestDispatcher("/staff_listTrainers.jsp?action=listGym");
                rd.forward(request, response);
            }

        } else if ("searchBooking".equalsIgnoreCase(action)) {
            // call the query db to get retrieve for all customer 
            String BookingType = request.getParameter("BookingType");
            String role = request.getParameter("role");
            ArrayList<bookingBean> booking;
            if (role.equals("trainer")) {
                String id = request.getParameter("id");
                booking = db.queryBookingByTrainerId(id);
            } else {
                booking = db.queryBookingByType(BookingType);
            }
            request.setAttribute("booking", booking);
            RequestDispatcher rd;
            if (BookingType.equals("trainer")) {
                rd = getServletContext().getRequestDispatcher("/staff_bookingRequest.jsp?action=listTrainers");
            } else {
                rd = getServletContext().getRequestDispatcher("/staff_bookingRequest.jsp?action=listGym");
            }
            rd.forward(request, response);

        } else if ("searchBooking1".equalsIgnoreCase(action)) {
            // call the query db to get retrieve for all customer 
            String TrainerId = request.getParameter("id");

            ArrayList<bookingBean> booking = db.queryBookingByTrainerId(TrainerId);
            request.setAttribute("booking", booking);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/staff_bookingRequest.jsp?action=listTrainers");
            rd.forward(request, response);

        } else if ("delete".equalsIgnoreCase(action)) {
            // call the query db to get retrieve for all customer 
            String type = request.getParameter("type");

            if ("trainer".equals(type)) {
                String id = request.getParameter("id");
                if (id != null) {
                    // call delete record method in the database
                    if(db.delRecord(id, type)){
                        System.out.println("Delete Success");
                    }else{
                    System.out.println("Delete Fail");
                    }
                    // redirect the result to list action 
                    request.setAttribute("url", listTrainer);
                    
                }
            } else if ("center".equals(type)) {
                String id = request.getParameter("id");
                if (id != null) {
                    // call delete record method in the database
                    db.delRecord(id, type);
                    // redirect the result to list action 
                    request.setAttribute("url", listGym);
                }
            }
            String message = "The Record has deleted";
                request.setAttribute("title", "Delete Success");
                request.setAttribute("message", message);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/customer_message.jsp");
            rd.forward(request, response);
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
                String message = "The Trainer is adding success";
                request.setAttribute("title", "Adding Success");
                request.setAttribute("message", message);
                request.setAttribute("url", listTrainer);
            } else {
                String message = "The Sport Type has not slected";
                request.setAttribute("title", "Adding Error");
                request.setAttribute("message", message);
                request.setAttribute("url", listTrainer);
            }
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/customer_message.jsp");
            rd.forward(request, response);
        } else if ("addCenter".equalsIgnoreCase(action)) {
            // call the query db to get retrieve for all customer 
            String centerName = request.getParameter("name");
            String description = request.getParameter("description");
            String hrRate = request.getParameter("hrRate");
            boolean succss = db.addCenterRecord(centerName, description, Integer.parseInt(hrRate));
            String message = "The Record is adding success";
            request.setAttribute("title", "Adding Success");
            request.setAttribute("message", message);
            request.setAttribute("url", listGym);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/customer_message.jsp");
            rd.forward(request, response);
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
            String[] states = request.getParameterValues("states");
            String states1 = "";
            String description = request.getParameter("description");
            int hrRate = Integer.parseInt(request.getParameter("hrRate"));
            String[] sportType = request.getParameterValues("sportType");
            int imageID = Integer.parseInt(request.getParameter("imageID"));
            // call  editCustomer to update the database record
//            System.out.print("states is"+states);
            if (states != null) {
                states1 = "enable";
            } else {
                states1 = "disable";
            }
            if (sportType != null) {
                String result = "";
                for (int i = 0; i < sportType.length; i++) {
//                    out.print(getPhones(Phone[i]));
                    result = result + sportType[i] + ",";
                }
                // call delete record method in the database
                TrainerBean tb = db.queryTrainerByID(id);
                db.editTrainerRecord(new TrainerBean(id, name, states1, hrRate, description, result, imageID));
                String message = "The record has been update";
                request.setAttribute("title", "Edit Success");
                request.setAttribute("message", message);
                request.setAttribute("url", listTrainer);
                // redirect the result to list action 
            } else {
                String message = "You have not select Any Sport Type";
                request.setAttribute("title", "Edit Fail");
                request.setAttribute("message", message);
                request.setAttribute("url", listTrainer);
                // redirect the result to “list” action again
            }
// redirect the result to “list” action again
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/customer_message.jsp");
            rd.forward(request, response);
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
            System.out.print("states is " + states1);
            GymBean gb = db.queryCenterByID(id);
            db.editCenterRecord(new GymBean(id, centerName, description, states1, hrRate, imageID));
            String message = "The record is edit";
            request.setAttribute("title", "Edit Success");
            request.setAttribute("message", message);
            request.setAttribute("url", listGym);
            // redirect the result to “list” action again
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/customer_message.jsp");
            rd.forward(request, response);
        } else if ("confirmBooking".equalsIgnoreCase(action)) {

            // obtain the parameters from request
            String id = request.getParameter("id");
            String role = request.getParameter("role");
            String userId = request.getParameter("userId");
            bookingBean Bb = new bookingBean();
            System.out.print("id is " + id);
            Bb.setId(id);
            Bb.setStatus("confirm");
            db.editBookingRecord(Bb);
            // redirect the result to “list” action again
            String message = "The Booking has been confirm";
            request.setAttribute("title", "Booking Confirm");
            request.setAttribute("message", message);
            request.setAttribute("url", "handleRecord?action=searchBooking&BookingType=trainer&role="+role+"&id="+userId);
            // redirect the result to “list” action again
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/customer_message.jsp");
            rd.forward(request, response);
        } else if ("declineBooking".equalsIgnoreCase(action)) {

            // obtain the parameters from request
            String id = request.getParameter("id");
            String role = request.getParameter("role");
            String userId = request.getParameter("userId");
            bookingBean Bb = new bookingBean();
            System.out.print("id is " + id);
            Bb.setId(id);
            Bb.setStatus("decline");
            db.editBookingRecord(Bb);

            // redirect the result to “list” action again
            String message = "The Booking has been decline";
            request.setAttribute("title", "Booking Decline");
            request.setAttribute("message", message);
            request.setAttribute("url", "handleRecord?action=searchBooking&BookingType=trainer&role="+role+"&id="+userId);
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
