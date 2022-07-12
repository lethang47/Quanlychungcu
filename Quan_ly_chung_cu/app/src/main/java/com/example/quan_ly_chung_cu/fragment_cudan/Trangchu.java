package com.example.quan_ly_chung_cu.fragment_cudan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

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

public class Trangchu extends Fragment {
    ListView list_thongbao;
    ArrayList<content_thongbao> content_thongbaoArrayList;
    content_thongbaoApdater contentThongbaoApdater;
    TextView khongcothongbao;
    String host = "192.168.1.196";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trangchu_cudan,container, false);
        list_thongbao = view.findViewById(R.id.id_list_thongbao);
        khongcothongbao = view.findViewById(R.id.id_tv_hienthikhongthongbao);
        content_thongbaoArrayList = new ArrayList<>();
        String url = "http://" + host +"/androidwebservice/getdata_thongbao.php";
        themThongbao(url);
        contentThongbaoApdater = new content_thongbaoApdater(getActivity(), R.layout.content_thongbao_cudan, content_thongbaoArrayList);
        list_thongbao.setAdapter(contentThongbaoApdater);
        return view;
    }
    private void themThongbao(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null
                , new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response.length() == 0){
                    khongcothongbao.setText("Hiện tại không có thông báo nào");
                } else
                for (int i = 0; i < response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String noidung = jsonObject.getString("Content");
                        String ngay = jsonObject.getString("Date");
                        content_thongbaoArrayList.add(new content_thongbao(noidung, ngay));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                contentThongbaoApdater.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}
