package marcos.example.com.miscontactos;

import android.app.Fragment;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import marcos.example.com.miscontactos.util.ContactListAdapter;
import marcos.example.com.miscontactos.util.ContactReciver;
import marcos.example.com.miscontactos.util.Contacto;

/**
 * Created by Marcos on 08/02/2016.
 */
public class ListaContactosFragment extends Fragment{

    private ArrayAdapter<Contacto> adapter;
    private ListView contactListView;
    private ContactReciver reciver;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_lista_contactos, container,false);
        inicializarComponentes(rootView);
        setHasOptionsMenu(true);//Habilita el ActionBar de este fragment para tener botones
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        reciver = new ContactReciver(adapter);
        getActivity().registerReceiver(reciver, new IntentFilter("listacontactos"));
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(reciver);
    }
    private void inicializarComponentes(View view){
        contactListView =(ListView) view.findViewById(R.id.listView);
        adapter = new ContactListAdapter(getActivity(),new ArrayList<Contacto>());
        //Se configura para que el "adapter" notifique los cambios en el "dataset" automaticamente
        adapter.setNotifyOnChange(true);
        contactListView.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_eliminar_contacto: eliminarContacto(item);return true;
            default: return super.onOptionsItemSelected(item);
        }


    }

    private void eliminarContacto(MenuItem item) {
        SparseBooleanArray array = contactListView.getCheckedItemPositions();
        ArrayList<Contacto> seleccion = new ArrayList<Contacto>();
        for(int i=0;i<array.size();i++){
            //Posicion del contacto en el adaptador
            int posicion =array.keyAt(i);
            if(array.valueAt(i)) seleccion.add(adapter.getItem(posicion));
            Intent intent =new Intent("listacontactos");
            intent.putExtra("operacion", ContactReciver.CONTACTO_ELIMINADO);
            intent.putExtra("datos", seleccion);
            getActivity().sendBroadcast(intent);
            contactListView.clearChoices();
        }
    }
}
