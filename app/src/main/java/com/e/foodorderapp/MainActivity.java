package com.e.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {
    int number=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn= findViewById(R.id.btn_order);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitOrder();
            }
        });
        Button bt_increse=findViewById(R.id.btn_increase);
        bt_increse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increment();
            }
        });
        Button tn_decrese=findViewById(R.id.btn_decrease);
        tn_decrese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrement();
            }
        });
    }

    /**
     * This method is called to increment the quantity of coffee
     */
    public void increment(){
        number++;
        display(number);

    }
    /**
     * This method is called to decrease the quantity of coffee
     */
    public void decrement(){
        if(number>0){
            number--;
        }

        display(number);
    }
    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder() {

      displayMessage(number*50);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int num) {
        TextView quantityTextView = (TextView) findViewById(R.id.tv_quantityNum);
        quantityTextView.setText("" + num);
    }

    /**
     *
     * This method displays the price of total order.
     */

    public void displayMessage(int num){
        CheckBox cbk_Whipp=findViewById(R.id.whippedCream_cbk);
        boolean wip=cbk_Whipp.isChecked();
        CheckBox cbk_choco=findViewById(R.id.chocolate_cbk);
        boolean chc=cbk_choco.isChecked();

        if(wip==true&&chc==true){
            num=num+number*5;

        }
        else if(wip==true){
            num=num+number*2;
        }
        else if(chc==true){
            num=num+number*3;
        }

       String str="Total Amount: Rs"+num;
        EditText edt=findViewById(R.id.name);
        String ed_name=edt.getText().toString();
       String name="Name: "+ed_name;


       String wpCr="Add Whipped Cream ? "+wip;

       String chck="Add Chocolate Toppings? "+chc;
       String Quantity="Quantity : "+number;
       String thnk="Thank You!!!";
      String body=name+"\n"+wpCr+"\n"+chck+"\n"+Quantity+"\n"+str+"\n"+thnk;

      //Intent used to mail the order details to the coffee Shop owner
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));// only email apps should handle this
        String[] mail={"poojasharma120899@gmail.com"};
        intent.putExtra(Intent.EXTRA_EMAIL, mail );
        intent.putExtra(Intent.EXTRA_SUBJECT, "FoodOrder App Final Ordering Details");
        intent.putExtra(Intent.EXTRA_TEXT,body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
