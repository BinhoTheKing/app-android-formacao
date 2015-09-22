package br.com.cast.turmaformacao.mytaskmanager.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.mytaskmanager.model.entities.Label;


public final class LabelRepository {
    private LabelRepository() {
        super();
    }

    public static void save(Label label) {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues labelValues = LabelContract.getContentValues(label);
        if (label.getId() == null) {
            db.insert(LabelContract.TABLE, null, labelValues);
        } else {
            String where = LabelContract.ID + " = ?";
            String[] params = {label.getId().toString()};
            db.update(LabelContract.TABLE, labelValues, where, params);
        }
        db.close();
        dataBaseHelper.close();
    }

    public static void delete(Integer id){
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        String where = LabelContract.ID + " = ?";
        String[] params = {id.toString()};
        db.delete(LabelContract.TABLE, where, params);

        db.close();
        dataBaseHelper.close();
    }

    public static Label getById(Long id){
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Label label;

        String where = LabelContract.ID + " = ?";
        String[] params = {id.toString()};
        Cursor cursor = db.query(LabelContract.TABLE, LabelContract.COLUMNS, where, params, null, null, null);
        label = LabelContract.getLabel(cursor);

        db.close();
        dataBaseHelper.close();
        return label;
    }

    public static List<Label> getAll(){
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        List<Label> labels;
        Cursor cursor = db.query(LabelContract.TABLE, LabelContract.COLUMNS, null, null, null, null, LabelContract.ID);
        labels = LabelContract.getLabels(cursor);
        db.close();
        dataBaseHelper.close();
        return labels;
    }
}
