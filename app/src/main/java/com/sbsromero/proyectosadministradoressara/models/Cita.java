package com.sbsromero.proyectosadministradoressara.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by USERPC on 13/11/2017.
 */

public class Cita extends RealmObject {

    @PrimaryKey
    private int id;

    public Cita() {
    }

}
