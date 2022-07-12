package com.example.quan_ly_chung_cu.fragment_cudan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

public class Hoso_thanhvien extends Fragment {
    String host = "192.168.1.196";
    TextView canho, tendangnhap, gioitinh, ngaysinh, mail, sdt, diachi, tenthanhvien;
    Button suathongtin;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hoso_cudan_thanhvien,container, false);
        Bundle bundle_fragment = getArguments();
        String tenhodan = bundle_fragment.getString("tenhodan");
        int id_account = bundle_fragment.getInt("id_account");

        String url = "http://" + host + "/androidwebservice/getdata_user.php";

        canho = view.findViewById(R.id.id_thanhvien_canho);
        tendangnhap = view.findViewById(R.id.id_thanhvien_tendangnhap);
        gioitinh = view.findViewById(R.id.id_thanhvien_gioitinh);
        ngaysinh = view.findViewById(R.id.id_thanhvien_ngaysinh);
        mail = view.findViewById(R.id.id_thanhvien_mail);
        sdt = view.findViewById(R.id.id_thanhvien_sdt);
        diachi = view.findViewById(R.id.id_thanhvien_diachi);
        suathongtin = view.findViewById(R.id.id_bt_suathongtin);

        tenthanhvien = view.findViewById(R.id.id_ten_thanhvien);
        tenthanhvien.setText(tenhodan);

        themThongtin(url, tenhodan);
        suaThongtin(tenhodan, id_account);
        return view;
    }
    private void themThongtin(String url, String tenhodan){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String tenhodan_data = jsonObject.getString("Name");
                                int canho_data_int = jsonObject.getInt("Id_apartment");
                                String canho_data_string = String.valueOf(canho_data_int);
                                if (tenhodan_data.compareTo(tenhodan) == 0){
                                        //Toast.makeText(getActivity(), gioitinh_data, Toast.LENGTH_SHORT).show();
                                        canho.setText("Căn hộ: " + canho_data_string);
                                        gioitinh.setText("Giới tính: " + jsonObject.getString("Gender"));
                                        ngaysinh.setText("Ngày sinh: " + jsonObject.getString("Birthday"));
                                        mail.setText(jsonObject.getString("Email"));
                                        sdt.setText("SĐT: " + jsonObject.getString("Phone_number"));
                                        diachi.setText("Địa chỉ: " + jsonObject.getString("Address"));
                                        String url = "http://" + host + "/androidwebservice/getdata_cudan.php";
                                        themThongtin_tendangnhap(url, jsonObject.getInt("Id_user"));
                                        break;
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
    private void themThongtin_tendangnhap(String url, int id_user){

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(id_user - 1);
                            tendangnhap.setText("Tên đăng nhập: " + jsonObject.getString("Username"));
                            //Toast.makeText(getActivity(), jsonObject.getString("Username"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    private void suaThongtin(String tenhodan, int id_account){
        suathongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Hoso_thanhvien_sua();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame_cudan, fragment);
                transaction.addToBackStack("hoso_thanhvien_sua");
                transaction.commit();
                Bundle bundle_fragment = new Bundle();
                bundle_fragment.putString("tenhodan", tenhodan);
                bundle_fragment.putInt("id_account", id_account);
                fragment.setArguments(bundle_fragment);
            }
        });
    }
}
