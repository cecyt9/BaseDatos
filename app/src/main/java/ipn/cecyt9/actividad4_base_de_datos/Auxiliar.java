package ipn.cecyt9.actividad4_base_de_datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Auxiliar extends SQLiteOpenHelper {

    String SQL_Tabla = "CREATE TABLE Usuarios (_id INTEGER PRIMARY KEY " +
            "AUTOINCREMENT, nombre TEXT, clave TEXT)";

    public Auxiliar(@Nullable Context context, @Nullable String nombre_db, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombre_db, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_Tabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        db.execSQL(SQL_Tabla);
    }
}
