/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Company.main;

import com.Company.bean.User;
import com.Company.dao.impl.UserDaoImpl;
import com.Company.dao.inter.UserDaoInter;
import java.util.List;

public class Main {
    
  
    
    
    public static void main(String[] args) throws Exception {
        UserDaoInter userDao = new UserDaoImpl();
        
//        userDao.removeUser(1);
//        
//        List<User> user =userDao.getAll();
//        
//        System.out.println("List="+user);

       User u=userDao.getById(3);
       u.setName("Akif");
       userDao.updateUser(u);

      
    }
}
