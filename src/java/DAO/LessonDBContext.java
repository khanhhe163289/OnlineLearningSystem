/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Lesson;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class LessonDBContext extends DBContext {
    public ArrayList<Lesson> getLessons(int topicId) {
        ArrayList<Lesson> lessons = null;
        String sql = "select * from [Lesson] where [topicId] = ?";
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, topicId);
            ResultSet rs = stm.executeQuery();
            lessons = new ArrayList<>();
            while(rs.next()) {
                Lesson l = new Lesson(rs.getInt("id"),
                                rs.getInt("order"),
                                rs.getString("name"),
                                rs.getString("url"),
                                rs.getString("description"),
                                rs.getBoolean("status"),
                                null);
                lessons.add(l);
            }
        }
        catch(Exception e){
            
        }
        return lessons;
    }
}
