package me.dellamite.appcompras;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListadoHolder extends RecyclerView.ViewHolder {
    private TextView tvNombreP, tvPrecioC, tvPrecioW, tvPrecioS;

    public TextView getTvNombreP(){
        return tvNombreP;
    }

    public TextView getTvPrecioC(){
        return tvPrecioC;
    }
    public TextView getTvPrecioW(){
        return tvPrecioW;
    }
    public TextView getTvPrecioS(){
        return tvPrecioS;
    }


    public ListadoHolder(@NonNull View itemView) {
        super(itemView);

        tvNombreP = itemView.findViewById(R.id.tvNombreP);
        tvPrecioC = itemView.findViewById(R.id.tvPrecioC);
        tvPrecioW = itemView.findViewById(R.id.tvPrecioW);
        tvPrecioS = itemView.findViewById(R.id.tvPrecioS);
    }
}
