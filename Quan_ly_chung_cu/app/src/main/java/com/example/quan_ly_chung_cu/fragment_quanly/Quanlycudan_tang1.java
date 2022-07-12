package com.example.quan_ly_chung_cu.fragment_quanly;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.quan_ly_chung_cu.R;

public class Quanlycudan_tang1 extends Fragment {
    ListView listView;
    content_quanlycudan_Apdater content_quanlycudan_apdater;
    String[] list_sonha_tang1 = {"101","102","103","104","105","106","107"};
    String[] list_sonha_tang2 = {"201","202","203","204","205","206","207"};
    String[] list_sonha_tang3 = {"301","302","303","304","305","306","307"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quanlycudan_quanly_tang1,container, false);
        listView = view.findViewById(R.id.id_listview_tang1);

        Bundle bundle_fragment = getArguments();
        int sotang = bundle_fragment.getInt("sotang");
        //Toast.makeText(getContext(), String.valueOf(sotang), Toast.LENGTH_SHORT).show();
        switch (sotang){
            case 1:
                content_quanlycudan_apdater = new content_quanlycudan_Apdater(getActivity(), R.layout.content_quanlycudan_quanly, list_sonha_tang1);
                listView.setAdapter(content_quanlycudan_apdater);

                break;
            case 2:
                content_quanlycudan_apdater = new content_quanlycudan_Apdater(getActivity(), R.layout.content_quanlycudan_quanly, list_sonha_tang2);
                listView.setAdapter(content_quanlycudan_apdater);
                break;
            case 3:
                content_quanlycudan_apdater = new content_quanlycudan_Apdater(getActivity(), R.layout.content_quanlycudan_quanly, list_sonha_tang3);
                listView.setAdapter(content_quanlycudan_apdater);
                break;
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fragment fragment = new Quanlycudan_tang1_1();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame_quanly, fragment);
                transaction.addToBackStack("quanlycudan_tang1_1");
                transaction.commit();
                TextView textView = view.findViewById(R.id.id_quanly_tang1_sonha);
                String sonha= textView.getText().toString();
                //Toast.makeText(getActivity(), itemValue, Toast.LENGTH_SHORT).show();

                Bundle bundle_frag = new Bundle();
                bundle_frag.putString("sonha", sonha);
                fragment.setArguments(bundle_frag);
            }
        });

        return view;
    }
}
