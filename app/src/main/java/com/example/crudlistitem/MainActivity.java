package com.example.crudlistitem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.crudlistitem.Adapter.TbItemAdapter;
import com.example.crudlistitem.DAO.tbitemDAO;
import com.example.crudlistitem.DTO.TBitem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText ed_ten_item, ed_ten_season;
    tbitemDAO tbitemDAO;
    TbItemAdapter tbItemAdapter;
    ListView listViewitem;
    ArrayList<TBitem> arrayList;
    TBitem currentObjitem = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newlistpage);

        //CreateDBitem createDBitem = new CreateDBitem(this);
        //createDBitem.getWritableDatabase();
        ed_ten_item = findViewById(R.id.ed_ten_item);
        ed_ten_season = findViewById(R.id.ed_ten_season);

        tbitemDAO = new tbitemDAO(this);
        tbitemDAO.open();

        arrayList = tbitemDAO.GetAll();
        tbItemAdapter = new TbItemAdapter(arrayList);
        listViewitem = findViewById(R.id.lv_ds_item);
        listViewitem.setAdapter(tbItemAdapter);

        listViewitem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                currentObjitem = arrayList.get(i);
                ed_ten_item.setText(currentObjitem.getTen_item());
                ed_ten_season.setText(currentObjitem.getSeason());

                return false;
            }
        });

    }
    public void AddRow(View view){
        TBitem tBitem= new TBitem();
        tBitem.setTen_item(ed_ten_item.getText().toString());
        tBitem.setSeason(ed_ten_season.getText().toString());
        long kq = tbitemDAO.AddNew(tBitem);
        if(kq>0){
            arrayList.clear();
            arrayList.addAll(tbitemDAO.GetAll());
            tbItemAdapter.notifyDataSetChanged();

            Toast.makeText(this, "Thêm mới thành công", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Thêm mới thất bại", Toast.LENGTH_SHORT).show();
        }
    }
    public void UpdateRow(View view){
        String ten_item_new = ed_ten_item.getText().toString();
        String ten_season_new = ed_ten_season.getText().toString();

        if(currentObjitem!=null &&
                (!currentObjitem.getTen_item().equals(ten_item_new) ||
                        !currentObjitem.getSeason().equals(ten_season_new)) ){
            currentObjitem.setTen_item(ten_item_new);
            currentObjitem.setSeason(ten_season_new);

            int res = tbitemDAO.Update(currentObjitem);
            if (res>0){
                ed_ten_season.setText("");
                ed_ten_item.setText("");

                arrayList.clear();
                arrayList.addAll(tbitemDAO.GetAll());
                tbItemAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                currentObjitem = null;
            }else {
                Toast.makeText(this, "Kiểm tra lại thông tin!", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Không có gì thay đổi để cập nhật", Toast.LENGTH_SHORT).show();
        }
    }
    public void DeleteRow(View view){
            if(currentObjitem!=null){
                int res = tbitemDAO.Delete(currentObjitem);
                if(res >0){
                    arrayList.clear();
                    arrayList.addAll(tbitemDAO.GetAll());
                    tbItemAdapter.notifyDataSetChanged();
                    ed_ten_item.setText("");
                    ed_ten_season.setText("");

                    Toast.makeText(this, "Đã xóa thành công!", Toast.LENGTH_SHORT).show();
                    currentObjitem = null;
                }else{
                    Toast.makeText(this, "Lỗi xóa", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Hãy bấm giữ một bản ghi nào đó trước khi xóa", Toast.LENGTH_SHORT).show();
            }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tbitemDAO.close();
    }
}