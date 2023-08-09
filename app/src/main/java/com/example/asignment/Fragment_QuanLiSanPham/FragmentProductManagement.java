package com.example.asignment.Fragment_QuanLiSanPham;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asignment.Adapter.SanPhamAdapter;
import com.example.asignment.DAO.SanPhamDAO;
import com.example.asignment.R;
import com.example.asignment.SanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FragmentProductManagement extends Fragment {

    private RecyclerView recyclerView;

    private FloatingActionButton floatingAdd;

    private SanPhamDAO sanPhamDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_productmanagement, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        floatingAdd = view.findViewById(R.id.icon_add);

        sanPhamDAO = new SanPhamDAO(getContext());
        loadData();

        floatingAdd.setOnClickListener(view1 -> {
            showDialogAdd();
        });

        return view;
    }

    public void loadData() {
        ArrayList<SanPham> list = sanPhamDAO.getDs();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        SanPhamAdapter adapter = new SanPhamAdapter(getContext(), list, sanPhamDAO);
        recyclerView.setAdapter(adapter);
    }

    private void showDialogAdd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_product, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // add & cancel trong Dialog
        EditText edtTen = view.findViewById(R.id.edtTenSP);
        EditText edtGia = view.findViewById(R.id.edtGiaSP);
        EditText edtSoLuong = view.findViewById(R.id.edtSoLuongSP);

        Button btnAdd = view.findViewById(R.id.btnAdd);
        Button btnCancel = view.findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(view1 -> {
            String tensp = edtTen.getText().toString();
            String giaban = edtGia.getText().toString();
            String soluong = edtSoLuong.getText().toString();

            if (tensp.length() == 0 || giaban.length() == 0 || soluong.length() == 0) {
                Toast.makeText(getContext(), "Vui long nhap day du thong tin", Toast.LENGTH_SHORT).show();
            } else {
                SanPham sanPham = new SanPham(tensp, Integer.parseInt(giaban), Integer.parseInt(soluong));
                boolean check = sanPhamDAO.themSanPham(sanPham);

                if (check) {
                    Toast.makeText(getContext(), "Them san pham thanh cong", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    loadData();
                } else {
                    Toast.makeText(getContext(), "Them san pham that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(view1 -> {
            alertDialog.dismiss();
        });

    }
}
