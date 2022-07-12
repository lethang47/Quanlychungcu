package com.example.quan_ly_chung_cu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.quan_ly_chung_cu.fragment_quanly.Dichvu_quanly;
import com.example.quan_ly_chung_cu.fragment_quanly.Phanhoi_quanly;
import com.example.quan_ly_chung_cu.fragment_quanly.Quanlycudan;
import com.example.quan_ly_chung_cu.fragment_quanly.Thongbao_quanly;
import com.example.quan_ly_chung_cu.fragment_quanly.Trangchu_quanly;
import com.google.android.material.navigation.NavigationView;

public class Quanly_dangnhap_main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    private static final int Fragment_trangchu = 0;
    private static final int Fragment_quanlycudan = 1;
    private static final int Fragment_dichvu = 2;
    private static final int Fragment_thongbao = 3;
    private static final int Fragment_phanhoi = 4;
    private  int Currentfragment = Fragment_trangchu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly_dangnhap_main);

        Toolbar toolbar = findViewById(R.id.toolbar_quanly);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout_quanly);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view_quanly);
        navigationView.setNavigationItemSelectedListener(this);


        replaceFragment(new Trangchu_quanly());
        navigationView.getMenu().findItem(R.id.id_trang_chu_quanly).setChecked(true);
        toolbar.setTitle("Trang chủ");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Toolbar toolbar = findViewById(R.id.toolbar_quanly);

        int id = item.getItemId();
        switch (id){
            case R.id.id_trang_chu_quanly:
                if (Currentfragment != Fragment_trangchu){
                    replaceFragment(new Trangchu_quanly());
                    Currentfragment = Fragment_trangchu;
                    toolbar.setTitle("Trang chủ");
                }
                break;
            case R.id.id_quan_ly_cu_dan:
                if (Currentfragment != Fragment_quanlycudan){
                    replaceFragment(new Quanlycudan());
                    Currentfragment = Fragment_quanlycudan;
                    toolbar.setTitle("Quản lý cư dân");
                }
                break;
            case R.id.id_quan_ly_dich_vu:
                if (Currentfragment != Fragment_dichvu){
                    replaceFragment(new Dichvu_quanly());
                    Currentfragment = Fragment_dichvu;
                    toolbar.setTitle("Dịch vụ");
                }
                break;
            case R.id.id_quan_ly_thong_bao:
                if (Currentfragment != Fragment_thongbao){
                    replaceFragment(new Thongbao_quanly());
                    Currentfragment = Fragment_thongbao;
                    toolbar.setTitle("Thông báo");
                }
                break;
            case R.id.id_quan_ly_phan_hoi:
                if (Currentfragment != Fragment_phanhoi){
                    replaceFragment(new Phanhoi_quanly());
                    Currentfragment = Fragment_phanhoi;
                    toolbar.setTitle("Phản hồi");
                }
                break;
            case R.id.id_quan_ly_dang_xuat:
                Intent dang_nhap_quanly = new Intent(Quanly_dangnhap_main.this, MainActivity.class);
                startActivity(dang_nhap_quanly);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame_quanly, fragment);
        fragmentTransaction.commit();

    }
}