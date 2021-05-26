package me.dellamite.appcompras;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosHolder> implements View.OnClickListener{
    private ArrayList<Producto> data;
    private Context context;
    private View.OnClickListener listener;

    public ProductosAdapter(Context context){
        this.context = context;

        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public ProductosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.productos_orden,parent, false);
        view.setOnClickListener(listener);
        return new ProductosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductosHolder holder, int position) {
        Producto producto = data.get(position);

        holder.getTvProductoNombre0().setText(producto.getNombre());

        if (producto.getImagen()!=null){
            Glide.with(context).load(producto.getImagen()).into(holder.getIvImagenProducto());
            holder.getIvImagenProducto().setVisibility(View.VISIBLE);
        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void add(Producto producto){
        data.add(producto);
        notifyDataSetChanged();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }
}
