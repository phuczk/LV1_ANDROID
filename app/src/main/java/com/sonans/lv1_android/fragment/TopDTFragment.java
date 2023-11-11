package com.sonans.lv1_android.fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.sonans.lv1_android.Dao.HoaDonDao;
import com.sonans.lv1_android.Dao.NhapKhoDao;
import com.sonans.lv1_android.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class TopDTFragment extends Fragment {
    private PieChart mChart;
    TextView tvxuatKho, tvnhap, tvLoiNhuan;

    EditText edFrom, edTo;
    ImageView imgFrom, imgTo;
    Button btnSee, btnBefore, btnAfter;
    TextView tvDoanhThu, tvNhap, tvLai;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    int mDay, mMonth, mYear;
    float xuat, nhap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_top_d_t, container, false);
        edFrom = v.findViewById(R.id.edFrom);
        edTo = v.findViewById(R.id.edTo);
        imgFrom = v.findViewById(R.id.btnFrom);
        imgTo = v.findViewById(R.id.btnTo);
        btnSee = v.findViewById(R.id.btnSee);
        tvDoanhThu = v.findViewById(R.id.soLieuNhap);
        tvNhap = v.findViewById(R.id.soLieuXuat);
        tvLai = v.findViewById(R.id.soLoiNhuan);
        imgFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = createDatePickerDialog(edFrom);
                datePickerDialog.show();
            }
        });

        imgTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = createDatePickerDialog(edTo);
                datePickerDialog.show();
            }
        });

        btnSee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String from = edFrom.getText().toString();
                String to = edTo.getText().toString();
                HoaDonDao hoaDonDao = new HoaDonDao(getActivity());
                String doanhThu = String.valueOf(hoaDonDao.getDoanhThu(from, to));
                tvDoanhThu.setText(doanhThu);
                NhapKhoDao nhapKhoDao = new NhapKhoDao(getActivity());
                String tienMua = String.valueOf(nhapKhoDao.getDoanhThu(from, to));
                tvNhap.setText(tienMua);
//                tvLai.setText((int) (Float.parseFloat(doanhThu) - Float.parseFloat(tienMua)));
                xuat = Float.parseFloat(doanhThu);
                nhap = Float.parseFloat(tienMua);
                float lai = xuat - nhap;
                tvLai.setText(String.valueOf(lai));
                if(lai <= 0){
                    tvLai.setTextColor(Color.RED);
                }else {
                    tvLai.setTextColor(Color.GREEN);
                }
                Log.d("/////", String.valueOf(xuat));

//                createPieChart(xuat);
                createPieChart(xuat);// Tạo PieChart sau khi xuat được cập nhật
            }
        });

        return v;
    }

    private void createPieChart(float xuat) {
        PieChart pieChart = new PieChart(getActivity());

        // Thiết lập kích thước và các thuộc tính khác cho PieChart
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        pieChart.setLayoutParams(layoutParams);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);

        // Tạo danh sách PieEntries (dữ liệu cho đồ thị Pie Chart)
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(xuat, "doanh thu"));
        entries.add(new PieEntry(nhap, "tien mua"));

        // Tạo PieDataSet và cấu hình các thuộc tính
        PieDataSet dataSet = new PieDataSet(entries, "Pie Chart");
        dataSet.setColors(Color.GREEN, Color.RED);
        dataSet.setValueTextSize(10f);
        dataSet.setValueTextColor(Color.rgb(225, 225, 225));

        // Tạo PieData từ PieDataSet
        PieData pieData = new PieData(dataSet);

        // Thiết lập PieData cho PieChart
        pieChart.setData(pieData);

        // Thêm PieChart vào LinearLayout
        LinearLayout chartContainer = getActivity().findViewById(R.id.chartContainer);
        // Xóa bỏ các PieChart trước đó (nếu có)
        chartContainer.addView(pieChart);
    }

    private DatePickerDialog createDatePickerDialog(final EditText editText) {
        final Calendar currentDate = Calendar.getInstance();
        mDay = currentDate.get(Calendar.DATE);
        mMonth = currentDate.get(Calendar.MONTH);
        mYear = currentDate.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mDay = dayOfMonth;
                mMonth = monthOfYear;
                mYear = year;

                Calendar selectedDate = new GregorianCalendar(mYear, mMonth, mDay);
                Date date = selectedDate.getTime();
                String formattedDate = sdf.format(date);
                editText.setText(formattedDate);
            }
        }, mYear, mMonth, mDay);

        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        return datePickerDialog;
    }
}