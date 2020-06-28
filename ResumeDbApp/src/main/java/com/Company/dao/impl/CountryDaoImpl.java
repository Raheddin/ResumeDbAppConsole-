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
import com.Company.dao.inter.CountryDaoInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.Company.dao.inter.UserSkillDaoInter;
import com.Company.entity.Country;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.AbstractList;

/**
 *
 * @author acer
 */

//DAO date access Object
//Loosely coupling
//thightly  coupling

public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter{

    private Country getAllCountry(ResultSet rs) throws Exception{
    int id=rs.getInt("id");
    String name = rs.getString("name");
    String nationality=rs.getString("nationality");
    Country cn= new Country(id, name, nationality);
    return  cn;
    }
    @Override
    public List<Country> getAll() {
    List<Country> result = new ArrayList<>();
    try(Connection c = connection()){
        Statement stm = c.createStatement();
        stm.execute("select * from Country");
        ResultSet rs = stm.getResultSet();
        while(rs.next()){
        Country cn= getAllCountry(rs);
        result.add(cn);
        }
    }
    catch(Exception ex){
    ex.printStackTrace();
    }
    return result;
            
    }
    
}
