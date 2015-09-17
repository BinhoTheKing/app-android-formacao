package br.com.cast.turmaformacao.mytaskmanager.model.persistence;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import br.com.cast.turmaformacao.mytaskmanager.model.entities.Label;

/**
 * Created by Administrador on 17/09/2015.
 */
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
}
