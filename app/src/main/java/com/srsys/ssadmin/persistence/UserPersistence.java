package com.srsys.ssadmin.persistence;

import com.srsys.ssadmin.objects.User;

import java.util.List;

public interface UserPersistence
{
    List<User> getUsers();

    void writeUser(String username, String password, String email);

    boolean deleteUser(User user);
}
