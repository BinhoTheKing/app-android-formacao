package br.com.cast.turmaformacao.mytaskmanager.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.mytaskmanager.model.entities.Label;

public class LabelContract {
    public static final String TABLE = "label";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String COLOR = "color";

    public static final String COLUMNS[] = {ID, NAME, DESCRIPTION, COLOR};

    public static String getCreateTableScript() {
        StringBuilder create = new StringBuilder();
        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(NAME + " TEXT, ");
        create.append(DESCRIPTION + " TEXT, ");
        create.append(COLOR + " TEXT ");
        create.append(" ); ");
        return create.toString();
    }

    public static ContentValues getContentValues(Label label) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, label.getId());
        contentValues.put(NAME, label.getName());
        contentValues.put(DESCRIPTION, label.getDescription());
        contentValues.put(COLOR, label.getColor());
        return contentValues;
    }

    public static Label getLabel(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Label label = new Label();
            label.setId(cursor.getInt(cursor.getColumnIndex(ID)));
            label.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            label.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
            label.setColor(cursor.getString(cursor.getColumnIndex(COLOR)));
            return label;
        }
        return null;
    }

    public static List<Label> getLabels(Cursor cursor){
        ArrayList<Label> labels = new ArrayList<>();
        while(cursor.moveToNext()){
            labels.add(getLabel(cursor));
        }
        return labels;
    }
}