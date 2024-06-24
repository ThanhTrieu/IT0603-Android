package com.example.campusexpense;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ListView listView = view.findViewById(R.id.lv_products);

        List<ProductModel> lisProduct = new ArrayList<>();
        lisProduct.add(new ProductModel(1, "Iphone 8", "https://cdn.tgdd.vn/Products/Images/42/326016/vivo-y28-vang-thumb-600x600.jpg", 1000));
        lisProduct.add(new ProductModel(2, "Iphone X", null, 1500));
        lisProduct.add(new ProductModel(3, "Iphone 11", null, 2000));
        lisProduct.add(new ProductModel(4, "Iphone 13", null, 3000));
        lisProduct.add(new ProductModel(5, "Iphone 15", null, 4000));
        lisProduct.add(new ProductModel(6, "Samsung S20", null, 2500));
        lisProduct.add(new ProductModel(7, "Samsung S22", null, 3500));
        lisProduct.add(new ProductModel(1, "Samsung S23+", null, 4500));
        ProductListViewAdapter listViewAdapter = new ProductListViewAdapter(lisProduct);
        listView.setAdapter(listViewAdapter);
        return view;
    }
}