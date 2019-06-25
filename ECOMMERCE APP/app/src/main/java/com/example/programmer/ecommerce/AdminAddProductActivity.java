package com.example.programmer.ecommerce;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AdminAddProductActivity extends AppCompatActivity
{

    private String Categoryname,Description,Name,Price,SaveCurrentDate,SaveCurrentTime;

    private ImageView InputProductImage;
    private EditText ProductName,ProductDescription,ProductPrice;
    private Button AddProductBtn;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String productRandomKey,downloadImageUrl;
    private StorageReference storageReference;
    private DatabaseReference productsRef;
    private ProgressDialog loadingbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_product);


        loadingbar = new ProgressDialog(this);

        storageReference = FirebaseStorage.getInstance().getReference().child("Product Images");

        productsRef = FirebaseDatabase.getInstance().getReference().child("products");

        InputProductImage = (ImageView) findViewById(R.id.Add_product_logo);

         ProductName  = (EditText) findViewById(R.id.product_name);
         ProductDescription  = (EditText) findViewById(R.id.product_description);
         ProductPrice  = (EditText) findViewById(R.id.product_price);

         AddProductBtn = (Button) findViewById(R.id.Add_product_btn);

        Categoryname = getIntent().getExtras().get("Category").toString();

        InputProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OpenGallery();
            }

        });

        AddProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ValidateProductDetail();
            }
        });

    }

    private void OpenGallery() {

        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,GalleryPick);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==GalleryPick && resultCode==RESULT_OK && data !=null){


            ImageUri = data.getData();
            InputProductImage.setImageURI(ImageUri);

        }

    }


    private void ValidateProductDetail() {

        Description = ProductDescription.getText().toString();
        Name = ProductName.getText().toString();
        Price = ProductPrice.getText().toString();

        if(ImageUri == null){

            Toast.makeText(AdminAddProductActivity.this , "product image is mandatory..",Toast.LENGTH_SHORT).show();

        }

       else if(TextUtils.isEmpty(Description)){

            Toast.makeText(AdminAddProductActivity.this , "product Description is mandatory..",Toast.LENGTH_SHORT).show();

        }

       else if(TextUtils.isEmpty(Name)){

            Toast.makeText(AdminAddProductActivity.this , "product Name is mandatory..",Toast.LENGTH_SHORT).show();

        }

        else if(TextUtils.isEmpty(Price)){

            Toast.makeText(AdminAddProductActivity.this , "product Price is mandatory..",Toast.LENGTH_SHORT).show();

        }

        else{

            StoreProductInformation();

        }


    }

    private void StoreProductInformation() {

        loadingbar.setTitle("Add New Product");
        loadingbar.setMessage("Dear Admin, please wait while we are adding the new product.");
        loadingbar.setCanceledOnTouchOutside(false);
        loadingbar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat CurrentDate = new SimpleDateFormat("MMM dd,YYYY");
        SaveCurrentDate = CurrentDate.format(calendar.getTime());

        SimpleDateFormat CurrentTime = new SimpleDateFormat("HH:mm:ss a");
        SaveCurrentTime = CurrentTime.format(calendar.getTime());

        productRandomKey = SaveCurrentDate + SaveCurrentTime;

        Log.i("PRODUCT RANDOM KEY",productRandomKey);

       final   StorageReference filepath = storageReference.child(ImageUri.getLastPathSegment() + productRandomKey + ".jpg");

        final UploadTask uploadTask = filepath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                loadingbar.dismiss();
                String message = e.toString();
                Toast.makeText(AdminAddProductActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(AdminAddProductActivity.this, "Product Image uploaded Successfully...", Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                    {
                        if (!task.isSuccessful())
                        {

                            throw task.getException();
                        }

                        downloadImageUrl = filepath.getDownloadUrl().toString();
                        return filepath.getDownloadUrl();

                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            downloadImageUrl = task.getResult().toString();

                            Toast.makeText(AdminAddProductActivity.this, "got the Product image Url Successfully...", Toast.LENGTH_SHORT).show();

                            SaveProductInformationToDatabase();
                        }

                    }
                });
            }
        });


    }
    private void SaveProductInformationToDatabase()

    {

       HashMap<String, Object> productMap = new HashMap<>();
       productMap.put("pid",productRandomKey);
       productMap.put("date",SaveCurrentDate);
       productMap.put("time",SaveCurrentTime);
       productMap.put("description",Description);
       productMap.put("image",downloadImageUrl);
       productMap.put("category",Categoryname);
       productMap.put("price",Price);
       productMap.put("pName",Name);


        productsRef.child(productRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful())
                {

                    Intent intent = new Intent(AdminAddProductActivity.this,AdminCategoryActivity.class);
                    startActivity(intent);

                    loadingbar.dismiss();
                    Toast.makeText(AdminAddProductActivity.this,"product is added successfully...",Toast.LENGTH_SHORT).show();

                }

                else {

                    loadingbar.dismiss();
                    String message = task.getException().toString();
                    Toast.makeText(AdminAddProductActivity.this,"Error: "+message,Toast.LENGTH_SHORT).show();
                }


            }
        });



    }


}
