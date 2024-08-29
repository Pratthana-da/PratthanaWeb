import model.Addfood;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Arrays;

public class GetUserServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            Addfood newFood = (Addfood) session.getAttribute("newFood");

            if (newFood == null) {
                String foodname = request.getParameter("foodname");
                String type = request.getParameter("Type");
                String Taste = request.getParameter("Taste");
                String Toppings = request.getParameter("Toppings");
                 int foodPrice = Integer.parseInt(request.getParameter("foodPrice")); 
                
                
                newFood = new Addfood();
                newFood.setFoodname(foodname);
                newFood.setType(type);
                newFood.setTaste(Taste);
                newFood.setToppings(Toppings);
                newFood.setFoodPrice(foodPrice);
                
                session.setAttribute("newFood", newFood);
                
                out.println("<h2>New Food Added</h2>");
                out.println("<p>Name: " + newFood.getFoodname() + "</p>");
                out.println("<p>Type: " + newFood.getType() + "</p>");
                out.println("<p>Taste: " + Arrays.toString(newFood.getTaste()) + "</p>");
                out.println("<p>Toppings: " + newFood.getToppings() + "</p>");
                out.println("<p>Price: $" + newFood.getFoodPrice() + "</p>");
                out.println("<br/><a href='addNewFoodForm.html'>Add Another Food</a>");
            } else {
                out.println("<h2>Food Details</h2>");
                out.println("<p>Name: " + newFood.getFoodname() + "</p>");
                out.println("<p>Type: " + newFood.getType() + "</p>");
                out.println("<p>Taste: " + Arrays.toString(newFood.getTaste()) + "</p>");
                out.println("<p>Toppings: " + newFood.getToppings() + "</p>");
                out.println("<p>Price: $" + newFood.getFoodPrice() + "</p>");
                out.println("<br/><a href='addNewFoodForm.html'>Add Another Food</a>");
                session.removeAttribute("newFood");
            }
        } catch (Exception e) {
            response.getWriter().println("ERROR: " + e.getMessage());
        }
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

    @Override
    public String getServletInfo() {
        return "Servlet for handling user information in session";
    }
}