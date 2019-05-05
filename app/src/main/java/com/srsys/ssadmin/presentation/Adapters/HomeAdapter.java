package com.srsys.ssadmin.presentation.Adapters;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.srsys.ssadmin.R;
import com.srsys.ssadmin.presentation.AccountActivity;
import com.srsys.ssadmin.presentation.ListActivity;
import com.srsys.ssadmin.presentation.MainActivity;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {


    public class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView logo;
        public TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);
            logo = itemView.findViewById(R.id.main_rv_image);
            name = itemView.findViewById(R.id.main_rv_text);
        }
    }

    List<String> name;
    List<Bitmap> logo;

    public HomeAdapter(List<String> name, List<Bitmap> logo) {
        this.name = name;
        this.logo = logo;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.main_rv_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.logo.setImageBitmap(logo.get(position));
        holder.logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position)
                {
                    case 0:
                        v.getContext().startActivity(new Intent(v.getContext(), MainActivity.class));
                        break;
                    case 1:
                        v.getContext().startActivity(new Intent(v.getContext(), AccountActivity.class));
                        break;
                    case 2:
//                        v.getContext().startActivity(new Intent(v.getContext(), TabActivity.class));
                        break;
                    case 3:
//                        v.getContext().startActivity(new Intent(v.getContext(), Main2Activity.class));
                        break;
                    case 4:
//                        v.getContext().startActivity(new Intent(v.getContext(), ListActivity.class));
                        break;
                    case 5:
//                        v.getContext().startActivity(new Intent(v.getContext(), ListActivity.class));
                        break;
                    case 6:
                        v.getContext().startActivity(new Intent(v.getContext(), ListActivity.class));
                        break;
                }
            }
        });
        holder.name.setText(name.get(position));
    }

    @Override
    public int getItemCount() {
        return name.size();
    }
}
