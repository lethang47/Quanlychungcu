package com.example.quan_ly_chung_cu.fragment_cudan;

import static java.lang.Integer.compare;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

public class Dichvu_beboi extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    ListView listView;
    ArrayList<content_dichvu_beboi> content_dichvu_beboiArrayList;
    contentdichvu_beboi_Apdater contentdichvuBeboiApdater;
    Button button_dangky, button_dong_hienthi;
    int id_account;
    int[] id_dichvu_array = new int[2000];
    TextView tv_giatien, tv_ngay_dangky, tv_thoihan_dangky, tv_giamgia;
    int index = 0;
    Dialog dialog_off;
    String host = "192.168.1.196";
    SwipeRefreshLayout swipeRefreshLayout_list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dichvu_beboi_cudan,container, false);

        Bundle bundle = getActivity().getIntent().getExtras();
        id_account = bundle.getInt("id_account");

        listView = view.findViewById(R.id.id_listview_register);
        button_dangky = view.findViewById(R.id.id_button_dk);
        swipeRefreshLayout_list = view.findViewById(R.id.id_swipeRefreshLayout);
        swipeRefreshLayout_list.setOnRefreshListener(this);

        content_dichvu_beboiArrayList = new ArrayList<>();

        String url = "http://" + host + "/androidwebservice/getdata_dichvu.php";
        Themdichvu(url);

        contentdichvuBeboiApdater = new contentdichvu_beboi_Apdater(getActivity(), R.layout.content_dichvu_beboi, content_dichvu_beboiArrayList);
        listView.setAdapter(contentdichvuBeboiApdater);

        // Xử lý listview hiển thị thông tin

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                display_thongtin(i, url);
                button_dong_hienthi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog_off.dismiss();
                    }
                });
            }
        });
        button_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Dichvu_beboi_dangky();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame_cudan, fragment);
                transaction.addToBackStack("dichvu_beboi_dangky");
                transaction.commit();
            }
        });
        //refresh_data();
        return view;
    }
    private void Themdichvu(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                int id = jsonObject.getInt("Id");
                                String name = jsonObject.getString("Name");
                                String registration_date = jsonObject.getString("Registration_date");
                                int id_user = jsonObject.getInt("Id_user");
                                if ((name.compareTo("Bể bơi") == 0) && compare(id_user, id_account) == 0){
                                    content_dichvu_beboiArrayList.add(new content_dichvu_beboi(registration_date, R.drawable.greenfile));
                                    //Toast.makeText(getActivity(), String.valueOf(id), Toast.LENGTH_SHORT).show();
                                    id_dichvu_array[index] = id;
                                    index++;
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        contentdichvuBeboiApdater.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    private void display_thongtin(int index, String url){
        Dialog dialog = new Dialog(getActivity());
        int id_dichvu = id_dichvu_array[index];
        //Toast.makeText(getActivity(), String.valueOf(id_dichvu), Toast.LENGTH_SHORT).show();
        dialog.setContentView(R.layout.dialog_hienthi_thongtin_dichvu);

        tv_giatien = dialog.findViewById(R.id.id_hienthi_giatiendichvu);
        tv_ngay_dangky = dialog.findViewById(R.id.id_hienthi_ngaydangkydv);
        tv_thoihan_dangky = dialog.findViewById(R.id.id_hienthi_thoigiandangkydv);
        tv_giamgia = dialog.findViewById(R.id.id_hienthi_giamgiadichvu);
        button_dong_hienthi = dialog.findViewById(R.id.id_button_dong_hienthi);

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                int id = jsonObject.getInt("Id");
                                String giatien = jsonObject.getString("Price_by_month");
                                String ngay_dangky = jsonObject.getString("Registration_date");
                                int thoihan_dangky = jsonObject.getInt("Term");
                                int giamgia = jsonObject.getInt("Discount");
                                if (compare(id, id_dichvu) == 0){
                                    tv_giatien.setText("Giá tiền: " + giatien);
                                    tv_ngay_dangky.setText("Ngày đăng ký: " + ngay_dangky);
                                    tv_thoihan_dangky.setText("Thời gian: " + String.valueOf(thoihan_dangky));
                                    tv_giamgia.setText("Giảm giá: " + String.valueOf(giamgia));
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
        dialog_off = dialog;
        dialog.show();
    }
    private void refresh_data(){
        final Handler handler = new Handler();
        Runnable refresh = new Runnable() {
            @Override
            public void run() {

                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(refresh, 1000);
    }

    @Override
    public void onRefresh() {
        content_dichvu_beboiArrayList = new ArrayList<>();

        String url = "http://" + host + "/androidwebservice/getdata_dichvu.php";
        Themdichvu(url);
        contentdichvuBeboiApdater = new contentdichvu_beboi_Apdater(getActivity(),R.layout.content_dichvu_beboi, content_dichvu_beboiArrayList);
        listView.setAdapter(contentdichvuBeboiApdater);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout_list.setRefreshing(false);
            }
        }, 4000);
    }
}
