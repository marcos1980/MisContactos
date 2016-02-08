package marcos.example.com.miscontactos;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;

import marcos.example.com.miscontactos.util.ContactReciver;
import marcos.example.com.miscontactos.util.Contacto;
import marcos.example.com.miscontactos.util.TextChangeListener;

/**
 * Created by Marcos on 08/02/2016.
 */
public class CrearContactoFragment extends Fragment implements View.OnClickListener {

    private EditText txtNombre, txtTelefono, txtEmail, txtDireccion;
    private ImageView imageViewContactos;
    private Button btnAgregar;
    private TabHost contenedor;
    private int request_code=1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_crear_contactos, container, false);
        inicializarComponentes(rootView);
        return  rootView;
    }

    private void inicializarComponentes(final View view) {
        txtNombre = (EditText) view.findViewById(R.id.txtNombre);
        txtTelefono = (EditText) view.findViewById(R.id.txtTelefono);
        txtEmail = (EditText) view.findViewById(R.id.txtEmail);
        txtDireccion = (EditText) view.findViewById(R.id.txtDireccion);
        imageViewContactos = (ImageView) view.findViewById(R.id.imageViewContactos);
        txtNombre.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence seq, int start, int before, int count) {
                btnAgregar = (Button) view.findViewById(R.id.btnGuardar);
                btnAgregar.setEnabled(!seq.toString().trim().isEmpty());
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view == btnAgregar){
            agregarContacto(
                    txtNombre.getText().toString(),
                    txtTelefono.getText().toString(),
                    txtDireccion.getText().toString(),
                    txtEmail.getText().toString(),
                    String.valueOf(imageViewContactos.getTag()) //Obtenemos el atributo TAG con la Uri de la imagen
            );

            String mesg = String.format("%s ha sido agragado a la lista!", txtNombre.getText());
            Toast.makeText(view.getContext(), mesg, Toast.LENGTH_SHORT).show();
            btnAgregar.setEnabled(false);
            limpiarCampos();

        }else if(view==imageViewContactos){
            Intent intent = null;
            //Verificamos la version de la plataforma
            if(Build.VERSION.SDK_INT < 19 ){
                //Android JellyBean 4.3 y anteriores
                intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
            }else{
                //Android KitKat 4.4 o superior
                intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
            }
            intent.setType("image/*");
            startActivityForResult(intent,request_code);
        }
    }

    private void agregarContacto(String nombre, String direccion, String telefono,String email,String imageUri) {
        Contacto nuevo = new Contacto(nombre,telefono,direccion,email,imageUri);
        Intent intent = new Intent("listacontactos");
        intent.putExtra("operacion", ContactReciver.CONTACTO_AGREGADO);
        intent.putExtra("datos", nuevo);
        getActivity().sendBroadcast(intent);
    }

    private void limpiarCampos() {
        txtNombre.getText().clear();
        txtDireccion.getText().clear();
        txtEmail.getText().clear();
        txtTelefono.getText().clear();
        imageViewContactos.setImageResource(R.drawable.contactos);
        txtNombre.requestFocus();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode== Activity.RESULT_OK && requestCode==request_code){
            imageViewContactos.setImageURI(data.getData());
            //Utilizamos el atributo TAG para almacenar la URI al archivo seleccionado
            imageViewContactos.setTag(data.getData());
        }
    }
}
