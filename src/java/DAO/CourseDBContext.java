/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Category;
import Model.Course;
import Model.Register;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;

/**
 *
 * @author ADMIN
 */
public class CourseDBContext extends DBContext {
public Course getCourse(int courseId) {
        Course c = null;
        String sql = "select * from [course] where [id] = ?";
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseId);
            ResultSet rs = stm.executeQuery();
            if(rs.next()) {
                c = new Course(rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getString("image"),
                                rs.getBoolean("status"),
                                rs.getBoolean("featured"),
                                new CategoryDBContext().getCategory(rs.getInt("categoryid")));
                c.setUsersTeach(new UserDBContext().getExpert(courseId));
                c.setUsersRegister(new UserDBContext().getCustomer(courseId));
                c.setPricePackages(new PricePackageDBContext().getAllPricePackage(courseId));
                c.setTopics(new TopicDBContext().getTopics(courseId));
            }
        }
        catch(Exception e){
            
        }
        return c;
    }
    //register
    public boolean registerCourse(int courseId, int userId, Timestamp validFrom, Timestamp validTo) {
        String sql = "insert into [register] values(?,?,?,?)";
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, courseId);
            stm.setTimestamp(3, validFrom);
            stm.setTimestamp(4, validTo);
            stm.executeUpdate();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    //get lastest register
    public Register getRegister(int courseId, int userId) {
        Register r = null;
        String sql = "select *\n" +
                    "from register\n" +
                    "where [userid] = ? and courseId = ?\n" +
                    "order by ValidTo desc";
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, courseId);
            ResultSet rs = stm.executeQuery();
            if(rs.next()) {
                r = new Register(rs.getInt("id"),
                                new UserDBContext().getUser(userId),
                                new CourseDBContext().getCourse(courseId),
                                rs.getTimestamp("validfrom"),
                                rs.getTimestamp("validto"));
            }
        }
        catch(Exception e){
            
        }
        return r;
    }
}
