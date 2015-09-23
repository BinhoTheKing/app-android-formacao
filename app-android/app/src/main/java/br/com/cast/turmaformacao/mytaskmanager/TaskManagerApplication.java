package br.com.cast.turmaformacao.mytaskmanager;

import android.app.Application;

import br.com.cast.turmaformacao.mytaskmanager.util.ApplicationUtil;

public class TaskManagerApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationUtil.setContext(getApplicationContext());
    }
}
