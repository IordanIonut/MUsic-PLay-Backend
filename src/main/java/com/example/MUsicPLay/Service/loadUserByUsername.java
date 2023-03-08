package com.example.MUsicPLay.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface loadUserByUsername {
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
}
