/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Company.dao.inter;

import com.Company.entity.EmploymentHistory;
import com.Company.entity.User;
import com.Company.entity.UserSkill;
import java.util.List;

/**
 *
 * @author acer
 */
public interface UserEmploymentHistoryDaoInter {
    
    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId);
    
    
}
