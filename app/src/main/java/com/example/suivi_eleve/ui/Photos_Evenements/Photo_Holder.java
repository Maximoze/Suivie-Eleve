package com.example.suivi_eleve.ui.Photos_Evenements;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suivi_eleve.R;
import com.squareup.picasso.Picasso;

public class Photo_Holder extends RecyclerView.ViewHolder {

    ImageView imageUrl;
    TextView description ;

    public Photo_Holder(@NonNull View itemView) {
        super(itemView);

        this.description = itemView.findViewById(R.id.libelecontent);
        this.imageUrl = itemView.findViewById(R.id.imageActivite);
}
    public void setImagage(FragmentActivity ctx, String image){

        Picasso.with(ctx).load(image).into(imageUrl);
    }
}
