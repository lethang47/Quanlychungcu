package com.example.quan_ly_chung_cu.fragment_cudan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import com.example.quan_ly_chung_cu.R;

import java.util.ArrayList;

public class fragment_hoso_list extends ListFragment {
    ArrayList<contenthoso> contenthosoArrayList;
    contenthossoApdater contenthossoapdater;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contenthosoArrayList = new ArrayList<>();
        contenthosoArrayList.add(new contenthoso("Nguyễn Văn A"));
        contenthosoArrayList.add(new contenthoso("Nguyễn Văn B"));
        contenthosoArrayList.add(new contenthoso("Nguyễn Văn C"));
        contenthosoArrayList.add(new contenthoso("Nguyễn Văn D"));
        contenthosoArrayList.add(new contenthoso("Nguyễn Văn E"));
        contenthosoArrayList.add(new contenthoso("Nguyễn Văn F"));
        contenthossoapdater = new contenthossoApdater(getActivity(), R.layout.content_hoso_cudan, contenthosoArrayList);
        setListAdapter(contenthossoapdater);
        return inflater.inflate(R.layout.content_hoso_cudan, container, false);
    }
}
