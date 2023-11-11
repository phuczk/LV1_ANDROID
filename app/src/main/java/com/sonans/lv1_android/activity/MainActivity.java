package com.sonans.lv1_android.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.sonans.lv1_android.R;
import com.sonans.lv1_android.fragment.DonViCungUngFragment;
import com.sonans.lv1_android.fragment.DonViVanChuyenFragment;
import com.sonans.lv1_android.fragment.HangFragment;
import com.sonans.lv1_android.fragment.HoaDonFragment;
import com.sonans.lv1_android.fragment.KhachHangFragment;
import com.sonans.lv1_android.fragment.LoaiHangFragment;
import com.sonans.lv1_android.fragment.NhanVienFragment;
import com.sonans.lv1_android.fragment.NhapKhoFragment;
import com.sonans.lv1_android.fragment.TopDTFragment;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolBar;
    View mHeaderView;
    TextView edUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolBar = findViewById(R.id.toolBar1);
        setSupportActionBar(toolBar);
        ActionBar ab = getSupportActionBar();

        ab.setHomeAsUpIndicator(R.drawable.menu);
        ab.setDisplayHomeAsUpEnabled(true);

        NavigationView nv = findViewById(R.id.nvView);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolBar, 0,0);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);
        mHeaderView = nv.getHeaderView(0);
        Fragment fragment = new LoaiHangFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = new LoaiHangFragment();

                if(item.getItemId() == R.id.nav_HoaDon){
                    toolBar.setTitle("");
                    fragment = new HoaDonFragment();
                } else if (item.getItemId() == R.id.nav_LoaiHang) {
                    toolBar.setTitle("");
                }else if (item.getItemId() == R.id.nav_Hang) {
                    toolBar.setTitle("");
                    fragment = new HangFragment();
                }else if (item.getItemId() == R.id.nav_KhachHang) {
                    toolBar.setTitle("");
                    fragment = new KhachHangFragment();
                }else if (item.getItemId() == R.id.nav_Shipping) {
                    toolBar.setTitle("");
                    fragment = new DonViVanChuyenFragment();
                }else if (item.getItemId() == R.id.nav_WinWin) {
                    toolBar.setTitle("");
                    fragment = new DonViCungUngFragment();
                }else if (item.getItemId() == R.id.nav_NhanVien) {
                    toolBar.setTitle("");
                    fragment = new NhanVienFragment();
                }else if (item.getItemId() == R.id.nav_NhapKho) {
                    toolBar.setTitle("");
                    fragment = new NhapKhoFragment();
                }else if (item.getItemId() == R.id.sub_Top) {
                    toolBar.setTitle("");
                    fragment = new LoaiHangFragment();

                }else if (item.getItemId() == R.id.sub_DoanhThu) {
                    toolBar.setTitle("");
                    fragment = new TopDTFragment();
                }else if (item.getItemId() == R.id.sub_AddUser) {
                    toolBar.setTitle("");
//                    Intent i = new Intent(MainActivity.this, SignUpActivity.class);
//                    startActivity(i);
//                    finish();
                    fragment = new LoaiHangFragment();
                }else if (item.getItemId() == R.id.sub_Pass) {
                    toolBar.setTitle("change password");
                    fragment = new LoaiHangFragment();
                }else {
                    Toast.makeText(MainActivity.this, "dang dang xuat", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(MainActivity.this, SignIn.class);
                            Toast.makeText(MainActivity.this, "dang xuat thanh cong", Toast.LENGTH_SHORT).show();
                            startActivity(i);
                            finish();
                        }
                    }, 2000);
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
                drawerLayout.close();
                return false;
            }
        });
    }
}