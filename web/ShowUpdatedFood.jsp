<%-- 
    Document   : ShowUpdatedFood
    Created on : Jul 18, 2024, 2:11:26 PM
    Author     : Acer
--%>

<%@page import="java.util.Arrays"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Addfood" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Fruit</title>
        <%
            Addfood newFood = (Addfood) session.getAttribute("newFood");
            out.println("ข้อมูลเดิม: " + "Name: "+ newFood.getFoodname() + ", " + "Type: "+ newFood.getType()
                    + ", " +"Taste: "+  Arrays.toString(newFood.getTaste())+ ", " +"Toppings: "+  newFood.getToppings()+ ", " 
                    +"Price: "+  newFood.getFoodPrice()+ "<br/>");
        %>

        <form action="AddNewFoodServlet1" method="post">
            <label for="foodname">Name Fruit:</label><br>
            <input type="text" id="foodname" name="foodname" value="<%= newFood.getFoodname()%>" required><br>

            <label for="Type">Type:</label><br>
            <select id="Type" name="Type">
                <option value="Fruit Smoothie" <%= newFood.getType().equals("Fruit Smoothie") ? "selected" : ""%>>Fruit Smoothie</option>
                <option value="Fruit Juice" <%= newFood.getType().equals("Fruit Juice") ? "selected" : ""%>>Fruit Juice</option>
            </select><br>

            <label>Taste:</label><br>
            <input type="checkbox" id="Taste1" name="Taste" value="Sweet 100%" <%= newFood.getTaste().equals("Sweet 100%") ? "checked" : ""%>>
            <label for="Taste1">Sweet 100%</label><br>
            <input type="checkbox" id="Taste2" name="Taste" value="Sweet 75%" <%= newFood.getTaste().equals("Sweet 75%") ? "checked" : ""%>>
            <label for="Taste2">Sweet 75%</label><br>
            <input type="checkbox" id="Taste3" name="Taste" value="Sweet 50%" <%= newFood.getTaste().equals("Sweet 50%") ? "checked" : ""%>>
            <label for="Taste3">Sweet 50%</label><br>
            <input type="checkbox" id="Taste4" name="Taste" value="Sweet 25%" <%= newFood.getTaste().equals("Sweet 25%") ? "checked" : ""%>>
            <label for="Taste4">Sweet 25%</label><br>

            <label>Toppings:</label><br>
            <input type="radio" id="Toppings1" name="Toppings" value="Brown Sugar Pearls" <%= newFood.getToppings().equals("Brown Sugar Pearls") ? "checked" : ""%>>
            <label for="Toppings1">Brown Sugar Pearls</label><br>
            <input type="radio" id="Toppings2" name="Toppings" value="Fruit JellyFruit Salad" <%= newFood.getToppings().equals("Fruit JellyFruit Salad") ? "checked" : ""%>>
            <label for="Toppings2">Fruit JellyFruit Salad</label><br>
            <input type="radio" id="Toppings3" name="Toppings" value="Caramel Jelly" <%= newFood.getToppings().equals("Caramel Jelly") ? "checked" : ""%>>
            <label for="Toppings3">Caramel Jelly</label><br>
            <input type="radio" id="Toppings4" name="Toppings" value="Panda Pearl Fruit Salad" <%= newFood.getToppings().equals("Panda Pearl Fruit Salad") ? "checked" : ""%>>
            <label for="Toppings4">Panda Pearl Fruit Salad</label><br>

            <label for="foodPrice">Price:</label><br>
            <input type="text" id="foodPrice" name="foodPrice" value="<%= newFood.getFoodPrice()%>" required><br>

            <input type="submit" value="Edit Fruit">
        </form>
        <%
            session.removeAttribute("newFood");
        %>
    </body>
</html>

