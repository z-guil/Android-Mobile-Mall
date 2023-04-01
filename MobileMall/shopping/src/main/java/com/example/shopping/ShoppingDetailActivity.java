package com.example.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopping.dao.GoodDao;
import com.example.shopping.database.GoodDatabase;
import com.example.shopping.entity.Cart;
import com.example.shopping.entity.Good;

public class ShoppingDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private GoodDao goodDao;
    private Good good;
    private ImageView pic;
    private TextView tv_price;
    private TextView tv_desc;
    private TextView tv_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_detail);
        String name =  getIntent().getStringExtra("GoodName");
        goodDao = GoodDatabase.getInstance(this.getApplication()).goodDao();
        good = goodDao.queryByName(name);
        TextView tv = findViewById(R.id.tv_titile);
        tv.setText("商品详情");
        show();
    }

    private void show() {
        pic = (ImageView) findViewById(R.id.iv_goods_pic);
        tv_price = (TextView) findViewById(R.id.tv_goods_price);
        tv_desc = (TextView) findViewById(R.id.tv_goods_desc);
        tv_count = (TextView) findViewById(R.id.tv_count);

        pic.setImageResource(good.picPath);
        tv_price.setText(String.valueOf(good.price));
        tv_desc.setText(good.description);
        tv_count.setText(String.valueOf(goodDao.queryAllFromCart().size()));

        findViewById(R.id.btn_add_cart).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.iv_cart).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_cart:
                Cart cart = new Cart(good.name,good.description,good.price,good.picPath);
                goodDao.insertIntoCart(cart);
                show();
                break;
            case R.id.iv_back:
                Intent intent1 = new Intent(this,MainActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
                break;
            case R.id.iv_cart:
                Intent intent2 = new Intent(this,CartActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
                break;
        }

    }
}