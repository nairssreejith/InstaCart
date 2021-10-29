package com.sreejithsnair.instacart.adapters;

import static com.sreejithsnair.instacart.model.ProductModel.itemCallback;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.sreejithsnair.instacart.databinding.ItemParticularsBinding;
import com.sreejithsnair.instacart.model.ProductModel;

public class ProductListAdapter extends ListAdapter<ProductModel, ProductListAdapter.ProductViewHolder> {

    ProductListInterface productListInterface;
    public ProductListAdapter(ProductListInterface productListInterface) {
        super(ProductModel.itemCallback);
        this.productListInterface = productListInterface;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemParticularsBinding itemParticularsBinding = ItemParticularsBinding.inflate(layoutInflater, parent, false);
        itemParticularsBinding.setProductInterface(productListInterface);
        return new ProductViewHolder(itemParticularsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        ProductModel productModel = getItem(position);
        holder.itemParticularsBinding.setProduct(productModel);
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        ItemParticularsBinding itemParticularsBinding;

        public ProductViewHolder(ItemParticularsBinding itemParticularsBinding) {
            super(itemParticularsBinding.getRoot());
            this.itemParticularsBinding = itemParticularsBinding;
        }
    }

    public interface ProductListInterface{
        void addProduct(ProductModel productModel);
        void onProductClick(ProductModel productModel);
    }
}
