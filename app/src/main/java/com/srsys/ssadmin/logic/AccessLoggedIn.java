package com.srsys.ssadmin.logic;

public class AccessLoggedIn
{
    private static AccessLoggedIn mInstance= null;

    public boolean logged_in;
    public boolean once_logged_in;
    public String logged_in_username;

    protected AccessLoggedIn(){}

    public static synchronized AccessLoggedIn getInstance()
    {
        if(null == mInstance)
        {
            mInstance = new AccessLoggedIn();
        }
        return mInstance;
    }

    public void setLogged_in(boolean logged_in)
    {
        this.logged_in = logged_in;
    }

}
