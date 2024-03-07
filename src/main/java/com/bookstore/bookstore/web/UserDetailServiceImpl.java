package com.bookstore.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bookstore.bookstore.domain.AppUser;
import com.bookstore.bookstore.domain.AppUserRepository;


//This class is used by spring security to authenticate and authorize user

@Service
public class UserDetailServiceImpl implements UserDetailsService{

    private final AppUserRepository urepository;

    @Autowired
    public UserDetailServiceImpl(AppUserRepository userRpository){
            this.urepository = userRpository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {   
    	AppUser curruser = urepository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
    }  

}
