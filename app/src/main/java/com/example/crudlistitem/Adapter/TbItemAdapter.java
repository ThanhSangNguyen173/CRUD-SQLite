package com.example.crudlistitem.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.crudlistitem.DTO.TBitem;
import com.example.crudlistitem.R;

import java.util.ArrayList;

public class TbItemAdapter extends BaseAdapter {
    final ArrayList<TBitem> tBitemArrayList;

    public TbItemAdapter(ArrayList<TBitem> tBitemArrayList) {
        this.tBitemArrayList = tBitemArrayList;
    }

    @Override
    public int getCount() {
        return tBitemArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return tBitemArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return tBitemArrayList.get(i).getId_item();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView;
        if(view==null){
            itemView = View.inflate(viewGroup.getContext(), R.layout.custom_list_item,null);
        }else itemView = view;
        TBitem tBitem = (TBitem) tBitemArrayList.get(i);
        TextView tv_id_item =  (TextView) itemView.findViewById(R.id.tv_id_item);
        TextView tv_ten_item =  (TextView) itemView.findViewById(R.id.tv_ten_item);
        TextView tv_season=  (TextView) itemView.findViewById(R.id.tv_season);

        tv_id_item.setText(tBitem.getId_item()+"");
        tv_ten_item.setText(tBitem.getTen_item());
        tv_season.setText(tBitem.getSeason());

        return itemView;
    }
}
