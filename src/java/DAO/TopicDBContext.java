/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Topic;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class TopicDBContext extends DBContext {
    public ArrayList<Topic> getTopics(int courseId) {
        ArrayList<Topic> topics = null;
        String sql = "select * from [Topic] where [courseid] = ?";
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseId);
            ResultSet rs = stm.executeQuery();
            topics = new ArrayList<>();
            while(rs.next()) {
                Topic t = new Topic(rs.getInt("id"),
                                rs.getInt("order"),
                                rs.getString("name"),
                                null);
                t.setLessons(new LessonDBContext().getLessons(rs.getInt("id")));
                topics.add(t);
            }
        }
        catch(Exception e){
            
        }
        return topics;
    }
}
