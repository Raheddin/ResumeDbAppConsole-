/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Company.main;
import com.Company.dao.impl.UserSkillDaoImpl;
import com.Company.dao.inter.*;

public class Main {
    
  
    
    
    public static void main(String[] args) throws Exception {
        UserDaoInter userDao =Contex.instanceUserDao();
       System.out.println(userDao.getAll("Akif",null,0));
       // System.out.println(userDao.getById(2));
       /// System.out.println(userDao.getAllskillByUserId(2));
    //    UserSkillDaoInter s =new UserSkillDaoImpl();
      //  System.out.println(s.getAllskillByUserId(2));
      
    }
}
