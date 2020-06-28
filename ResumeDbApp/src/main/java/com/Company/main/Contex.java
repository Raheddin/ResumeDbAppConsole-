/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Company.main;

import com.Company.dao.impl.CountryDaoImpl;
import com.Company.dao.impl.SkillDaoImpl;
import com.Company.dao.impl.UserDaoImpl;
import com.Company.dao.impl.UserEmploymentHistoryDaoImpl;
import com.Company.dao.impl.UserSkillDaoImpl;
import com.Company.dao.inter.CountryDaoInter;
import com.Company.dao.inter.SkillDaoInter;
import com.Company.dao.inter.UserDaoInter;
import com.Company.dao.inter.UserEmploymentHistoryDaoInter;
import com.Company.dao.inter.UserSkillDaoInter;

/**
 *
 * @author acer
 */
public class Contex {
    public static UserDaoInter instanceUserDao(){
    return new UserDaoImpl();
    }
    
     public static UserSkillDaoInter instanceUserSkillDao(){
    return new UserSkillDaoImpl();
    }
     
      public static UserEmploymentHistoryDaoInter instanceEmploymentHistoryDao(){
    return new UserEmploymentHistoryDaoImpl();
    }
      
     public static SkillDaoInter skillDao(){
    return new SkillDaoImpl();
    }
     
     public static CountryDaoInter countryDao(){
    return new CountryDaoImpl();
    }
    
    
}
