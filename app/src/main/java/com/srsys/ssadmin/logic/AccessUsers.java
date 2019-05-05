package com.srsys.ssadmin.logic;

import com.srsys.ssadmin.Application.Services;
import com.srsys.ssadmin.objects.User;
import com.srsys.ssadmin.persistence.UserPersistence;

import java.util.ArrayList;
import java.util.List;

public class AccessUsers
{
    private UserPersistence data;

    public AccessUsers()
    {
        data = Services.getUserPersistence();
    }

    public AccessUsers(UserPersistence data)
    {
        this();
        this.data = data;
    }

    public List<User> getUsers()
    {
        return data.getUsers();
    }

    public boolean deleteuser(User user)
    {
        return data.deleteUser(user);
    }

    public String addUser(String user, String pass, String email)
    {
        try {
            Boolean valid = true;
            String errorMessage = "";

            String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

            if (!email.matches(regex))
            {
                valid = false;
                errorMessage = "Improper email";
            }


            if (pass.length() < 4)
            {
                valid = false;
                errorMessage = "Password must be greater than 6 characters";
                if (pass.equals(""))
                {
                    errorMessage = "Enter a password";
                }
            }

            if (user.equals(""))
            {
                valid = false;
                errorMessage = "Enter a user name";
            }

            if (valid == true)
                data.writeUser(user, pass, email);
            return errorMessage;
        }
        catch (Exception e)
        {
            return "Error";
        }
    }
}
