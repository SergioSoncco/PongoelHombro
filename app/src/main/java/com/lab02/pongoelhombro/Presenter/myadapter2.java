package com.lab02.pongoelhombro.Presenter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lab02.pongoelhombro.Model.Noticia;
import com.lab02.pongoelhombro.Model.Vacuna;
import com.lab02.pongoelhombro.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class myadapter2 extends RecyclerView.Adapter<myadapter2.myviewholder> implements View.OnClickListener
{
   ArrayList<Vacuna> dataholder;
    private View.OnClickListener listener;


    public myadapter2(ArrayList<Vacuna> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.vacrecycler,parent,false);
        view.setOnClickListener(this);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position)
    {
      //holder.img.setImageResource(dataholder.get(position).getImage());

      holder.title.setText(dataholder.get(position).getVacLab());



    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    public void setOnclickListener(View.OnClickListener listener)
    {
        this.listener=listener;
    }
    @Override
    public void onClick(View view) {
        if(listener!=null)
        {
            listener.onClick(view);
        }
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView title,t1,t2,t3;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            title=itemView.findViewById(R.id.title);

        }
    }
}
