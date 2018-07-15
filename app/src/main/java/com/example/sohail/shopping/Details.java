package com.example.sohail.shopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {

    PhotoView photoView;
    TextView ProductPrice,ProductName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        photoView = findViewById(R.id.photo_view);
        ProductName = findViewById(R.id.product_name1);
        ProductPrice = findViewById(R.id.product_price1);

        getExtras();

    }

    public void getExtras(){

        if(getIntent().hasExtra("imageUrl") && getIntent().hasExtra("productName") && getIntent().hasExtra("productPrice")){
            String Url = getIntent().getStringExtra("imageUrl");
            String name = getIntent().getStringExtra("productName");
            String price = getIntent().getStringExtra("productPrice");
            Picasso.get().load(Url).into(photoView);
            ProductPrice.setText(price);
            ProductName.setText(name);

        }
    }

}
