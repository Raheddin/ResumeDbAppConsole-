/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Company.dao.impl;

import com.Company.dao.inter.AbstractDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.Company.dao.inter.UserEmploymentHistoryDaoInter;
import com.Company.entity.EmploymentHistory;
import com.Company.entity.User;
import java.sql.Date;

/**
 *
 * @author acer
 */

//DAO date access Object
//Loosely coupling
//thightly  coupling

public class UserEmploymentHistoryDaoImpl extends AbstractDAO implements UserEmploymentHistoryDaoInter{
    
    
    private EmploymentHistory getUserEmploymentHistory(ResultSet rs) throws Exception{
        
        String header=rs.getString("header");
        String jobDescription = rs.getString("job_description");
        Date beginDate = rs.getDate("begin_date");
        Date endDate   = rs.getDate("end_date");
        int userId     = rs.getInt("user_id");
    EmploymentHistory emp =new EmploymentHistory(null, header, beginDate, endDate, jobDescription, new User(userId));
    return emp;
    }

    @Override
    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId) {
      List<EmploymentHistory> result =new ArrayList<>();
        try(Connection c    = connection()) {
            PreparedStatement stmt = c.prepareStatement("select * from employment_history where user_id=?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs =stmt.getResultSet();
            while(rs.next()){
                EmploymentHistory u =getUserEmploymentHistory(rs);
                result.add(u);
            }
        } catch (Exception ex) {
          ex.printStackTrace();
        }
        return result;
    }
    
}
