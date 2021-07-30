package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class register extends AppCompatActivity {

    EditText ed1, ed2, ed4, ed8, ed6;
    TextView ed3;
    Button bt1;
    Spinner sp;
    DatePickerDialog dp;
    String str=""; int strOldlen=0;
    ImageView iv1;
    private static final int CAMERA_REQUEST = 1888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        iv1= (ImageView)findViewById(R.id.imageView3);
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });


        ed1= (EditText)findViewById(R.id.editText);
        ed2= (EditText)findViewById(R.id.editText2);
        ed3= (TextView) findViewById(R.id.editText3);
        ed4= (EditText)findViewById(R.id.editText4);
        ed8= (EditText)findViewById(R.id.editText8);
        ed6= (EditText)findViewById(R.id.editText6);
        bt1= (Button)findViewById(R.id.button);


        ed1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                str = ed1.getText().toString();
                int strLen = str.length();

                if(strOldlen<strLen) {
                    if (strLen > 0) {
                        if (strLen == 4 || strLen == 9) {
                            str=str+" ";
                            ed1.setText(str);
                            ed1.setSelection(ed1.getText().length());
                        }
                        else{
                            if(strLen==5){
                                if(!str.contains(" ")){
                                    String tempStr=str.substring(0,strLen-1);
                                    tempStr +=" "+str.substring(strLen-1,strLen);
                                    ed1.setText(tempStr);
                                    ed1.setSelection(ed1.getText().length());
                                }
                            }
                            if(strLen==10){
                                if(str.lastIndexOf(" ")!=9){
                                    String tempStr=str.substring(0,strLen-1);
                                    tempStr +=" "+str.substring(strLen-1,strLen);
                                    ed1.setText(tempStr);
                                    ed1.setSelection(ed1.getText().length());
                                }
                            }
                            strOldlen = strLen;
                        }
                    }
                    else    return;
                }
                else   strOldlen = strLen;
            }
        });


        setDateTimeField();
        ed3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dp.show();
                return false;
            }
        });


        String s[]= {"Select your Gender","Male", "Female", "Others"};
        sp= (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> aa= new ArrayAdapter(this, android.R.layout.simple_spinner_item, s){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)   return false;
                else    return true;
            }

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTextColor(Color.parseColor("#7F8C8D"));
                return v;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0)   tv.setTextColor(Color.GRAY);
                else    tv.setTextColor(Color.BLACK);
                return view;
            }
        };

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setPrompt("");
        sp.setAdapter(aa);


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int flag=0;
                if(ed1.getText().toString().isEmpty() || ed1.length()!=14){ ed1.setError("Enter Valid Aadhaar Number of 12 digits!"); flag=1;}
                if(ed8.getText().toString().isEmpty() ){ ed8.setError("Enter Valid Employee ID!"); flag=1;}
                if(ed6.getText().toString().isEmpty() || ed6.length()!=10){ ed6.setError("Enter Valid Aadhaar Number of 12 digits!"); flag=1;}
                if(ed2.getText().toString().isEmpty()){ ed2.setError("Enter Valid Name!"); flag=1;}
                if(ed3.getText().toString().isEmpty()){ ed3.setError("Enter Valid Data!"); flag=1;}
                if(ed4.getText().toString().isEmpty()  || !ed4.getText().toString().contains(".") || !ed4.getText().toString().contains("@")){
                    ed4.setError("Enter Valid Email ID!"); flag=1;}
                if(sp.getId()==0 || sp.equals("Select your Gender")){ ((TextView)sp.getSelectedView()).setError("Enter Valid Data!"); flag=1;}

                if(flag==0){
                    Toast.makeText(getApplicationContext(), "Submitted Successfully", Toast.LENGTH_LONG).show();
                    Intent i= new Intent(getApplicationContext(), face.class);
                    startActivity(i);
                }
            }
        });
    }

    private void setDateTimeField() {
        Calendar c = Calendar.getInstance();
        dp = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar cd = Calendar.getInstance();
                cd.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
                final Date s2 = cd.getTime();
                String fdate = sd.format(s2);
                ed3.setText(fdate);
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        dp.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST) {
            Bitmap bt= (Bitmap) data.getExtras().get("data");
            iv1.setImageBitmap(bt);
        }
    }
}