package com.example.quan_ly_chung_cu.fragment_cudan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.quan_ly_chung_cu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Hoso_thanhvien_sua extends Fragment {
    EditText etTen, etGioitinh, etNgaysinh, etEmail, etSdt, etDiachi;
    TextView tensua;
    Button capnhat;
    String host = "192.168.1.196";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hoso_cudan_thanhvien_sua,container, false);
        Bundle bundle_fragment = getArguments();
        String tenhodan = bundle_fragment.getString("tenhodan");


        String url = "http://" + host + "/androidwebservice/getdata_user.php";

        etTen = view.findViewById(R.id.id_et_suaten);
        etGioitinh = view.findViewById(R.id.id_et_suagioitinh);
        etNgaysinh = view.findViewById(R.id.id_et_suangaysinh);
        etEmail = view.findViewById(R.id.id_et_suamail);
        etSdt = view.findViewById(R.id.id_et_suasdt);
        etDiachi = view.findViewById(R.id.id_et_suadiachi);
        capnhat = view.findViewById(R.id.id_bt_capnhatthongtin);
        tensua = view.findViewById(R.id.id_ten_thanhvien_sua);

        tensua.setText(tenhodan);
        setThongtin(url, tenhodan);
        capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getIdaccount(url, tenhodan);
            }
        });
        return view;
    }
    private void getIdaccount(String url, String tenhodan){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String tenhodan_data = jsonObject.getString("Name");
                                if (tenhodan_data.compareTo(tenhodan) == 0){
                                    //Toast.makeText(getActivity(), gioitinh_data, Toast.LENGTH_SHORT).show();
                                    int id_account = jsonObject.getInt("Id_user");
                                    String url = "http://" + host + "/androidwebservice/update_user.php";
                                    capnhatThongtin(url, id_account);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    private void capnhatThongtin(String url, int id_account){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("success")){
                            Toast.makeText(getActivity(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        } else Toast.makeText(getActivity(), "Cập nhật bị lỗi", Toast.LENGTH_SHORT).show();
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
                params.put("id_user", String.valueOf(id_account));
                if (etTen.getText().toString() != "") params.put("name", etTen.getText().toString());
                if (etEmail.getText().toString() != "") params.put("email", etEmail.getText().toString());
                if (etSdt.getText().toString() != "") params.put("sdt", etSdt.getText().toString());
                if (etGioitinh.getText().toString() != "") params.put("gender", etGioitinh.getText().toString());
                if (etDiachi.getText().toString() != "") params.put("address", etDiachi.getText().toString());
                if (etNgaysinh.getText().toString() != "") params.put("birthday", etNgaysinh.getText().toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void setThongtin(String url, String tenhodan){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String tenhodan_data = jsonObject.getString("Name");
                                if (tenhodan_data.compareTo(tenhodan) == 0){
                                    etTen.setText(tenhodan);
                                    etGioitinh.setText(jsonObject.getString("Gender"));
                                    etDiachi.setText(jsonObject.getString("Address"));
                                    etEmail.setText(jsonObject.getString("Email"));
                                    etNgaysinh.setText(jsonObject.getString("Birthday"));
                                    etSdt.setText(jsonObject.getString("Phone_number"));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}
