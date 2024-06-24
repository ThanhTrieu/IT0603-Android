package com.example.campusexpense;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class ProductListViewAdapter extends BaseAdapter {
    public List<ProductModel> products;
    public ProductListViewAdapter(List<ProductModel> items){
        super();
        this.products = items;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return products.get(position).idProduct;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewProduct;
        if (convertView == null) {
            viewProduct = View.inflate(parent.getContext(), R.layout.products_list, null);
        } else {
            viewProduct = convertView;
        }
        // do du lieu tu model ra list view
        ProductModel productModel = (ProductModel) getItem(position);
        ImageView imgPd = viewProduct.findViewById(R.id.imgProduct);
        TextView tvIdPd = viewProduct.findViewById(R.id.idProduct);
        TextView tvNamePd = viewProduct.findViewById(R.id.nameProduct);
        TextView tvPricePd = viewProduct.findViewById(R.id.priceProduct);

        tvIdPd.setText(String.format("%d", productModel.idProduct));
        tvNamePd.setText(String.format("%s", productModel.nameProduct));
        Picasso.get().load(productModel.imageProduct).into(imgPd);
        tvPricePd.setText(String.format("%d",productModel.priceProduct));

        return viewProduct;
    }
}
