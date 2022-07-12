package com.example.quan_ly_chung_cu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button dang_nhap_button;
    ConstraintLayout constraintLayout_dangnhap;
    EditText taikhoan, matkhau;
    RadioButton quanly, cudan;
    RadioGroup chose;
    TextView text_header_cudan;
    String host = "192.168.1.196";
    int id_account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        String url_cudan = "http://" + host + "/androidwebservice/getdata_cudan.php";
        String url_quanly = "http://" + host +  "/androidwebservice/getdata_quanly.php";
        getData_dangnhap_quanly(url_quanly);
        getData_dangnhap_cudan(url_cudan);
    }

    private void AnhXa() {
        dang_nhap_button = (Button) findViewById(R.id.button);
        constraintLayout_dangnhap = findViewById(R.id.main_dangnhap);
        taikhoan = findViewById(R.id.editTextTaiKhoan);
        matkhau = findViewById(R.id.editTextPassWord);
        chose = findViewById(R.id.radioGroupchose);
        text_header_cudan = findViewById(R.id.id_header_cudan);
    }

    private void getData_dangnhap_cudan(String url) {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        chose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                dang_nhap_button.setOnClickListener(new View.OnClickListener() {

                                    @Override
                                    public void onClick(View view) {

                                        switch (i) {
                                            case R.id.rButtoncudan: {
                                                boolean error_cudan = false;
                                                boolean check_cudan = false;
                                                for (int j = 0; j < response.length(); j++) {
                                                    try {
                                                        JSONObject jsonObject = response.getJSONObject(j);
                                                        try {
                                                            String matkhau_database = jsonObject.getString("Password");
                                                            String taikhau_database = jsonObject.getString("Username");
                                                            int id = jsonObject.getInt("Id");

                                                            String matkhau_nhap = matkhau.getText().toString();
                                                            String taikhoan_nhap = taikhoan.getText().toString();
                                                            if (matkhau_nhap.isEmpty()) {
                                                                matkhau.setError("Vui lòng nhập mật khẩu");
                                                                error_cudan = true;
                                                            }
                                                            if (taikhoan_nhap.isEmpty()) {
                                                                taikhoan.setError("Vui lòng nhập tài khoản");
                                                                error_cudan = true;
                                                            }
                                                            if ((matkhau_nhap.compareTo(matkhau_database) == 0) && (taikhoan_nhap.compareTo(taikhau_database) == 0) && (error_cudan == false)) {
                                                                id_account = id;
                                                                //Toast.makeText(MainActivity.this, String.valueOf(id_account), Toast.LENGTH_SHORT).show();
                                                                Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                                                Intent intent = new Intent(MainActivity.this, Cudan_dangnhap_main.class);
                                                                Bundle bundle = new Bundle();
                                                                bundle.putInt("id_account", id_account);
                                                                intent.putExtras(bundle);
                                                                startActivity(intent);
                                                                text_header_cudan.setText("");
                                                                check_cudan = true;
                                                                break;
                                                            }
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                                if (error_cudan == false && check_cudan == false) {
                                                    Toast.makeText(MainActivity.this, "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                                                }
                                                break;
                                            }
                                        }
                                    }
                                });
                            }
                        });
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    private void getData_dangnhap_quanly(String url) {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        chose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                dang_nhap_button.setOnClickListener(new View.OnClickListener() {

                                    @Override
                                    public void onClick(View view) {

                                        switch (i) {

                                            case R.id.rButtonquanly: {
                                                boolean error_quanly = false;
                                                boolean check_quanly = false;
                                                for (int j = 0; j < response.length(); j++) {
                                                    JSONObject jsonObject;
                                                    try {
                                                        jsonObject = response.getJSONObject(j);
                                                        try {
                                                            String matkhau_database = jsonObject.getString("Password");
                                                            String taikhau_database = jsonObject.getString("Username");
                                                            String matkhau_nhap = matkhau.getText().toString();
                                                            String taikhoan_nhap = taikhoan.getText().toString();
                                                            if (matkhau_nhap.isEmpty()) {
                                                                matkhau.setError("Vui lòng nhập mật khẩu");
                                                                error_quanly = true;
                                                            }
                                                            if (taikhoan_nhap.isEmpty()) {
                                                                taikhoan.setError("Vui lòng nhập tài khoản");
                                                                error_quanly = true;
                                                            }
                                                            if ((matkhau_nhap.compareTo(matkhau_database) == 0) && (taikhoan_nhap.compareTo(taikhau_database) == 0) && (error_quanly == false)) {
                                                                Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                                                Intent intent = new Intent(MainActivity.this, Quanly_dangnhap_main.class);
                                                                startActivity(intent);
                                                                check_quanly = true;
                                                                break;
                                                            }

                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                                if (error_quanly == false && check_quanly == false) {
                                                    Toast.makeText(MainActivity.this, "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                                                }
                                                break;
                                            }
                                        }
                                    }
                                });
                            }
                        });
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    @Override
    public void onBackPressed() {

        moveTaskToBack(true);
    }
    public int get_id_account(){

        return id_account;
    }
}