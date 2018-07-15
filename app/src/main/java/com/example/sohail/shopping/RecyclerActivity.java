package com.example.sohail.shopping;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    String[] productname; //={
//            "Kids Tshirt",
//            "Shoe",
//            "Jegging",
//            "Pink Top",
//            "product"
//    };

    int[] productprice; //= {
//            485,
//            199,
//            299,
//            499,
//            485
//    };

    String[] imagepath;//= {
//            "https://assets.ajio.com/medias/sys_master/root/h69/h1a/10780758573086/-1117Wx1400H-460106574-blue-MODEL.jpg",
//            "https://assets.ajio.com/medias/sys_master/root/h19/h59/10963586908190/-473Wx593H-460133297-greymelange-MODEL.jpg",
//            "https://assets.ajio.com/medias/sys_master/root/he4/h69/10870565404702/-473Wx593H-460131556-navy-MODEL.jpg",
//            "https://assets.ajio.com/medias/sys_master/root/h79/h36/11496926609438/ajio-ankle-length-jeggings-with-frayed-hems.jpg",
//            "https://assets.ajio.com/medias/sys_master/root/h81/hc0/11500461817886/-473Wx593H-460202588-pink-MODEL.jpg"
//    };



    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    List<String> images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        recyclerView = findViewById(R.id.recycler_view);

        images = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Products");
//        for(int k =0;k<productname.length;k++){
//            for(int i=0;i<imagepath.length;i++){
//                databaseReference.child(productname[k]).child("Images").child(String.valueOf(i)).setValue(imagepath[i]);
//            }
//        }


        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Products");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                ArrayList<String> images;
//                images = new ArrayList<String>();
                int NumberOfChildren = (int) dataSnapshot.getChildrenCount();
               int i =0;
               int size = (int) dataSnapshot.getChildrenCount();
               productprice = new int[size];
               productname = new String[size];
               imagepath = new String[size];
                Iterable<DataSnapshot> children =  dataSnapshot.getChildren();
                for (DataSnapshot child: children) {

                    productname[i] = child.getKey();
                    String p = child.child("Price").getValue().toString();
                    productprice[i] = Integer.valueOf(p);
                    imagepath[i] = child.child("Images").child(String.valueOf(0)).getValue().toString();
                    i++;
//                    images = (ArrayList<String>) child.child("Images").getValue();
//                    int size = images.size();
//                    String[] img;
//                    img = new String[size];
//                    for(i =0;i<size;i++){
//                        img[i] = images.get(i);
//                    }





//                    img[0] = images.get(0);
                    recyclerViewAdapter = new RecyclerViewAdapter(RecyclerActivity.this,imagepath,productname,productprice);
                    layoutManager = new GridLayoutManager(RecyclerActivity.this,2);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(recyclerViewAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        Toast.makeText(RecyclerActivity.this,"Recycler Activity",Toast.LENGTH_SHORT).show();
//        recyclerViewAdapter = new RecyclerViewAdapter(this,imagepath,productname,productprice);
//        layoutManager = new GridLayoutManager(this,2);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(recyclerViewAdapter);

    }
}
