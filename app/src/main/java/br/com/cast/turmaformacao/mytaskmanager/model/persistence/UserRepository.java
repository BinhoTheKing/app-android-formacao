package br.com.cast.turmaformacao.mytaskmanager.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.mytaskmanager.model.entities.Task;

public final class UserRepository {
    private UserRepository() {
        super();
    }

    public static void save(Task task) {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        ContentValues values = TaskContract.getContentValues(task);

        if (task.getId() == null) {
            db.insert(TaskContract.TABLE, null, values);
        } else {
            String where = TaskContract.ID + " = ? ";
            String[] params = {task.getId().toString()};
            db.update(TaskContract.TABLE, values, where, params);
        }

        db.close();
        dataBaseHelper.close();
    }

    public static void delete(Long id){
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        String where = TaskContract.ID + " = ? ";
        String[] params = {id.toString()};
        db.delete(TaskContract.TABLE,where,params);

        db.close();
        dataBaseHelper.close();
    }

    public static List<Task> getAll() {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        List<Task> values;

        Cursor cursor = db.query(TaskContract.TABLE, TaskContract.COLUMNS, null, null, null, null, TaskContract.ID);

        values = TaskContract.getTasks(cursor);

        db.close();
        dataBaseHelper.close();

        return values;
    }

}
