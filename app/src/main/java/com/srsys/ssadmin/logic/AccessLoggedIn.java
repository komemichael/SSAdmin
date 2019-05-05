package com.srsys.ssadmin.logic;

import android.content.SharedPreferences;

public class AccessLoggedIn
{
    private static AccessLoggedIn mInstance = null;

    public boolean logged_in;
    SharedPreferences mPrefs;
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    protected AccessLoggedIn(){}

    public static synchronized AccessLoggedIn getInstance()
    {
        if(null == mInstance)
        {
            mInstance = new AccessLoggedIn();
        }
        return mInstance;
    }

    public void setSharedPreference(SharedPreferences sharedPreference)
    {
        this.mPrefs = sharedPreference;
    }

    public void setPrefVariable(String value)
    {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("LoggedIn", value).commit();
    }

    public void setLogged_in(boolean logged_in)
    {
        this.logged_in = logged_in;
    }

}
