package marcos.example.com.miscontactos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;

import marcos.example.com.miscontactos.util.ContactListAdapter;
import marcos.example.com.miscontactos.util.Contacto;
import marcos.example.com.miscontactos.util.TextChangeListener;

public class MainActivity extends AppCompatActivity {

    private EditText txtNombre, txtTelefono, txtEmail, txtDireccion;
    private ArrayAdapter<Contacto> adapter;
    private ListView listaContactos;
    private ImageView imageViewContactos;
    private Button btnAgregar;
    private TabHost contenedor;
    private int request_code=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarComponentesUI();
        inicializarContactos();
        inicializarTabs();
    }

    private void inicializarContactos() {
        adapter = new ContactListAdapter(this, new ArrayList<Contacto>());
        listaContactos.setAdapter(adapter);

    }

    private void inicializarTabs(){
        //Necesario rellenar las pestañas, sino no se ven
        contenedor=(TabHost)findViewById(R.id.tabHost);
        contenedor.setup();

        TabHost.TabSpec spec=contenedor.newTabSpec("tag1");
        spec.setContent(R.id.peCrearContacto);
        spec.setIndicator("Contactos");
        contenedor.addTab(spec);

        spec=contenedor.newTabSpec("tag2");
        spec.setContent(R.id.peLista);
        spec.setIndicator("Lista Contactos");
        contenedor.addTab(spec);
        //------------------------------------------------------------------------
    }

    private void inicializarComponentesUI() {
        txtNombre = (EditText)findViewById(R.id.txtNombre);
        txtTelefono = (EditText)findViewById(R.id.txtTelefono);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtDireccion = (EditText)findViewById(R.id.txtDireccion);
        listaContactos=(ListView)findViewById(R.id.listView);
        imageViewContactos =(ImageView)findViewById(R.id.imageViewContactos);

        txtNombre.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence seq, int start, int before, int count) {
                btnAgregar = (Button) findViewById(R.id.btnGuardar);
                btnAgregar.setEnabled(!seq.toString().trim().isEmpty());
            }
        });
    }
}
