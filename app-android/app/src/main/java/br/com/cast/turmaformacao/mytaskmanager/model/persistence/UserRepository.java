package br.com.cast.turmaformacao.mytaskmanager.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.mytaskmanager.model.entities.Task;
import br.com.cast.turmaformacao.mytaskmanager.model.entities.User;

public final class UserRepository {
    private UserRepository() {
        super();
    }

    public static void save(User user) {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        ContentValues values = UserContract.getContentValues(user);

        if (user.getId() == null) {
            db.insert(UserContract.TABLE, null, values);
        } else {
            String where = TaskContract.ID + " = ? ";
            String[] params = {user.getId().toString()};
            db.update(UserContract.TABLE, values, where, params);
        }

        db.close();
        dataBaseHelper.close();
    }

    public static void delete(Long id){
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        String where = UserContract.ID + " = ? ";
        String[] params = {id.toString()};
        db.delete(UserContract.TABLE,where,params);

        db.close();
        dataBaseHelper.close();
    }

    public static List<User> getAll() {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        List<User> values;

        Cursor cursor = db.query(UserContract.TABLE, UserContract.COLUMNS, null, null, null, null, UserContract.ID);

        values = UserContract.getUsers(cursor);

        db.close();
        dataBaseHelper.close();

        return values;
    }

    public static User getById(Long id) {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        String where = UserContract.ID + " = ? ";
        String[] params = {id.toString()};
        List<User> values;

        Cursor cursor = db.query(UserContract.TABLE, UserContract.COLUMNS, where, params, null, null, null);

        values = UserContract.getUsers(cursor);

        db.close();
        dataBaseHelper.close();
        return values != null ? values.get(0) : null;
    }
}
