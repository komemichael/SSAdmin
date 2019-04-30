package com.srsys.ssadmin.presentation.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.srsys.ssadmin.R;
import com.srsys.ssadmin.objects.Bills;

import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.MyViewHolder> {

    private List<Bills> bills;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView billname, billdate;

        public MyViewHolder(View view) {
            super(view);
            billname = view.findViewById(R.id.adapter_billname);
            billdate = view.findViewById(R.id.adapter_billdate);
        }
    }


    public BillAdapter(List<Bills> bills) {
        this.bills = bills;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Bills bill = bills.get(position);
        holder.billdate.setText(bill.getDate().toString());
        holder.billname.setText(bill.getNamebill());
    }


    @Override
    public int getItemCount() {
        return bills.size();
    }
}
