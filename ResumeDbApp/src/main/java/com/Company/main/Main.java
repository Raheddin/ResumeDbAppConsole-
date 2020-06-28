/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Company.main;
import com.Company.dao.inter.CountryDaoInter;
import com.Company.dao.inter.SkillDaoInter;
import com.Company.dao.inter.UserDaoInter;
import com.Company.dao.inter.UserEmploymentHistoryDaoInter;

public class Main {
    
  
    
    
    public static void main(String[] args) throws Exception {
        CountryDaoInter userDao =Contex.countryDao();
        System.out.println(userDao.getAll());
      
       /// System.out.println(userDao.getAllskillByUserId(2));
      
    }
}
