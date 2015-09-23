package br.com.cast.turmaformacao.mytaskmanager.util;

import android.content.Context;

public class ApplicationUtil {
    private static Context applicationContext;
    private ApplicationUtil(){
        super();
    }
    public static void setContext(Context context){
        applicationContext = context;
    }

    public static Context getApplicationContext(){
        return applicationContext;
    }

}
