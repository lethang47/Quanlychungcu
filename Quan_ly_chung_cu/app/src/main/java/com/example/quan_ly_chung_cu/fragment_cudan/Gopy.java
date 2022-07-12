package com.example.quan_ly_chung_cu.fragment_cudan;

import static java.lang.Integer.compare;

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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.quan_ly_chung_cu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Gopy extends Fragment {
    EditText et_noidung;
    Button bt_gui;
    int id_account;
    int id_apartment;
    String host = "192.168.1.196";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gopy_cudan,container, false);

        et_noidung = view.findViewById(R.id.id_edittext_gopy);
        bt_gui = view.findViewById(R.id.id_button_gui_gopy);

        Bundle bundle = getActivity().getIntent().getExtras();
        id_account = bundle.getInt("id_account");

        //Toast.makeText(getActivity(), String.valueOf(getid_apartment()), Toast.LENGTH_SHORT).show();

        String url = "http://" + host + "/androidwebservice/getdata_user.php";
        bt_gui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String noidung = et_noidung.getText().toString();
                if (noidung.isEmpty()) {
                    et_noidung.setError("Vui lòng nhập nội dung");
                } else GetIdApartment(url, id_account);
            }
        });

        return view;
    }
    private void GetIdApartment(String url, int id_account){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                int id_account1 = jsonObject.getInt("Id_user");
                                if (compare(id_account1,id_account)==0){
                                    int id_apartment1 = jsonObject.getInt("Id_apartment");
                                    //Toast.makeText(getActivity(), String.valueOf(id_apartment1), Toast.LENGTH_SHORT).show();
                                    String url = "http://" + host + "/androidwebservice/insert_gopy.php";
                                    Themgopy(url, id_apartment1);
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
    private void Themgopy(String url, int id_apartment){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")){
                            Toast.makeText(getActivity(), "Đã gửi góp ý", Toast.LENGTH_SHORT).show();
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
                params.put("noidung", et_noidung.getText().toString());
                params.put("id_apartment", String.valueOf(id_apartment));
                et_noidung.setText("");
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
