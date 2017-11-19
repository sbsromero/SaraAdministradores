package com.sbsromero.proyectosadministradoressara.app;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.sbsromero.proyectosadministradoressara.models.Admin;
import com.sbsromero.proyectosadministradoressara.models.Monitor;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by USERPC on 13/11/2017.
 */

public class MyApplication extends Application {

    public static AtomicInteger MonitorId = new AtomicInteger();
    public static AtomicInteger AdminId = new AtomicInteger();

    @Override
    public void onCreate() {
        super.onCreate();

        setUpRealmConfig();
        Realm realm = Realm.getDefaultInstance();
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());

        MonitorId = getIdByTable(realm, Monitor.class);
        AdminId = getIdByTable(realm, Admin.class);
        realm.close();
    }

    private void setUpRealmConfig(){
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass){
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size() > 0 ) ? new AtomicInteger(results.max("id").intValue()) : new AtomicInteger();
    }
}
