/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Company.dao.inter;

import com.Company.bean.User;
import java.util.List;

/**
 *
 * @author acer
 */
public interface UserDaoInter {
    
    public List<User> getAll();
    
    public User getById(int userId);
    
    public boolean updateUser(User u);
    
    public boolean removeUser(int id);
    
    
}
