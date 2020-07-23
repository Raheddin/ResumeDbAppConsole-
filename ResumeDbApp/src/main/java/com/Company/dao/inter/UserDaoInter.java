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
public interface UserDaoInter {
    
    public List<User> getAll(String name,String surname,Integer nationalityId);
    
    public User getById(int userId);
    
    public boolean updateUser(User u);
    
    public boolean addUser(User u);
    
    public boolean removeUser(int id);

    public User findByEmailAndPassword(String email,String password);

    public User findByEmail(String email);
}
