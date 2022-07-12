package com.example.quan_ly_chung_cu.fragment_cudan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.quan_ly_chung_cu.R;

import java.util.List;

public class contenthossoApdater extends BaseAdapter {
    private Context context;
    private int layout;
    private List<contenthoso> contenthosoList;

    public contenthossoApdater(Context context, int layout, List<contenthoso> contenthosoList) {
        this.context = context;
        this.layout = layout;
        this.contenthosoList = contenthosoList;
    }

    @Override
    public int getCount() {
        return contenthosoList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);
        // anh xa
        Button button = view.findViewById(R.id.id_bt_thanhvien_hodan);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(viewGroup.getContext(), "abc", Toast.LENGTH_SHORT).show();
//            }
//        });
        // gan gia tri
        contenthoso cthoso = contenthosoList.get(i);
        button.setText(cthoso.getResident_name());

        return view;
    }
}
