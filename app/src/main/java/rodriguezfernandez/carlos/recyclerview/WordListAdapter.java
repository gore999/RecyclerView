package rodriguezfernandez.carlos.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    @NonNull
    private final LinkedList<String> mWordList;
    //crear el inflador--> Pasar de xml a view.
    private LayoutInflater mInflater;
    //constructor
    public WordListAdapter(Context context, LinkedList<String> WordList){
        mWordList=WordList;
        // Crear el inflador. Necesitamos el contexto de esta aplicacion.
        mInflater=LayoutInflater.from(context);
    }
    //Crear el viewHolder
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mItemView=mInflater.inflate(R.layout.wordlist_item,viewGroup,false);
        //devolvemos el nuevo WordViewHolder, necesitamos pasarle el mItemView y el adapter.
        return new WordViewHolder(mItemView,this);
    }
    //Se le pasa una posicion y un viewHolder
    // Con la posicion, recuperamos los datos del elemento java (el string de la LinkedList)
    // Con el viewHolder, reemplazamos en cada elemento los datos con los datos de la lista.
    @Override
    public void onBindViewHolder(@NonNull WordViewHolder wordViewHolder, int i) {
        // i es la posicion del
        //Vincula al holder los valores de la listaa. hace el trabajo de vincular datos a vista.
        String mCurrent=mWordList.get(i);// Recupero el valor de la lista, segun la posicion que se me solicite.
        wordViewHolder.wordItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        //debe devolver un entero que de el numero de elementos de la lista.
        return mWordList.size();
    }



    public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //Contendrá un adaptador y un textview
        public final TextView wordItemView;
        final WordListAdapter mAdapter;
        //El constructor necesítará un view y un adaptador para rellenar los anteriores.
        public WordViewHolder(@NonNull View itemView, WordListAdapter adapter) {
            super(itemView);
            //Crear los elementos que se tomarán del layout.
            wordItemView= itemView.findViewById(R.id.word);
            //Crear el adaptador.
            this.mAdapter=adapter;
            //Añadir el escuchador (el propio WordViewHolder).
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            //Que elemento he pulsado? devuelve la posicion.
            int mPosition=getLayoutPosition();
            //Creo un String con los datos que contiene el view.
            String element=mWordList.get(mPosition);
            //Modifico el elemento de esa posicion, cambiando el texto.
            mWordList.set(mPosition,"Clicked!"+element);
            mAdapter.notifyDataSetChanged();
        }
    }
}
