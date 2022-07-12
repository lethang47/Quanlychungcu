package com.example.quan_ly_chung_cu.fragment_quanly;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quan_ly_chung_cu.R;

import java.util.ArrayList;

public class Quanlycudan_tang1_1_phanhoi extends Fragment {
    ListView listView_phanhoi;
    ArrayList<content_quanlycudan_phanhoi> content_quanlycudan_phanhoiArrayList;
    content_quanlycudan_phanhoiApdater contentQuanlycudanPhanhoiApdater;
    String host = "192.168.0.101";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quanlycudan_quanly_tang1_1_phanhoi,container, false);
//        listView_phanhoi = view.findViewById(R.id.id_lv_phanhoi);
//        Bundle bundle_fragment = getArguments();
//        String sonha = bundle_fragment.getString("sonha");
//        Toast.makeText(getActivity(), sonha, Toast.LENGTH_SHORT).show();
//        content_quanlycudan_phanhoiArrayList = new ArrayList<>();
//        content_quanlycudan_phanhoiArrayList.add(new content_quanlycudan_phanhoi("Tôi muốn phản hồi về nhà A, vì nhà A hơi ồn, rất mất chật tự Tôi muốn phản hồi về nhà A, vì nhà A hơi ồn, rất mất chật tự","7/7/2022"));
//        content_quanlycudan_phanhoiArrayList.add(new content_quanlycudan_phanhoi("Tùng","8/7/2022"));
//        content_quanlycudan_phanhoiArrayList.add(new content_quanlycudan_phanhoi("Quỳnh","9/7/2022"));
//        String url = "http://" + host + "/androidwebservice/getdata_phanhoi.php";
//        themPhanhoi(url, sonha);
//        contentQuanlycudanPhanhoiApdater = new content_quanlycudan_phanhoiApdater(getActivity(), R.layout.content_quanly_quanlycudan_phanhoi, content_quanlycudan_phanhoiArrayList);
//        listView_phanhoi.setAdapter(contentQuanlycudanPhanhoiApdater);
        return view;
    }
//    private void themPhanhoi(String url, String sonha){
//        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null
//                , new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                for (int i = 0; i < response.length(); i++){
//                    try {
//                        JSONObject jsonObject = response.getJSONObject(i);
//                        int sonha_data = jsonObject.getInt("Id_apartment");
//                        String noidung = jsonObject.getString("Content");
//                        String ngay = jsonObject.getString("Date");
//                        //Toast.makeText(getActivity(), String.valueOf(sonha_data), Toast.LENGTH_SHORT).show();
//                        if (sonha.compareTo(String.valueOf(sonha_data)) == 0){
//                            content_quanlycudan_phanhoiArrayList.add(new content_quanlycudan_phanhoi(noidung, ngay));
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//                contentQuanlycudanPhanhoiApdater.notifyDataSetChanged();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        requestQueue.add(jsonArrayRequest);
//    }
}
