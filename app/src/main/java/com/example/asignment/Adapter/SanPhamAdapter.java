package com.example.asignment.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asignment.DAO.SanPhamDAO;
import com.example.asignment.R;
import com.example.asignment.SanPham;

import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder> {

    private final Context context;
    private ArrayList<SanPham> list;
    private SanPhamDAO sanPhamDAO;

    public SanPhamAdapter(Context context, ArrayList<SanPham> list, SanPhamDAO sanPhamDAO) {
        this.context = context;
        this.list = list;
        this.sanPhamDAO = sanPhamDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sanpham, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTen.setText(list.get(position).getTensp());
        holder.txtGia.setText(list.get(position).getGiaban() + " VND");
        holder.txtSoLuong.setText("SL: " + list.get(position).getSoluong());

        // update san pham
        holder.txtSua.setOnClickListener(view -> {
            ShowDialogUpdate(list.get(holder.getAdapterPosition()));
        });


        // xoa san pham
        holder.txtXoa.setOnClickListener(view -> {

            showDialogDelete(list.get(holder.getAdapterPosition()).getTensp(), list.get(holder.getAdapterPosition()).getMasp());

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTen, txtGia, txtSoLuong, txtSua, txtXoa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.tv_Name);
            txtGia = itemView.findViewById(R.id.tv_Gia);
            txtSoLuong = itemView.findViewById(R.id.tv_SoLuong);
            txtSua = itemView.findViewById(R.id.tv_ChinhSua);
            txtXoa = itemView.findViewById(R.id.tv_Xoa);
        }
    }


    private void ShowDialogUpdate(SanPham sanPham) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_update_product, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText edtTenSp = view.findViewById(R.id.edtTenSP);
        EditText edtGiaSP = view.findViewById(R.id.edtGiaSP);
        EditText edtSoLuongSP = view.findViewById(R.id.edtSoLuongSP);
        Button btnUpdate = view.findViewById(R.id.btnUpdate);
        Button btnCancel = view.findViewById(R.id.btnCancel);

        // dua du lieu cua item can chinh sua len edt
        edtTenSp.setText(sanPham.getTensp());
        edtGiaSP.setText(String.valueOf(sanPham.getGiaban()));
        edtSoLuongSP.setText(String.valueOf(sanPham.getSoluong()));

        btnCancel.setOnClickListener(view1 -> {
            alertDialog.dismiss();
        });

        btnUpdate.setOnClickListener(view1 -> {
            int masp = sanPham.getMasp();
            String tensp = edtTenSp.getText().toString();
            String giaban = edtGiaSP.getText().toString();
            String soluong = edtSoLuongSP.getText().toString();

            if (tensp.length() == 0 || giaban.length() == 0 || soluong.length() == 0) {
                Toast.makeText(context, "Vui long nhap day du thong tin", Toast.LENGTH_SHORT).show();
            } else {
                SanPham sanPham1 = new SanPham(masp, tensp, Integer.parseInt(giaban), Integer.parseInt(soluong));
                boolean check = sanPhamDAO.UpdateSanPham(sanPham1);

                if (check) {
                    Toast.makeText(context, "Update san pham thanh cong", Toast.LENGTH_SHORT).show();

                    LoadData();

                    alertDialog.dismiss();
                } else {
                    Toast.makeText(context, "update san pham that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void showDialogDelete(String tensp, int masp) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Warning");
        builder.setMessage("Ban co chan chan muon xoa san pham nay khong?");

        builder.setPositiveButton("Xoa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean check = sanPhamDAO.DeleteSanPham(masp);
                if (check) {
                    Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_SHORT).show();

                    LoadData();
                } else {
                    Toast.makeText(context, "Xoa that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Huy", null);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void LoadData() {
        list.clear();
        list = sanPhamDAO.getDs();
        notifyDataSetChanged();
    }

}