/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Acer
 */
import java.sql.*;  // Using 'Connection', 'Statement' and 'ResultSet' classes in java.sql package
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Addfood;

public class DBConnection {

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Smoothie",
                "root", "Prabenz@34839");
    }

    //public boolean deleteFoodById(int id) {
        //boolean rowDeleted = false;
        //String sql = "DELETE FROM food WHERE id = ?";
        //try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            //statement.setInt(1, id);
            //rowDeleted = statement.executeUpdate() > 0;
        //} catch (SQLException e) {
            //e.printStackTrace();
        //}
        //return rowDeleted;
    //}

    public List<Addfood> getAllFoods() {
        List<Addfood> foodList = new ArrayList<>();
        String query = "SELECT * FROM food";

        try (Connection con = getConnection(); 
                PreparedStatement ps = con.prepareStatement(query); 
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Addfood food = new Addfood();
                food.setFoodname(rs.getString("name"));
                food.setType(rs.getString("type"));
                food.setTaste(rs.getString("Taste").split(","));
                food.setToppings(rs.getString("toppings"));
                food.setFoodPrice(rs.getDouble("price"));
                foodList.add(food);
                System.out.println("Food found: " + food.getFoodname() + ", " + food.getType());
            }

        } catch (SQLException e) {
            e.printStackTrace(); // พิมพ์ข้อผิดพลาดเพื่อการตรวจสอบ
        }

        return foodList;
    }

    public boolean insertNewFood(Addfood food) {
        boolean result = false;
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Smoothie",
                    "root", "Prabenz@34839");
            


            try (Statement statement = connection.createStatement()) {
                String query = "INSERT INTO food "
                        + "(name, type, Taste, Toppings, price) "
                        + "VALUES('"
                        + food.getFoodname() + "','"
                        + food.getType() + "','"
                        + Arrays.toString(food.getTaste()) + "','"
                        + food.getToppings() + "',"
                        + food.getFoodPrice() + ")";
                System.out.println("........SQL: " + query);

                int i = statement.executeUpdate(query);
                if (i != 0) {
                    result = true;
                }
                statement.close();
                connection.close();
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println(exception);
        }

        return result;
    }

    public Addfood getSmoothie(String name) {
        Addfood food = new Addfood();
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Smoothie",
                    "root", "Prabenz@34839");

            try (Statement statement = connection.createStatement()) {
                String query = "SELECT * FROM food WHERE name='" + name + "'";
                System.out.println(">>>>>>>>>> query=" + query);

                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    food.setFoodname(resultSet.getString("name").trim());
                    food.setType(resultSet.getString("type").trim());
                    food.setTaste(resultSet.getString("taste").trim().split(","));
                    food.setToppings(resultSet.getString("toppings").trim());
                    food.setFoodPrice(resultSet.getDouble("price"));
                }

                resultSet.close();
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println(exception);
        }
        return food;
    }
}
