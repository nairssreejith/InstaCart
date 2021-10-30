package com.sreejithsnair.instacart.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.sreejithsnair.instacart.databinding.CartParticularsBinding;
import com.sreejithsnair.instacart.model.CartProductModel;

public class CartListAdapter extends ListAdapter<CartProductModel, CartListAdapter.CartViewHolder> {

    CartInterface cartInterface;
    public CartListAdapter(CartInterface cartInterface) {

        super(CartProductModel.itemCallback);
        this.cartInterface = cartInterface;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CartParticularsBinding cartParticularsBinding = CartParticularsBinding.inflate(layoutInflater, parent, false);

        return new CartViewHolder(cartParticularsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.cartParticularsBinding.setCartItem(getItem(position));
        holder.cartParticularsBinding.executePendingBindings();
    }

    class CartViewHolder extends RecyclerView.ViewHolder{
        CartParticularsBinding cartParticularsBinding;
        public CartViewHolder(CartParticularsBinding cartParticularsBinding){
            super(cartParticularsBinding.getRoot());
            this.cartParticularsBinding=cartParticularsBinding;
            cartParticularsBinding.deleteProductButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cartInterface.deleteItem((getItem(getAdapterPosition())));
                }
            });
        }

    }

    public interface CartInterface{
        void deleteItem(CartProductModel cartProductModel);
    }

}
