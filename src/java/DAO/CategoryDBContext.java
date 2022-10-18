/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ADMIN
 */
public class CategoryDBContext extends DBContext {
    public Category getCategory(int categoryId) {
        Category c = null;
        String sql = "select * from [category] where [id] = ?";
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, categoryId);
            ResultSet rs = stm.executeQuery();
            if(rs.next()) {
                c = new Category(rs.getInt("id"),
                                rs.getString("name"));
            }
        }
        catch(Exception e){
            
        }
        return c;
    }
}
