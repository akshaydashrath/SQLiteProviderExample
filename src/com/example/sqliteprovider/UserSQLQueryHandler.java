package com.example.sqliteprovider;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentValues;

public class UserSQLQueryHandler {

    private final AsyncQueryHandler queryHandler;

    private UserSQLQueryHandler(ContentResolver cr) {
        queryHandler = new AsyncQueryHandler( cr ) {
        };
    }

    public static UserSQLQueryHandler get(ContentResolver cr) {
        UserSQLQueryHandler generator = new UserSQLQueryHandler( cr );
        return generator;
    }

    public void insert(User user) {
        ContentValues values = new ContentValues();
        values.put( User.Columns.NAME, user.getUserName() );
        values.put( User.Columns.DOB, user.getDOB() );
        queryHandler.startInsert( 0, 0, User.URI, values );
    }

    public void clear() {
        queryHandler.startDelete( 0, 0, User.URI, null, null );
    }
}
