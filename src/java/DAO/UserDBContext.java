/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Role;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class UserDBContext extends DBContext {
    //get customer register courses
    public ArrayList<User> getCustomer(int courseId) {
        ArrayList<User> users = null;
        String sql = "  select * \n" +
                    "  from register \n" +
                    "  where CourseId = ? \n";
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseId);
            ResultSet rs = stm.executeQuery();
            users = new ArrayList<>();
            while(rs.next()) {
                int userId = rs.getInt("userid");
                users.add(getUser(userId));
            }
        }
        catch(Exception e){
            
        }
        return users;
    }
    
    //get expert teach courses
    public ArrayList<User> getExpert(int courseId) {
        ArrayList<User> users = null;
        String sql = "  select * \n" +
                    "  from teach \n" +
                    "  where CourseId = ? \n";
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseId);
            ResultSet rs = stm.executeQuery();
            users = new ArrayList<>();
            while(rs.next()) {
                int userId = rs.getInt("userid");
                users.add(getUser(userId));
            }
        }
        catch(Exception e){
            
        }
        return users;
    }
    
    public User getUser(int id) {
        User user = null;
        String sql = "  select * \n" +
                    "  from [user] \n" +
                    "  where id = ? \n";
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if(rs.next()) {
                user = new User(rs.getInt("id"),
                                rs.getString("password"),
                                rs.getString("fullname"),
                                rs.getString("email"),
                                rs.getString("phone"),
                                rs.getString("image"),
                                rs.getBoolean("gender"),
                                new Role(rs.getInt("roleid"))
                                );
            }
        }
        catch(Exception e){
            
        }
        return user;
    }
    public User getUserAcc(String email, String pass) {
        User s = null;
        
        try {
            String sql = "select * from [User]"
                    + "where Email=? and [password] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, pass);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Role r = new Role(rs.getInt(8));
                return new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getBoolean(7),
                        r);
            }

        } catch (Exception e) {
        }
        return s;
    }
}
