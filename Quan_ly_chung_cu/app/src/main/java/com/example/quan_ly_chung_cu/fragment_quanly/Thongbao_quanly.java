package com.example.quan_ly_chung_cu.fragment_quanly;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.quan_ly_chung_cu.R;

import java.util.HashMap;
import java.util.Map;

public class Thongbao_quanly extends Fragment {
    EditText noidung_thongbao;
    Button gui_thongbao;
    String host = "192.168.1.196";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_thongbao_quanly,container, false);

        noidung_thongbao = view.findViewById(R.id.id_edittext_thongbao);
        gui_thongbao = view.findViewById(R.id.id_button_gui_thongbao);
        String url = "http://" + host + "/androidwebservice/insert_thongbao.php";

        gui_thongbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String noidung = noidung_thongbao.getText().toString();
                if (noidung.isEmpty()) {
                    noidung_thongbao.setError("Vui lòng nhập nội dung");
                } else themThongbao(url);
            }

        });
        return view;
    }
    private void themThongbao(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")){
                            Toast.makeText(getActivity(), "Đã gửi thông báo", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Lỗi thêm!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("noidung", noidung_thongbao.getText().toString());
                noidung_thongbao.setText("");
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
