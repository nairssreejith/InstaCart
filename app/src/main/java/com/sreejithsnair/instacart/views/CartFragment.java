package com.sreejithsnair.instacart.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sreejithsnair.instacart.R;
import com.sreejithsnair.instacart.adapters.CartListAdapter;
import com.sreejithsnair.instacart.databinding.FragmentCartBinding;
import com.sreejithsnair.instacart.model.CartProductModel;
import com.sreejithsnair.instacart.viewmodel.ProductListViewModel;

import java.util.List;

public class CartFragment extends Fragment implements CartListAdapter.CartInterface{

    ProductListViewModel productListViewModel;
    FragmentCartBinding fragmentCartBinding;
    NavController navController;
    CartListAdapter cartListAdapter;

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentCartBinding = FragmentCartBinding.inflate(inflater, container, false);
        return fragmentCartBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        cartListAdapter = new CartListAdapter(this);
        fragmentCartBinding.cartRecyclerView.setAdapter(cartListAdapter);



        productListViewModel = new ViewModelProvider(requireActivity()).get(ProductListViewModel.class);
        productListViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartProductModel>>() {
            @Override
            public void onChanged(List<CartProductModel> cartProductModels) {
                cartListAdapter.submitList(cartProductModels);
                fragmentCartBinding.placeOrder.setEnabled(cartProductModels.size()>0);
            }
        });

        productListViewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                fragmentCartBinding.orderTotal.setText("₹ " + aDouble.toString());
            }
        });
    }

    @Override
    public void deleteItem(CartProductModel cartProductModel) {
        productListViewModel.removeItemFromCart(cartProductModel);
    }

}