package marcos.example.com.miscontactos.util;

import android.app.Activity;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import marcos.example.com.miscontactos.R;

/**
 * Created by Marcos on 07/02/2016.
 */
public class ContactListAdapter extends ArrayAdapter<Contacto> {

    private Activity ctx;
    public ContactListAdapter(Activity context,List<Contacto> contactos){

        super(context, R.layout.listview_item, contactos);
        this.ctx = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view==null) {
            view = ctx.getLayoutInflater().inflate(R.layout.listview_item, parent, false);
        }

        Contacto actual = this.getItem(position);
        inicializarCamposDeTexto(view, actual);
        return view;
    }

    private void inicializarCamposDeTexto(View view, Contacto actual) {
        TextView textView =(TextView) view.findViewById(R.id.viewNombre);
        textView.setText(actual.getNombre());

        textView =(TextView) view.findViewById(R.id.viewTelefono);
        textView.setText(actual.getTelefono());

        textView =(TextView) view.findViewById(R.id.viewDireccion);
        textView.setText(actual.getDireccion());

        textView =(TextView) view.findViewById(R.id.viewEmail);
        textView.setText(actual.getEmail());

        ImageView ivContactImage = (ImageView) view.findViewById(R.id.ivContactImage);
        //Con el "parse" convertimos un String en un Uri (imagen)
        ivContactImage.setImageURI(Uri.parse(actual.getImageUri()));

    }
}
