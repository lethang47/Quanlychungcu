package com.example.quan_ly_chung_cu.fragment_quanly;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.quan_ly_chung_cu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Phanhoi_quanly extends Fragment {
    ListView list_phanhoi;
    ArrayList<content_quanlycudan_phanhoi> content_quanlycudan_phanhoiArrayList;
    content_quanlycudan_phanhoiApdater contentQuanlycudanPhanhoiApdater;
    String host = "192.168.1.196";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phanhoi_quanly,container, false);

        list_phanhoi = view.findViewById(R.id.id_lv_phanhoi_quanly);
        content_quanlycudan_phanhoiArrayList = new ArrayList<>();
        String url = "http://" + host + "/androidwebservice/getdata_phanhoi.php";
        themPhanhoi(url);
        contentQuanlycudanPhanhoiApdater = new content_quanlycudan_phanhoiApdater(getContext(), R.layout.content_quanly_quanlycudan_phanhoi, content_quanlycudan_phanhoiArrayList);
        list_phanhoi.setAdapter(contentQuanlycudanPhanhoiApdater);
        return view;
    }
    private void themPhanhoi(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null
                , new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int sonha = jsonObject.getInt("Id_apartment");
                        String noidung = jsonObject.getString("Content");
                        String ngay = jsonObject.getString("Date");
                        content_quanlycudan_phanhoiArrayList.add(new content_quanlycudan_phanhoi(noidung, String.valueOf(sonha), ngay));
                        //Toast.makeText(getActivity(), String.valueOf(sonha_data), Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                contentQuanlycudanPhanhoiApdater.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}
