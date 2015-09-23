package br.com.cast.turmaformacao.mytaskmanager.util;

import android.widget.EditText;

public class FormHelper {
    private FormHelper() {
    }

    public static boolean validateRequired(String requiredMessage, EditText... editTexts) {
        boolean hasRequired = false;
        for (EditText editText :
                editTexts) {
            String textValue = editText.getText().toString();
            if (textValue.trim().isEmpty()) {
                editText.setError(requiredMessage);
                hasRequired = true;
            }
        }
        return hasRequired;
    }
}
