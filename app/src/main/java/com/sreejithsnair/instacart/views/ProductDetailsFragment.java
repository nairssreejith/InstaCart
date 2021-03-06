package com.sreejithsnair.instacart.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sreejithsnair.instacart.R;
import com.sreejithsnair.instacart.database.Cart;
import com.sreejithsnair.instacart.database.CartDatabase;
import com.sreejithsnair.instacart.databinding.FragmentProductDetailsBinding;
import com.sreejithsnair.instacart.model.ProductModel;
import com.sreejithsnair.instacart.viewmodel.ProductListViewModel;

import java.util.List;

public class ProductDetailsFragment extends Fragment {

    FragmentProductDetailsBinding fragmentProductDetailsBinding;
    ProductListViewModel productListViewModel;

    CartDatabase cartDatabase;
    Cart cart;


    public ProductDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentProductDetailsBinding = FragmentProductDetailsBinding.inflate(inflater, container, false);
        return fragmentProductDetailsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cartDatabase = CartDatabase.getInstance(getContext());

        productListViewModel = new ViewModelProvider(requireActivity()).get(ProductListViewModel.class);
        fragmentProductDetailsBinding.setProductListViewModel(productListViewModel);

        fragmentProductDetailsBinding.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productListViewModel.insertIntoCartBD(cart, cartDatabase);
                Log.d("Database", "Add to Cart OnClick Hit");
            }
        });

    }

}