/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Company.dao.impl;

import com.Company.entity.Country;
import com.Company.entity.Skill;
import com.Company.entity.User;
import com.Company.entity.UserSkill;
import com.Company.dao.inter.AbstractDAO;
import com.Company.dao.inter.UserDaoInter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acer
 */

//DAO date access Object
//Loosely coupling
//thightly  coupling

public class UserDaoImpl extends AbstractDAO implements UserDaoInter{
   
    private User getUser(ResultSet rs) throws Exception{
    int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String profileDesc = rs.getString("profile_description");
                String address = rs.getString("address");
                Date  brithDate=rs.getDate("brithDate");
                int   nationalityId =rs.getInt("Nationality_id");
                int   brithplaceid =rs.getInt("brithplace_id");
                String nationalitStr =rs.getString("nationality");
                String brithplaceStr  = rs.getString("brithplace");  
                Country nationality=new Country(nationalityId, null, nationalitStr);
                Country brithplace=new Country(brithplaceid, brithplaceStr, null);
                return  new User(id,name,surname,phone,email,profileDesc,address,brithDate,nationality,brithplace);
    }

    @Override
    public List<User> getAll() {
        List<User> result =new ArrayList<>();
        try(Connection c    = connection()) {
            Statement stmt = c.createStatement();
            stmt.execute("select " +
            " u.* " +
            ",c.name as brithplace " +
            ",n.nationality  " +
            "from user u " +
            "LEFT JOIN country n " +
            "on  u.Nationality_id = n.id " +
            "LEFT JOIN country c " +
            "on  u.brithplace_id = c.id");
            ResultSet rs =stmt.getResultSet();
            while(rs.next()){
                User u =getUser(rs);
                result.add(u);
            }
        } catch (Exception ex) {
          ex.printStackTrace();
        }
        return result;
}
    
    @Override
    public boolean updateUser(User u) {
        try(Connection c    = connection()) {
            PreparedStatement stmt = c.prepareStatement("update user set name=?,surname=?,phone=?,email=?,profile_Description=?,brithdate=?,address=? where id=?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurName());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getProfileDesc());
            stmt.setDate(6, u.getBrithDate());
            stmt.setString(7, u.getAddress());
            stmt.setInt(8, u.getId());
            return stmt.execute();
        } catch (Exception ex) {
           ex.printStackTrace();
            return false;
        }
}

    @Override
    public boolean removeUser(int id) {
       try(Connection c    = connection()) {
            Statement stmt = c.createStatement();
            return stmt.execute("delete from  user  Where id="+id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } }

    @Override
    public User getById(int userId) {
      User result =null;
        try(Connection c    = connection()) {
            Statement stmt = c.createStatement();
            stmt.execute("select " +
            " u.* " +
            ",c.name as brithplace " +
            ",n.nationality  " +
            "from user u " +
            "LEFT JOIN country n " +
            "on  u.Nationality_id = n.id " +
            "LEFT JOIN country c " +
            "on  u.brithplace_id = c.id where u.id="+userId);
            ResultSet rs =stmt.getResultSet();
            while(rs.next()){
                result = getUser(rs);
            }
        } catch (Exception ex) {
          ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addUser(User u) {
 try(Connection c    = connection()) {
            PreparedStatement stmt = c.prepareStatement("insert into  user (name,surname,phone,email,profile_Description,brithdate,address) values (?,?,?,?,?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurName());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getProfileDesc());
            stmt.setDate(6, u.getBrithDate());
            stmt.setString(7, u.getAddress());
            return stmt.execute();
        } catch (Exception ex) {
           ex.printStackTrace();
            return false;
        }   
    }  
    
}
