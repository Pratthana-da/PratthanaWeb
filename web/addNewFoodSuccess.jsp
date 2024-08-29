<%-- 
    Document   : addNewFoodSuccess
    Created on : Jul 18, 2024, 2:04:30 PM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Addfood" %>
<!DOCTYPE html>
<html>
    <body>
        <h1>Add Smoothie Success</h1>
        <%
            Addfood newFood = (Addfood) session.getAttribute("newFood");
        %>
        Namefruit: <%= newFood.getFoodname()%><br/>
        Type: <%= newFood.getType()%><br/>
        Taste: 
        <%
            String[] tasteArray = newFood.getTaste();
            if (tasteArray != null) {
                for (String taste : tasteArray) {
                    out.print(taste + " ");
                }
            }
        %>
        <br/>
        Toppings: <%= newFood.getToppings()%><br/>
        Price: <%= newFood.getFoodPrice()%><br/>
        <a href="ShowUpdatedFood.jsp">Edit Smoothie</a>
    </body>
</html>

