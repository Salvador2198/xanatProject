package me.dellamite.appcompras;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductosHolder extends RecyclerView.ViewHolder {
    public TextView tvProductoNombre0;
    public ImageView ivImagenProducto;

    public TextView getTvProductoNombre0() {
        return tvProductoNombre0;
    }

    public ImageView getIvImagenProducto() {
        return ivImagenProducto;
    }

    public ProductosHolder(@NonNull View itemView) {
        super(itemView);

        ivImagenProducto = itemView.findViewById(R.id.ivImagenProducto);
        tvProductoNombre0 = itemView.findViewById(R.id.tvProductoNombre0);


    }
}
