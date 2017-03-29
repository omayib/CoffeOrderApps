package us.qisc.coffeorderapps;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button orderButton;
    private EditText nameEditText, totalCupEditText;
    private CheckBox topping1CheckBox, topping2CheckBox, topping3CheckBox;
    private ArrayList<Map<String,String>> orderCollections = new ArrayList<>();
    private ListView orderListView;
    private SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orderButton = (Button) findViewById(R.id.orderButton);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        totalCupEditText = (EditText) findViewById(R.id.totalCupEditText);
        topping1CheckBox = (CheckBox) findViewById(R.id.topingOptions1);
        topping2CheckBox = (CheckBox) findViewById(R.id.topingOptions2);
        topping3CheckBox = (CheckBox) findViewById(R.id.topingOptions3);
        orderListView = (ListView) findViewById(R.id.orderListView);

        simpleAdapter = new SimpleAdapter(this,orderCollections,
                android.R.layout.simple_list_item_2,
                new String[]{"name","totalCups"},
                new int[]{android.R.id.text1,android.R.id.text2});
        orderListView.setAdapter(simpleAdapter);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grabUserInput();
            }
        });

        topping1CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    showConfirmation(compoundButton.getText().toString());
                }
            }
        });

        topping2CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    showConfirmation(compoundButton.getText().toString());
                }
            }
        });

        topping3CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    showConfirmation(compoundButton.getText().toString());
                }
            }
        });
    }

    private void showConfirmation(String textSelected) {
        new AlertDialog.Builder(this)
                .setMessage("Are your sure to order with "+textSelected)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
    }

    private void grabUserInput() {
        String name = nameEditText.getText().toString();
        String totalCups = totalCupEditText.getText().toString();

        Map<String,String> selectedItem = new HashMap<>(2);
        selectedItem.put("name",name);
        selectedItem.put("totalCups",totalCups+" cups ordered");

        orderCollections.add(selectedItem);

        simpleAdapter.notifyDataSetChanged();

        String message = "hi "+name+", you are order "+totalCups+" cups";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
