/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Company.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
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
 * @author acer
 */

//DAO date access Object
//Loosely coupling
//thightly  coupling

public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    private User getUser(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        String profileDesc = rs.getString("profile_description");
        String address = rs.getString("address");
        Date brithDate = rs.getDate("brithDate");
        int nationalityId = rs.getInt("Nationality_id");
        int brithplaceid = rs.getInt("brithplace_id");
        String nationalitStr = rs.getString("nationality");
        String brithplaceStr = rs.getString("brithplace");
        Country nationality = new Country(nationalityId, null, nationalitStr);
        Country brithplace = new Country(brithplaceid, brithplaceStr, null);
        return new User(id, name, surname, phone, email, profileDesc, address, brithDate, nationality, brithplace);
    }
    private User getUserSimpl(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        String profileDesc = rs.getString("profile_description");
        String address = rs.getString("address");
        Date brithDate = rs.getDate("brithDate");
        int nationalityId = rs.getInt("Nationality_id");
        int brithplaceid = rs.getInt("brithplace_id");
        User user= new User(id, name, surname, phone, email, profileDesc, address, brithDate, null, null);
        user.setPassword(rs.getString("password"));
        return user;
    }

    @Override
   public List<User> getAll(String name,String surname,Integer nationalityId) {
        List<User> result = new ArrayList<>();
        try (Connection c = connection()) {
              String sql ="select " +
                    " u.* " +
                    ",c.name as brithplace " +
                    ",n.nationality  " +
                    "from user u " +
                    "LEFT JOIN country n " +
                    "on  u.Nationality_id = n.id " +
                    "LEFT JOIN country c " +
                    "on  u.brithplace_id = c.id where 1=1 ";
            if(name!=null && !name.trim().isEmpty()){
                sql +=" and u.name=?";
            }
            if (surname!=null && !surname.trim().isEmpty()){
                sql +=" and u.surname = ?";
            }
            if(nationalityId!=null){
                sql +=" and u.Nationality_id = ?";
            }
            PreparedStatement stmt = c.prepareStatement(sql);
           int i=1;
            if(name!=null && !name.trim().isEmpty()){
                System.out.println(name);
                stmt.setString(i,name);
                i++;
            }
            if(surname!=null && !surname.trim().isEmpty()){
                stmt.setString(i,surname);
                i++;
            }
            if(nationalityId!=null){
                stmt.setInt(i,nationalityId);
            }
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                User u = getUser(rs);
                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    @Override
    public boolean updateUser(User u) {
        try (Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement("update user set name=?,surname=?,phone=?,email=?,profile_Description=?,brithdate=?,address=?,brithplace_id=?,nationality_id=? where id=?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurName());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getProfileDesc());
            stmt.setDate(6, u.getBrithDate());
            stmt.setString(7, u.getAddress());
            stmt.setInt(8, u.getBirthPlace().getId());
            stmt.setInt(9, u.getNationality().getId());
            stmt.setInt(10, u.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeUser(int id) {
        try (Connection c = connection()) {
            Statement stmt = c.createStatement();
            return stmt.execute("delete from  user  Where id=" + id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User result = null;
        try (Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement("select * from user u where u.email=? and u.password=?");
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs =stmt.executeQuery();
            while (rs.next()) {
                result = getUserSimpl(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  result;
    }

    @Override
    public User findByEmail(String email) {
        User result = null;
        try (Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement("select * from user u where u.email=?");
            stmt.setString(1, email);
            ResultSet rs =stmt.executeQuery();
            while (rs.next()) {
                result = getUserSimpl(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  result;
    }

    @Override
    public User getById(int userId) {
        User result = null;
        try (Connection c = connection()) {
            Statement stmt = c.createStatement();
            stmt.execute("select " +
                    " u.* " +
                    ",c.name as brithplace " +
                    ",n.nationality  " +
                    "from user u " +
                    "LEFT JOIN country n " +
                    "on  u.Nationality_id = n.id " +
                    "LEFT JOIN country c " +
                    "on  u.brithplace_id = c.id where u.id=" + userId);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                result = getUser(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private BCrypt.Hasher bcrypt=BCrypt.withDefaults();
    @Override
    public boolean addUser(User u) {
        try (Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement("insert into  user (name,surname,phone,email,password,profile_Description,brithdate,address) values (?,?,?,?,?,?,?,?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurName());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, bcrypt.hashToString(4,u.getPassword().toCharArray()));
            stmt.setString(6, u.getProfileDesc());
            stmt.setDate(7, u.getBrithDate());
            stmt.setString(8, u.getAddress());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        User u = new User(0,"test","test","test","test",null,null,null,null,null);
        u.setPassword("12345");
         new UserDaoImpl().addUser(u);
    }
}
