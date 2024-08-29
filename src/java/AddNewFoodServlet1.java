/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import controller.DBConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import static java.lang.System.out;
import java.util.List;
import model.Addfood;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Acer
 */
@WebServlet(urlPatterns = {"/AddNewFoodServlet1"})
public class AddNewFoodServlet1 extends HttpServlet {

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
        String foodname = request.getParameter("foodname");
        String type = request.getParameter("Type");
        String[] taste = request.getParameterValues("Taste");
        String toppings = request.getParameter("Toppings");
        String foodPriceStr = request.getParameter("foodPrice");

        // Validate input
        if (foodname == null || foodname.trim().isEmpty()) {
            RequestDispatcher rd = request.getRequestDispatcher("/addNewFood.html");
            rd.forward(request, response);
            return;
        }

        try {
            int foodPrice = Integer.parseInt(foodPriceStr.trim());

            // Create Addfood object and set properties
            Addfood newFood = new Addfood();
            newFood.setFoodname(foodname);
            newFood.setType(type);
            newFood.setTaste(taste);
            newFood.setToppings(toppings);
            newFood.setFoodPrice(foodPrice);

            // Store Addfood object in session
            HttpSession session = request.getSession();
            session.setAttribute("newFood", newFood);

            DBConnection dbConnection = new DBConnection();
            if (!dbConnection.insertNewFood(newFood)) {
                System.out.println(">>> AddNewFoodMySQL ERROR");
            }
            ServletContext sc = getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/addNewFoodSuccess.jsp");
            rd.forward(request, response);

            List<Addfood> foodList = dbConnection.getAllFoods();
            JSONArray jsonArray = new JSONArray();   // สร้าง JSON เพื่อเก็บข้อมูล

            for (Addfood food : foodList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", food.getFoodname());
                jsonObject.put("type", food.getType());
                jsonObject.put("Taste", food.getTaste());
                jsonObject.put("Toppings", food.getToppings());
                jsonObject.put("foodPrice", food.getFoodPrice());
                jsonArray.put(jsonObject);
            }
            out.println(jsonArray.toString());

        } catch (NumberFormatException e) {
            RequestDispatcher rd = request.getRequestDispatcher("/addNewFood.html");
            rd.forward(request, response);
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
