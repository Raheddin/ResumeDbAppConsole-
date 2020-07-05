/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Company.dao.impl;

import com.Company.entity.Skill;
import com.Company.entity.User;
import com.Company.entity.UserSkill;
import com.Company.dao.inter.AbstractDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.Company.dao.inter.UserSkillDaoInter;

/**
 *
 * @author acer
 */

//DAO date access Object
//Loosely coupling
//thightly  coupling

public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter{
    
   private UserSkill getUserSKILL(ResultSet rs) throws Exception{
    int userSkillId = rs.getInt("userSkillId");   
    int userId = rs.getInt("id");
    int skillId = rs.getInt("skill_id");
    String skill_name = rs.getString("skill_name");
    int power  = rs.getInt("power");
    return new UserSkill(userSkillId,new User(userId), new Skill(skillId,skill_name), power);
    }

    @Override
    public List<UserSkill> getAllskillByUserId(int userId) {
      List<UserSkill> result =new ArrayList<>();
        try(Connection c    = connection()) {
            PreparedStatement stmt = c.prepareStatement("SELECT" +
                " us.id as userSkillId, "+  
                " u.*," +
                " us.skill_id," +
                " s.NAME AS Skill_name," +
                " us.power " +
                " FROM" +
                " user_skill us" +
                " LEFT JOIN USER u ON u.id = us.user_id" +
                " LEFT JOIN skill s ON s.id = us.skill_id " +
                " WHERE" +
                " u.id = ?");
                            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs =stmt.getResultSet();
            while(rs.next()){
                UserSkill u =getUserSKILL(rs);
                result.add(u);
            }
        } catch (Exception ex) {
          ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean insertUserSkill(UserSkill u) {
      try(Connection c    = connection()){
      PreparedStatement stm=c.prepareStatement("insert into user_skill (skill_id,user_id,power) values (?,?,?)");
      stm.setInt(1, u.getSkill().getId());
       stm.setInt(2, u.getUser().getId());
        stm.setInt(3, u.getPower());
            return stm.execute();
          }catch(Exception ex){
           ex.printStackTrace();
           return false;
        }
    }

    @Override
    public boolean updateUserSkill(UserSkill u) {
     try(Connection c    = connection()){
      PreparedStatement stm=c.prepareStatement("update user_skill set skill_id=?,user_id=?,power=? where id=? ");
      stm.setInt(1, u.getSkill().getId());
       stm.setInt(2, u.getUser().getId());
        stm.setInt(3, u.getPower());
          stm.setInt(4, u.getId());
            return stm.execute();
          }catch(Exception ex){
           ex.printStackTrace();
           return false;
        }
    }

    @Override
    public boolean removeUserSkill(int id) {
     try(Connection c    = connection()){
      PreparedStatement stm=c.prepareStatement("delete from user_skill where id=?");
      stm.setInt(1, id);
      return stm.execute();
    }catch(Exception ex){
     ex.printStackTrace();
     return false;
        }
    }
}
