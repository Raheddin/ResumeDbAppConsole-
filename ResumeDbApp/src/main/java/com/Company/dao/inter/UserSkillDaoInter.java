/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Company.dao.inter;

import com.Company.entity.User;
import com.Company.entity.UserSkill;
import java.util.List;

/**
 *
 * @author acer
 */
public interface UserSkillDaoInter {
    
    public List<UserSkill> getAllskillByUserId(int userId);
    
    public boolean insertUserSkill(UserSkill u);
    
    public boolean updateUserSkill(UserSkill u);
    
    public boolean removeUserSkill(int id);
    
    
}
