package com.sreejithsnair.instacart.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sreejithsnair.instacart.R;
import com.sreejithsnair.instacart.adapters.ProductListAdapter;
import com.sreejithsnair.instacart.databinding.FragmentProductListBinding;
import com.sreejithsnair.instacart.model.ProductModel;
import com.sreejithsnair.instacart.viewmodel.ProductListViewModel;

import java.util.List;

public class ProductListFragment extends Fragment implements ProductListAdapter.ProductListInterface {

    FragmentProductListBinding fragmentProductListBinding;

    private ProductListAdapter productListAdapter;
    private ProductListViewModel productListViewModel;
    private NavController navController;

    String token;

    public ProductListFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentProductListBinding = FragmentProductListBinding.inflate(inflater, container, false);
        return fragmentProductListBinding.getRoot();
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        token = "Bearer ";

       if (getActivity().getIntent().getExtras() != null) {
             token += getActivity().getIntent().getStringExtra("token");
        }

        productListAdapter = new ProductListAdapter(this);
        fragmentProductListBinding.recyclerViewProductList.setAdapter(productListAdapter);

        if(token != "Bearer "){
            productListViewModel = new ViewModelProvider(requireActivity()).get(ProductListViewModel.class);
            productListViewModel.getProducts(token).observe(getViewLifecycleOwner(), new Observer<List<ProductModel>>() {
                @Override
                public void onChanged(List<ProductModel> productModels) {
                    productListAdapter.submitList(productModels);
                }
            });
        }

        navController = Navigation.findNavController(view);
    }

    @Override
    public void addProduct(ProductModel productModel) {
        boolean isAdded = productListViewModel.addItemToCart(productModel);
        Log.d("Moose", "addProduct: " + productModel.getTitle() + isAdded);
    }

    @Override
    public void onProductClick(ProductModel productModel) {
        productListViewModel.setProduct(productModel);
        navController.navigate(R.id.action_productListFragment_to_productDetailsFragment);
    }
}