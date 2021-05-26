package me.dellamite.appcompras;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListadoAdapter extends RecyclerView.Adapter<ListadoHolder> {
    private ArrayList<Lista> dataL;
    private Context context;

    public ListadoAdapter(Context context){
        this.context = context;

        dataL = new ArrayList<>();
    }


    @NonNull
    @Override
    public ListadoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_productos,parent, false);
        return new ListadoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListadoHolder holder, int position) {
        Lista lista = dataL.get(position);

        String pProducto = lista.getProducto().get(0);

        holder.getTvNombreP().setText( pProducto);

        holder.getTvPrecioC().setText(String.valueOf((int) lista.getPrecioC()));
        holder.getTvPrecioW().setText(String.valueOf((int) lista.getPrecioW()));
        holder.getTvPrecioS().setText(String.valueOf((int) lista.getPrecioS()));
    }

    @Override
    public int getItemCount() {
        return dataL.size();
    }

    public  void add(Lista lista){
        dataL.add(lista);
        notifyDataSetChanged();
    }
}
