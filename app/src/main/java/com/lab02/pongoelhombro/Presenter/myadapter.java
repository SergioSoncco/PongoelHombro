package com.lab02.pongoelhombro.Presenter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lab02.pongoelhombro.Model.Noticia;
import com.lab02.pongoelhombro.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder> implements View.OnClickListener
{
    ArrayList<Noticia> dataholder;
    private View.OnClickListener listener;


    public myadapter(ArrayList<Noticia> dataholder) {

        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_design,parent,false);
        view.setOnClickListener(this);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position)
    {
      //holder.img.setImageResource(dataholder.get(position).getImage());
      holder.header.setText(dataholder.get(position).getHeader());
      holder.desc.setText(dataholder.get(position).getDesc());
      Picasso.get().load(dataholder.get(position).getImage()).into(holder.img);



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
        ImageView img;
        TextView header,desc;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            img=itemView.findViewById(R.id.img1);
            header=itemView.findViewById(R.id.t1);
            desc=itemView.findViewById(R.id.t2);
        }
    }
}
