/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Company.dao.inter;

import com.Company.entity.Skill;
import java.util.List;

/**
 *
 * @author acer
 */
public interface SkillDaoInter {
    
    public List<Skill> getAll();
    
    public boolean insertSkill(Skill s);
    
    
}
