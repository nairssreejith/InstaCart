package com.sreejithsnair.instacart.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.sreejithsnair.instacart.R;
import com.sreejithsnair.instacart.model.CartProductModel;
import com.sreejithsnair.instacart.viewmodel.ProductListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    String TAG = "Moose";
    NavController navController;
    ProductListViewModel productListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController);

        productListViewModel = new ViewModelProvider(this).get(ProductListViewModel.class);
        productListViewModel.getCart().observe(this, new Observer<List<CartProductModel>>() {
            @Override
            public void onChanged(List<CartProductModel> cartProductModels) {
                Log.d(TAG, "onChanged:" + cartProductModels.size());
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        navController.navigateUp();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }
}