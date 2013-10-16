package com.example.sqliteprovider;

import android.net.Uri;
import android.provider.BaseColumns;

public class User {

    public static final Uri URI = Uri.parse( "content://" + ExampleProvider.AUTHORITY + "/user/" );

    private final String userName;
    private final String DOB;

    private User(String userName, String DOB){
        this.userName = userName;
        this.DOB = DOB;
    }

    public static User create(String userName, String DOB){
        return new User( userName, DOB );
    }

    public String getUserName() {
        return userName;
    }

    public String getDOB() {
        return DOB;
    }

    public interface  Columns extends BaseColumns{
        String NAME = "name";
        String DOB = "dob";
    }
}
