package com.example.sqliteprovider;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MyActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {

    private ListView list;
    private UserSQLQueryHandler queryHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.main );
        getLoaderManager().initLoader( 0, null, this );
        queryHandler = UserSQLQueryHandler.get( getContentResolver());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.activity_main, menu );
        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                queryHandler.insert( User.make( "Akshay Dashrath", "15/03/1985" ) );
                break;
            case R.id.menu_clear:
                queryHandler.clear();
                break;
        }
        return super.onOptionsItemSelected( item );
    }

    public ListView getListView() {
        if (list == null) {
            list = (ListView) findViewById( android.R.id.list );
        }
        return list;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader( getApplicationContext(), User.URI, null, null, null, null );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (getListView().getAdapter() == null) {
            getListView().setAdapter( getNewAdapter( data ) );
        } else {
            ((SimpleCursorAdapter) getListView().getAdapter()).swapCursor( data );
        }
    }

    private SimpleCursorAdapter getNewAdapter(Cursor data) {
        return new SimpleCursorAdapter( getApplicationContext(), R.layout.list_item_user, data,
                                                           new String[]{User.Columns.NAME, User.Columns.DOB},
                                                           new int[]{android.R.id.text1, android.R.id.text2},
                                                           -1 );
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if (getListView() != null) {
            ((SimpleCursorAdapter) getListView().getAdapter()).swapCursor( null );
            getListView().setAdapter( null );
        }
    }
}
