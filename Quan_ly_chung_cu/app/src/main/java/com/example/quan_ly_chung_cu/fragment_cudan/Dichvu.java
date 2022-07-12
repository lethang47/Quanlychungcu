package com.example.quan_ly_chung_cu.fragment_cudan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.quan_ly_chung_cu.R;

import java.util.ArrayList;

public class Dichvu extends Fragment {
    GridView image_dichvu;
    ArrayList<contentdichvu> ctdichvuList;
    contentdichvuApdater contentdichvuapdater;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dichvu_cudan,container, false);
        image_dichvu = view.findViewById(R.id.grid_view_image_dichvu);
        ctdichvuList = new ArrayList<>();
        ctdichvuList.add(new contentdichvu("Bể bơi", R.drawable.beboi));
        ctdichvuList.add(new contentdichvu("Gym", R.drawable.gym));
        ctdichvuList.add(new contentdichvu("Để xe", R.drawable.parking));
        contentdichvuapdater = new contentdichvuApdater(getActivity(), R.layout.content_dichvu, ctdichvuList);
        image_dichvu.setAdapter(contentdichvuapdater);
        image_dichvu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getActivity(), (CharSequence) ctdichvuList.get(i).getName(), Toast.LENGTH_SHORT).show();
                switch (i){
                    case 0:

                        Fragment fragment = new Dichvu_beboi();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.content_frame_cudan, fragment);
                        transaction.addToBackStack("dichvu_beboi");
                        transaction.commit();
                        break;
                    case 1:
                        Toast.makeText(getActivity(), "gym", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getActivity(), "Dexe", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;

    }



}
