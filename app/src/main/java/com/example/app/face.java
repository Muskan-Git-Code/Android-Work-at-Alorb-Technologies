package com.example.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class face extends AppCompatActivity {

    TextView tv1;
    ImageView iv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face);

        getSupportActionBar().hide();

        tv1= (TextView)findViewById(R.id.textView);
        iv1= (ImageView) findViewById(R.id.animationView);
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,0);

                Toast.makeText(getApplicationContext(), "Scanned Successfully", Toast.LENGTH_LONG).show();

                //if image matched to database then open home activity
//                Intent i2= new Intent(getApplicationContext(), home.class);
//                startActivity(i2);
            }
        });


        AlertDialog.Builder b= new AlertDialog.Builder(face.this);
        b.setIcon(R.drawable.l1);    b.setTitle("Alorb Technologies");   b.setMessage("New to Alorb Technologies?");
        b.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(getApplicationContext(),register.class);
                startActivity(intent);
            }
        });
        b.setNegativeButton("NO", null);

        AlertDialog a= b.create();
        a.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bt= (Bitmap) data.getExtras().get("data");
        iv1.setImageBitmap(bt);
        tv1.setText("");

        Intent i2= new Intent(getApplicationContext(), home.class);
        startActivity(i2);
    }
}