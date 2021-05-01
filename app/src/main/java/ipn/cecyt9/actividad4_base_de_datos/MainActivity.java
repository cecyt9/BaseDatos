package ipn.cecyt9.actividad4_base_de_datos;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button agregar,siguiente,anterior,ultimo,primero,eliminar,actualizar;
    EditText txtNombre,contra;
    Cursor cursor;
    TextView ID,Nombre,Clave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargaDatos();
        agregar = (Button) findViewById(R.id.agregar);
        txtNombre = (EditText) findViewById(R.id.nombre);
        contra = (EditText) findViewById(R.id.contra);
        siguiente = (Button) findViewById(R.id.adelante);
        anterior = (Button) findViewById(R.id.atras);
        ultimo = (Button) findViewById(R.id.ultimo);
        primero = (Button) findViewById(R.id.primero);
        eliminar = (Button) findViewById(R.id.eliminar);
        ID = (TextView) findViewById(R.id.ED);
        Nombre = (TextView) findViewById(R.id.mosNombre);
        Clave = (TextView)findViewById(R.id.mosCLave);
        actualizar = (Button)findViewById(R.id.actualizar);

        if (cursor.moveToFirst()){
            ID.setText(cursor.getString(0));
            Nombre.setText(cursor.getString(1));
            Clave.setText(cursor.getString(2));
        }
        agregar.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Auxiliar sql = new Auxiliar(getApplicationContext(), "DBCurso",
                        null, 1);
                //Nombre de la base, version anterior, version nueva
                final SQLiteDatabase db = sql.getWritableDatabase();

                if (db != null)
                {
                    ContentValues nuevoRegistro = new ContentValues();
                    nuevoRegistro.put("nombre", txtNombre.getText().toString());
                    nuevoRegistro.put("clave", contra.getText().toString());
                    try {
                        db.insert("Usuarios", null, nuevoRegistro);
                        Toast.makeText(getApplicationContext(), "Registro agregado", Toast.LENGTH_LONG).show();
                    } catch (Exception e){
                        Toast.makeText(getApplicationContext(), "Error al agregar: " + e, Toast.LENGTH_LONG).show();
                    }
                    db.close();
                }
                else{
                    Toast.makeText(getApplicationContext(), "No existe la base de datos", Toast.LENGTH_LONG).show();
                }
            }
        });

        siguiente.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if(cursor.moveToNext())
                {
                    ID.setText(cursor.getString(0));
                    Nombre.setText(cursor.getString(1));
                    Clave.setText(cursor.getString(2));
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Ya no hay", Toast.LENGTH_LONG).show();
                }
            }
        });

        anterior.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if(cursor.moveToPrevious())
                {
                    ID.setText(cursor.getString(0));
                    Nombre.setText(cursor.getString(1));
                    Clave.setText(cursor.getString(2));
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Ya no hay mas", Toast.LENGTH_LONG).show();
                }

            }
        });

        primero.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if(cursor.moveToFirst())
                {
                    ID.setText(cursor.getString(0));
                    Nombre.setText(cursor.getString(1));
                    Clave.setText(cursor.getString(2));
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "No hay primero", Toast.LENGTH_LONG).show();
                }

            }
        });

        ultimo.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if(cursor.moveToLast())
                {
                    ID.setText(cursor.getString(0));
                    Nombre.setText(cursor.getString(1));
                    Clave.setText(cursor.getString(2));
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "No hay ultimo", Toast.LENGTH_LONG).show();
                }

            }
        });
        eliminar.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Auxiliar sql = new Auxiliar(getApplicationContext(),"DBCurso",null,1);
                final SQLiteDatabase db = sql.getWritableDatabase();

                if (db != null){
                    String[] args = new String[] { txtNombre.getText().toString()};

                    try {
                        db.delete("Usuarios", "nombre=?", args);
                        Toast.makeText(getApplicationContext(), "Registro Eliminado", Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(), "Error al eliminar", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        actualizar.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View e) {


            }
        });
        //Para actualizar
        // valores.put("clave",txtClave.getText().toString());
        // db.update ("Usuarios",valores,"nombre=?",args)

    }

    private void cargaDatos(){
        Auxiliar sql = new Auxiliar(getApplicationContext(), "DBCurso",null,1);

        final SQLiteDatabase db = sql.getReadableDatabase();
        if(db != null){
            String[] columnas = new String[] {"_id", "nombre","clave"};
            try{ //SELECT * From Usuarios
                cursor = db.query("Usuarios", columnas, null, null, null, null, null);
            }
            catch (Exception e){
                Toast.makeText(getApplicationContext(), "Error!!" + e, Toast.LENGTH_LONG).show();
            }
        }
    }

}