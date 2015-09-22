package br.com.cast.turmaformacao.mytaskmanager.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.mytaskmanager.model.entities.User;

/**
 * Created by Administrador on 21/09/2015.
 */
public class UserContract {
    public static final String TABLE = "user";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";

    public static final String[] COLUMNS = {TABLE,ID,NAME, PASSWORD};

    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();
        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(NAME + " TEXT, ");
        create.append(PASSWORD + " TEXT ");
        create.append(" ) ");
        return create.toString();
    }
    public static ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(ID, user.getId());
        values.put(NAME, user.getName());
        values.put(PASSWORD, user.getPassword());
        return values;
    }
    public static User getUser(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            User user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex(ID)));
            user.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(PASSWORD)));
            return user;
        }
        return null;
    }

    public static List<User> getUsers(Cursor cursor) {
        ArrayList<User> users = new ArrayList<>();
        while (cursor.moveToNext()) {
            users.add(getUser(cursor));
        }
        return users;
    }
}
