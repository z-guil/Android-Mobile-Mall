package com.example.shopping.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping.R;
import com.example.shopping.entity.Cart;

import org.w3c.dom.Text;

import java.util.List;

public class CartAdapter extends BaseAdapter {
    private Context mContext;
    private List<Cart> cartList;

    public CartAdapter(Context mContext, List<Cart> cartList) {
        this.mContext = mContext;
        this.cartList = cartList;
    }

    @Override
    public int getCount() {
        return cartList.size();
    }

    @Override
    public Object getItem(int position) {
        return cartList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_cart, null);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_desc = (TextView) convertView.findViewById(R.id.tv_desc);
            holder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            holder.iv_thumb = (ImageView) convertView.findViewById(R.id.iv_thumb);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Cart cart = cartList.get(position);
        holder.tv_name.setText(cart.getName());
        holder.tv_price.setText(String.valueOf(cart.getPrice()));
        holder.tv_desc.setText(cart.getDescription());
        holder.iv_thumb.setImageResource(cart.getPicPath());
        return convertView;
    }

    public final class ViewHolder {
        public TextView  tv_name;
        public TextView  tv_desc;
        public TextView  tv_price;
        public ImageView iv_thumb;
    }
}
