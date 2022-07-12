package com.example.quan_ly_chung_cu.fragment_quanly;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.quan_ly_chung_cu.R;

public class Quanlycudan extends Fragment {
    Button bt_tang1, bt_tang2, bt_tang3;
//    ListView listView;
//    content_quanlycudan_Apdater content_quanlycudan_apdater;
//    String[] list_sonha = {"101","102","103","104","105","106","107"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quanlycudan_quanly,container, false);
//        listView = view.findViewById(R.id.id_listview_tang1);
//        content_quanlycudan_apdater = new content_quanlycudan_Apdater(getActivity(), R.layout.content_quanlycudan_quanly, list_sonha);
//        listView.setAdapter(content_quanlycudan_apdater);

        bt_tang1 = view.findViewById(R.id.id_quanly_tang1);
        bt_tang2 = view.findViewById(R.id.id_quanly_tang2);
        bt_tang3 = view.findViewById(R.id.id_quanly_tang3);

        bt_tang1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Quanlycudan_tang1();
                Bundle bundle_frag = new Bundle();
                bundle_frag.putInt("sotang", 1);
                fragment.setArguments(bundle_frag);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame_quanly, fragment);
                transaction.addToBackStack("quanlycudan_tang1");
                transaction.commit();

            }
        });
        bt_tang2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Quanlycudan_tang1();
                Bundle bundle_frag = new Bundle();
                bundle_frag.putInt("sotang", 2);
                fragment.setArguments(bundle_frag);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame_quanly, fragment);
                transaction.addToBackStack("quanlycudan_tang1");
                transaction.commit();

            }
        });
        bt_tang3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Quanlycudan_tang1();
                Bundle bundle_frag = new Bundle();
                bundle_frag.putInt("sotang", 3);
                fragment.setArguments(bundle_frag);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame_quanly, fragment);
                transaction.addToBackStack("quanlycudan_tang1");
                transaction.commit();

            }
        });
        return view;
    }
}
