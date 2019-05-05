package com.srsys.ssadmin.presentation.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.srsys.ssadmin.R;
import com.srsys.ssadmin.logic.AccessLoggedIn;
import com.srsys.ssadmin.objects.Message;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    private List<Message> messages;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView message_textview, date_textview;
        public LinearLayout linearLayout_message;

        public MyViewHolder(View view)
        {
            super(view);
            message_textview= view.findViewById(R.id.message_textview_message);
            date_textview = view.findViewById(R.id.message_textview_time);
            linearLayout_message = view.findViewById(R.id.message_linear_layout);
        }
    }


    public MessageAdapter(List<Message> messages)
    {
        this.messages = messages;
    }

    @Override
    public MessageAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inflate_message, parent, false);
        return new MessageAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MessageAdapter.MyViewHolder holder, int position)
    {
        Message message = messages.get(position);
        holder.message_textview.setText(message.getMessage());
        holder.date_textview.setText(message.getTimeStamp());

        AccessLoggedIn accessLogedIn  = AccessLoggedIn.getInstance();

        if(message.getMessgeId().equals(accessLogedIn.getUid()))
        {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)
                    holder.linearLayout_message.getLayoutParams();
            layoutParams.setMargins(50, 0, 0, 0);
            holder.linearLayout_message.setLayoutParams(layoutParams);
        }
    }


    @Override
    public int getItemCount()
    {
        return messages.size();
    }
}