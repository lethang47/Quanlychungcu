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

public class Quanlycudan_tang1_1 extends Fragment {
    ListView list_thanhvien;
    ArrayList<content_quanlycudan_thanhvien> content_quanlycudan_thanhvienArrayList;
    content_quanlycudan_thanhvienApdater contentQuanlycudanThanhvienApdater;
    String host = "192.168.1.196";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quanlycudan_quanly_tang1_1,container, false);

        Bundle bundle_fragment = getArguments();
        String sonha = bundle_fragment.getString("sonha");
        list_thanhvien = view.findViewById(R.id.id_lv_quanlycudan_thanhvien);
        content_quanlycudan_thanhvienArrayList = new ArrayList<>();
        String url = "http://" + host + "/androidwebservice/getdata_user.php";
        themThanhvien(url,sonha);

        contentQuanlycudanThanhvienApdater = new content_quanlycudan_thanhvienApdater(getContext(), R.layout.content_quanlycudan_quanly_thongtin, content_quanlycudan_thanhvienArrayList);
        list_thanhvien.setAdapter(contentQuanlycudanThanhvienApdater);

        return view;
    }
    private void themThanhvien(String url, String sonha){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                int sonha_data = jsonObject.getInt("Id_apartment");
                                String name = jsonObject.getString("Name");
                                if (sonha.compareTo(String.valueOf(sonha_data)) == 0){
                                    content_quanlycudan_thanhvienArrayList.add(new content_quanlycudan_thanhvien(name));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        contentQuanlycudanThanhvienApdater.notifyDataSetChanged();


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}
