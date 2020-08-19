package com.example.recyclescroller;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CallLogAdapter extends RecyclerView.Adapter<CallLogAdapter.CallLogHolder> {

    private List<BluetoothCallLog> datas;

    private OnCallLogClickListener listener;

    public void setData(List<BluetoothCallLog> datas){
        this.datas = datas;
    }

    public void setListener(OnCallLogClickListener listener){
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onBindViewHolder(@NonNull CallLogHolder holder, int position) {
        BluetoothCallLog callLog = datas.get(position);

        String namenumber;
        if(TextUtils.isEmpty(callLog.name)){
            namenumber = callLog.number;
        }else{
            namenumber = callLog.name + "(" + callLog.number + ")";
        }
        holder.name_number_tv.setText(namenumber);
    }

    @NonNull
    @Override
    public CallLogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.bt_fragment_calllog_item,null);
        return new CallLogHolder(view);
    }


    class CallLogHolder extends RecyclerView.ViewHolder {

        public TextView name_number_tv,status_tv,time_tv;
        public ImageView typeView;
        public CallLogHolder(@NonNull View itemView) {
            super(itemView);
            name_number_tv = itemView.findViewById(R.id.name_number_tv);
            status_tv = itemView.findViewById(R.id.calllog_status_tv);
            time_tv = itemView.findViewById(R.id.calllog_time_tv);
            typeView = itemView.findViewById(R.id.calllog_type_iv);
        }
    }


    public interface OnCallLogClickListener {
        void onItemClick(BluetoothCallLog item);
    }

}
