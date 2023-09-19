package com.example.businesscardproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private Context maincon;
    LayoutInflater Inflator;
    ArrayList<Person> listdata;
    int layout;

    public CustomAdapter(Context context, int layout, ArrayList<Person> listdata) {
        this.maincon = context;
        Inflator = (LayoutInflater) maincon.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout = layout;
        this.listdata = listdata;
    }
    @Override
    public int getCount() {
        return listdata.size();
    }

    @Override
    public Object getItem(int position) {
        return listdata.get(position).name;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;

        if(convertView == null){
            convertView = Inflator.inflate(layout, parent, false);
        }
        ImageView img = convertView.findViewById(R.id.img1);
        img.setImageResource(listdata.get(position).icon);

        TextView txt = convertView.findViewById(R.id.txt1);
        txt.setText(listdata.get(position).name);

        TextView txt2 = convertView.findViewById(R.id.txt2);
        txt2.setText(listdata.get(position).phone);

        TextView txt3 = convertView.findViewById(R.id.txt3);
        txt3.setText(listdata.get(position).email);

        Button btn = convertView.findViewById(R.id.btn1);
        btn.setOnClickListener(v -> {
            String str = listdata.get(pos).name + "에게 전화를 겁니다.";
            Toast.makeText(maincon, str, Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(phone))
        });

        return convertView;
    }
}
