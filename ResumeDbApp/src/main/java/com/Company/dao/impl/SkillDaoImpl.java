/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Company.dao.impl;

import com.Company.dao.inter.AbstractDAO;
import com.Company.dao.inter.SkillDaoInter;
import com.Company.entity.Skill;
import com.Company.entity.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter{

    private Skill getAllSkill(ResultSet rs) throws Exception {
    int id = rs.getInt("id");
    String name= rs.getString("name");
    Skill skill= new Skill(id, name);
    return skill;
    } 
    @Override
    public List<Skill> getAll() {
       List<Skill> result =new ArrayList<>();
        try(Connection c    = connection()) {
            Statement stmt = c.createStatement();
            stmt.execute("select * from skill");
            ResultSet rs =stmt.getResultSet();
            while(rs.next()){
                Skill u =getAllSkill(rs);
                result.add(u);
            }
        } catch (Exception ex) {
          ex.printStackTrace();
        }
        return result;
    }


    
}
