package com.example.quan_ly_chung_cu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.quan_ly_chung_cu.fragment_cudan.Dichvu;
import com.example.quan_ly_chung_cu.fragment_cudan.Gopy;
import com.example.quan_ly_chung_cu.fragment_cudan.Hoso;
import com.example.quan_ly_chung_cu.fragment_cudan.Thanhtoan;
import com.example.quan_ly_chung_cu.fragment_cudan.Trangchu;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Cudan_dangnhap_main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerlayout;
    private static final int Fragment_trangchu = 0;
    private static final int Fragment_hoso = 1;
    private static final int Fragment_dichvu = 2;
    private static final int Fragment_thanhtoan = 3;
    private static final int Fragment_gopy = 4;
    private  int Currentfragment = Fragment_trangchu;
    TextView ten_cudan_header;
    String host = "192.168.1.196";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cudan_dangnhap_main);


        Toolbar toolbar = findViewById(R.id.toolbar_cudan);
        setSupportActionBar(toolbar);

        drawerlayout = findViewById(R.id.drawer_layout_cudan);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerlayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerlayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view_cudan);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new Trangchu());
        navigationView.getMenu().findItem(R.id.id_trang_chu).setChecked(true);
        toolbar.setTitle("Trang chủ");
        ten_cudan_header = navigationView.getHeaderView(0).findViewById(R.id.id_header_cudan);
        Bundle bundle = getIntent().getExtras();
        int id_account = bundle.getInt("id_account");
        set_name_header(id_account);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Toolbar toolbar = findViewById(R.id.toolbar_cudan);
        int id = item.getItemId();
        switch (id){
            case R.id.id_trang_chu:
                if (Currentfragment != Fragment_trangchu){
                    replaceFragment(new Trangchu());
                    Currentfragment = Fragment_trangchu;
                    toolbar.setTitle("Trang chủ");
                }
                break;
            case R.id.id_ho_so:
                if (Currentfragment != Fragment_hoso){
                    replaceFragment(new Hoso());
                    Currentfragment = Fragment_hoso;
                    toolbar.setTitle("Hồ sơ");
                }
                break;
            case R.id.id_dich_vu:
                if (Currentfragment != Fragment_dichvu){
                    replaceFragment(new Dichvu());
                    Currentfragment = Fragment_dichvu;

                    toolbar.setTitle("Dịch vụ");
                }
                break;
            case R.id.id_thanh_toan:
                if (Currentfragment != Fragment_thanhtoan){
                    replaceFragment(new Thanhtoan());
                    Currentfragment = Fragment_thanhtoan;
                    toolbar.setTitle("Thanh toán");
                }
                break;
            case R.id.id_gop_y:
                if (Currentfragment != Fragment_gopy){
                    replaceFragment(new Gopy());
                    Currentfragment = Fragment_gopy;
                    toolbar.setTitle("Góp ý");
                }
                break;
            case R.id.id_dang_xuat:
                Intent dang_nhap_cudan = new Intent(Cudan_dangnhap_main.this, MainActivity.class);
                startActivity(dang_nhap_cudan);
        }
        drawerlayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void set_name_header(int id_account){
        String url = "http://" + host + "/androidwebservice/getdata_user.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(id_account - 1);
                            String name = jsonObject.getString("Name");
                            ten_cudan_header.setText(name);
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

    @Override
    public void onBackPressed() {
        if (drawerlayout.isDrawerOpen(GravityCompat.START)){
            drawerlayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        if (getFragmentManager().getBackStackEntryCount() > -1){
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame_cudan, fragment);
        fragmentTransaction.commit();

    }
}