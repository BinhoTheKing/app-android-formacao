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

    public static final String COLUMNS[] = {ID, NAME};

    public static String getCreateTable() {
        StringBuilder create = new StringBuilder();
        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY ");
        create.append(NAME + " TEXT ");
        create.append(" ) ");
        return create.toString();
    }

    public static ContentValues getContentValues(Label label) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, label.getId());
        contentValues.put(NAME, label.getName());
        return contentValues;
    }

    public static Label getLabel(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Label label = new Label();
            label.setId(cursor.getInt(cursor.getColumnIndex(ID)));
            label.setName(cursor.getString(cursor.getColumnIndex(NAME)));
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
