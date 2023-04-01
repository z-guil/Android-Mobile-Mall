package com.example.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shopping.dao.GoodDao;
import com.example.shopping.database.GoodDatabase;
import com.example.shopping.entity.Cart;
import com.example.shopping.entity.Good;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private GoodDao goodDao;
    private GridLayout gl_channel;
    private TextView count;
    private TextView tv_title;
    private List<Cart> cartList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gl_channel = (GridLayout)findViewById(R.id.gl_channel);
        tv_title = findViewById(R.id.tv_titile);
        tv_title.setText("手机商城");
        count = findViewById(R.id.tv_count);
        findViewById(R.id.iv_cart).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);




        goodDao = GoodDatabase.getInstance(this.getApplication()).goodDao();
        //刷新购物车列表
        cartList = goodDao.queryAllFromCart();
        //刷新购物车列表数目
        count.setText(String.valueOf(cartList.size()));

//导入测试用例数据
//        goodDao.deleteAll();
//        Good g1 = new Good();
//        Good g2 = new Good();
//        Good g3 = new Good();
//        Good g4 = new Good();
//        Good g5 = new Good();
//        Good g6 = new Good();
//        g1.setName("iPhone11");
//        g2.setName("Mate30");
//        g3.setName("小米10");
//        g4.setName("OPPO Reno3");
//        g5.setName("vivo X30");
//        g6.setName("荣耀30S");
//        g1.setPrice(6299);
//        g2.setPrice(4999);
//        g3.setPrice(3999);
//        g4.setPrice(2999);
//        g5.setPrice(2998);
//        g6.setPrice(2399);
//        g1.setDescription("Apple iPhone11 256GB 绿色 4G全网通手机");
//        g2.setDescription("华为 HUAWEIMate30 8GB+256GB 丹霞橙 5G全网通 全面屏手机");
//        g3.setDescription("小米 MI10 8GB+128GB 银黑 5G手机 游戏拍照手机");
//        g4.setDescription("OPPO Reno3 8GB+128GB 蓝色星夜 双模5G 拍照游戏智能手机");
//        g5.setDescription("vivo X30 8GB+128GB 绯云 5G全网通 美颜拍照手机");
//        g6.setDescription("荣耀30S 8GB+128GB 蝶羽红 5G芯片 自拍全面屏手机");
//        g1.setPicPath(R.drawable.iphone11);
//        g2.setPicPath(R.drawable.mate30);
//        g3.setPicPath(R.drawable.xiaomi);
//        g4.setPicPath(R.drawable.oppo);
//        g5.setPicPath(R.drawable.vivo);
//        g6.setPicPath(R.drawable.honor);
//        goodDao.insert(g1);
//        goodDao.insert(g2);
//        goodDao.insert(g3);
//        goodDao.insert(g4);
//        goodDao.insert(g5);
//        goodDao.insert(g6);


        showGoods();

    }

    private void showGoods() {
        //商品条目是一个线性布局，设置布局的宽度为屏幕的一半
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenWidth / 2, LinearLayout.LayoutParams.WRAP_CONTENT);

        List<Good> goods = goodDao.queryAll();
        for (Good good:goods) {
            //获取布局文件item_goods.xml的根视图
            View v = LayoutInflater.from(this).inflate(R.layout.item_goods, null);
            ImageView pic = (ImageView) v.findViewById(R.id.iv_thumb);
            TextView tv_name = (TextView) v.findViewById(R.id.tv_name);
            TextView tv_price = (TextView) v.findViewById(R.id.tv_price);
            Button btn_add = (Button)v.findViewById(R.id.btn_add);
            //设置参数
            pic.setImageResource(good.getPicPath());
            tv_name.setText(good.getName());
            tv_price.setText(String.valueOf(good.getPrice()));
            //将item添加到GridLayout中
            gl_channel.addView(v,params);


            //查看商品详情
            pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, ShoppingDetailActivity.class);
                    intent.putExtra("GoodName", good.name);
                    MainActivity.this.startActivity(intent);
                }
            });
            //加入购物车
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cart cart = new Cart(good.name, good.description, good.price, good.picPath);
                    goodDao.insertIntoCart(cart);
                    cartList = goodDao.queryAllFromCart();
                    count.setText(String.valueOf(cartList.size()));
                }
            });

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_cart:
                Intent intent = new Intent(this,CartActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }
}