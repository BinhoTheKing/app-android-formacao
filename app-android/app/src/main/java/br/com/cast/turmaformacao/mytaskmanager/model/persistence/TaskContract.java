package br.com.cast.turmaformacao.mytaskmanager.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.mytaskmanager.model.entities.Task;
import br.com.cast.turmaformacao.mytaskmanager.model.services.LabelBusinessService;
import br.com.cast.turmaformacao.mytaskmanager.model.services.UserBusinessService;

public class TaskContract {
    public static final String TABLE = "task";
    public static final String ID = "id";
    public static final String WEB_ID = "web_id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String LABEL_ID = "label_id";
    public static final String USER_ID = "user_id";

    public static final String COLUMNS[] = {ID, WEB_ID, NAME, DESCRIPTION, LABEL_ID, USER_ID};

    private TaskContract() {
    }

    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();
        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
	    create.append(WEB_ID + " INTEGER UNIQUE NOT NULL, ");
        create.append(NAME + " TEXT, ");
        create.append(DESCRIPTION + " TEXT, ");
        create.append(LABEL_ID + " INTEGER, ");
        create.append(USER_ID + " INTEGER, ");
        create.append("FOREIGN KEY (" + LABEL_ID + ") REFERENCES " + LabelContract.TABLE);
        create.append(" ( " + LabelContract.ID + " ), ");
        create.append("FOREIGN KEY (" + USER_ID + ") REFERENCES " + UserContract.TABLE);
        create.append(" ( " + UserContract.ID + " ) ");
        create.append(" ) ");
        return create.toString();
    }

    public static ContentValues getContentValues(Task task) {
        ContentValues values = new ContentValues();
        values.put(ID, task.getId());
	    values.put(WEB_ID, task.getWebId());
        values.put(NAME, task.getName());
        values.put(DESCRIPTION, task.getDescription());
        values.put(LABEL_ID,task.getLabel().getId());
        values.put(USER_ID,task.getLabel().getId());
        return values;
    }


    public static Task getTask(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Task task = new Task();
            task.setId(cursor.getLong(cursor.getColumnIndex(ID)));
	        task.setWebId(cursor.getLong(cursor.getColumnIndex(WEB_ID)));
            task.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            task.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
            task.setLabel(LabelBusinessService.getById(cursor.getLong(cursor.getColumnIndex(LABEL_ID))));
            task.setUser(UserBusinessService.getById(cursor.getLong(cursor.getColumnIndex(USER_ID))));
            return task;
        }
        return null;
    }

    public static List<Task> getTasks(Cursor cursor) {
        ArrayList<Task> tasks = new ArrayList<>();
        while (cursor.moveToNext()) {
            tasks.add(getTask(cursor));
        }
        return tasks;
    }
}
