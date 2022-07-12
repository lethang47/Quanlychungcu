package com.example.quan_ly_chung_cu.fragment_cudan;

import android.app.Dialog;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.quan_ly_chung_cu.R;

import java.util.HashMap;
import java.util.Map;

public class Dichvu_beboi_dangky extends Fragment {
    Button button_thanhtoan, button_xacnhan_dangky;
    TextView tv_ten_dichvu, tv_giatien_thanhtoan, tv_giatien_beboi, tv_discount, tv_giatien_xacnhan;
    EditText et_sothang_dangky_beboi;
    int id_account;
    int giatien_thanhtoan;
    Dialog dialog_off;
    String host = "192.168.1.196";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dichvu_beboi_dangky, container, false);

        Bundle bundle = getActivity().getIntent().getExtras();
        id_account = bundle.getInt("id_account");

        button_thanhtoan = view.findViewById(R.id.id_button_thanhtoan);
        tv_ten_dichvu = view.findViewById(R.id.id_xacnhan_thanhtoan_tendichvu);
        tv_giatien_thanhtoan = view.findViewById(R.id.id_xacnhan_thanhtoan_giatien);
        tv_giatien_beboi = view.findViewById(R.id.id_giatien_beboi);
        tv_discount = view.findViewById(R.id.id_discount);
        et_sothang_dangky_beboi = view.findViewById(R.id.id_edittext_sothang_beboi);


        String giatien_beboi = (String) tv_giatien_beboi.getText();
        int giatien_beboi_int = Integer.valueOf(giatien_beboi);
        String discount = (String) tv_discount.getText();
        int discount_int = Integer.valueOf(discount);


        button_thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sothang_dangky_beboi = et_sothang_dangky_beboi.getText().toString();
                if (sothang_dangky_beboi.isEmpty()) {
                    et_sothang_dangky_beboi.setError("Vui lòng số lớn hơn 1");
                } else {

                    int sothang_dangky_beboi_int = Integer.valueOf(sothang_dangky_beboi);
                    float giatien_beboi_final = (giatien_beboi_int * sothang_dangky_beboi_int) - ((float) discount_int / (float) 100) * (giatien_beboi_int * sothang_dangky_beboi_int);
                    int giatien_beboi_final_round = Math.round(giatien_beboi_final);
                    giatien_thanhtoan = giatien_beboi_final_round;
                    Dialog_thanhtoan(giatien_beboi_final_round);
                    button_xacnhan_dangky.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String url = "http://" + host + "/androidwebservice/insert_dichvu.php";
                            Themdichvu(url);
                            dialog_off.dismiss();
                        }
                    });
                }

            }
        });
        return view;
    }

    public void Dialog_thanhtoan(int giatien) {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_xacnhan_thanhtoan_dichvu);
        tv_giatien_xacnhan = dialog.findViewById(R.id.id_xacnhan_thanhtoan_giatien);
        button_xacnhan_dangky = dialog.findViewById(R.id.id_button_xacnhan_dangky);
        tv_giatien_xacnhan.setText("Giá tiền: " + String.valueOf(giatien));
        dialog_off = dialog;
        dialog.show();

    }

    private void Themdichvu(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")){
                            Toast.makeText(getActivity(), "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Lỗi thêm!", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Xảy ra lỗi!", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("tendichvu", "Bể bơi");
                params.put("giadichvu", tv_giatien_beboi.getText().toString());
                params.put("thoihan",et_sothang_dangky_beboi.getText().toString());
                params.put("giamgia", tv_discount.getText().toString());
                params.put("id_nguoidung", String.valueOf(id_account));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
