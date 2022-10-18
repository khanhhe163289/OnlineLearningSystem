/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.PricePackage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class PricePackageDBContext extends DBContext {
    public ArrayList<PricePackage> getAllPricePackage(int courseId) {
        ArrayList<PricePackage> pp = null;
        String sql = "  select * \n" +
                    "  from PricePackage \n" +
                    "  where CourseId = ? \n" +
                    "  order by SalePrice";
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseId);
            ResultSet rs = stm.executeQuery();
            pp = new ArrayList<>();
            while(rs.next()) {
                pp.add(new PricePackage(rs.getInt("id"), 
                        rs.getInt("duration"), 
                        rs.getString("name"), 
                        rs.getFloat("listedprice"), 
                        rs.getFloat("saleprice"), 
                        rs.getBoolean("status")));
            }
        }
        catch(Exception e){
            
        }
        return pp;
    }
    public PricePackage getPricePackage(int id) {
        PricePackage pp = null;
        String sql = "  select * \n" +
                    "  from PricePackage \n" +
                    "  where id = ?";
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if(rs.next()) {
                pp = (new PricePackage(rs.getInt("id"), 
                        rs.getInt("duration"), 
                        rs.getString("name"), 
                        rs.getFloat("listedprice"), 
                        rs.getFloat("saleprice"), 
                        rs.getBoolean("status")));
            }
        }
        catch(Exception e){
            
        }
        return pp;
    }
}
