package com.example.multipurposecalci;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class BMICalci extends AppCompatActivity {

    EditText weight,height;
    TextView bmi,bmist;
    Button cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalci);

        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        bmi = findViewById(R.id.bmi);
        bmist = findViewById(R.id.bmist);
        cal=findViewById(R.id.cal);
    }

    public void calculate(View view){
        try {
            float w = Float.parseFloat(weight.getText() + "");
            float h = Float.parseFloat(height.getText() + "");
            h= (float) (h*0.3048);
            float val = (float) (Math.round(w/(h*h) * 100.0) / 100.0);
            bmi.setText(val + "");
            if (val < 18.5) {
                bmist.setText("Underweight");
                bmist.setTextColor(Color.parseColor("#1A9EC6"));
            } else if (val < 25) {
                bmist.setText("Normal");
                bmist.setTextColor(Color.parseColor("#65EF46"));
            } else {
                bmist.setText("Overweight");
                bmist.setTextColor(Color.parseColor("#DA0A1B"));
            }
        }
        catch (Exception e){
            Toast.makeText(this, "The BMI doesn't look right.Make sure the given values are vaild", Toast.LENGTH_SHORT).show();
        }
    }

}