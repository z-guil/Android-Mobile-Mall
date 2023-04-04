package com.example.shopping;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shopping.adapter.CartAdapter;
import com.example.shopping.dao.GoodDao;
import com.example.shopping.database.GoodDatabase;
import com.example.shopping.entity.Cart;

import java.util.List;

public class CartActivity extends AppCompatActivity implements View.OnClickListener {

    private GoodDao goodDao;
    private List<Cart> cartList;
    private ListView lv_cart;
    private TextView tv_count;
    private TextView tv_total_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_listview);
        goodDao = GoodDatabase.getInstance(this.getApplication()).goodDao();
        cartList = goodDao.queryAllFromCart();
        lv_cart = (ListView)findViewById(R.id.lv_cart);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.btn_settle).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);


        showCart();
    }

    private void showCart() {
        tv_count = findViewById(R.id.tv_count);
        tv_total_price = findViewById(R.id.tv_total_price);



//        lv_cart.removeAllViews();
        CartAdapter adapter = new CartAdapter(this,cartList);
        lv_cart.setAdapter(adapter);
        int count = adapter.getCount();
        Double sum = new Double(0);
        for (int i = 0; i < cartList.size(); i++) {
            sum+=cartList.get(i).price;
        }
//        for (Cart cart:cartList) {
//
//            //获取布局文件item_cart.xml的根视图
//            View v = LayoutInflater.from(this).inflate(R.layout.item_cart, null);
//            ImageView pic = (ImageView) v.findViewById(R.id.iv_thumb);
//            TextView tv_name = (TextView) v.findViewById(R.id.tv_name);
//            TextView tv_desc = (TextView) v.findViewById(R.id.tv_desc);
//            TextView tv_price = (TextView) v.findViewById(R.id.tv_price);
//            //设置参数
//            pic.setImageResource(cart.picPath);
//            tv_name.setText(cart.name);
//            tv_desc.setText(cart.description);
//            tv_price.setText(String.valueOf(cart.price));
//            //
//            count++;
//            sum+=cart.price;
//            //插入子布局
//            ll_cart.addView(v);
//        }
        tv_count.setText(String.valueOf(count));
        tv_total_price.setText(String.valueOf(sum));
        //如果为空，展示空的布局
        if(count==0){
            findViewById(R.id.ll_empty).setVisibility(View.VISIBLE);
            findViewById(R.id.btn_shopping_channel).setOnClickListener(this);
        }else{
            findViewById(R.id.ll_empty).setVisibility(View.INVISIBLE);
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_clear:
                goodDao.deleteAllFromCart();
                cartList = goodDao.queryAllFromCart();
                showCart();
                break;
            case R.id.btn_settle:
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("尊敬的客户")
                        .setMessage("功能还未开发")
                        .setPositiveButton("确定",null)
                        .create();
                dialog.show();
                break;
            case R.id.iv_back:
            case R.id.btn_shopping_channel:
                Intent intent = new Intent(this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }
}