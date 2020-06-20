package com.example.franklin.hudumu.services.shopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.franklin.hudumu.R;
import com.example.franklin.hudumu.adapters.HudumaShoppingAdapter;
import com.example.franklin.hudumu.models.HudumaModel;

import java.util.ArrayList;

public class Shopping extends AppCompatActivity {

    ArrayList<HudumaModel> arrayList;
    ListView mListView;
    private HudumaShoppingAdapter hudumaShoppingAdapter;
    private Button mButtonSubtotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        mListView = (ListView) findViewById(R.id.listViewShopping);
        mButtonSubtotal = (Button)findViewById(R.id.sumShopItems);

        mButtonSubtotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int total = 0;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Selected Items");


            }
        });

        arrayList = new ArrayList<>();

        arrayList.add(new HudumaModel("Maize Flour 2KG: Ksh. 125.00", false));
        arrayList.add(new HudumaModel("Wheat Flour 2KG: Ksh. 135.00", false));
        arrayList.add(new HudumaModel("Sugar 2KG: Ksh. 180.00", false));
        arrayList.add(new HudumaModel("Salt 2KG: Ksh. 100.00", false));
        arrayList.add(new HudumaModel("Rice 2KG: Ksh. 200.00", false));
        arrayList.add(new HudumaModel("Bread 600G: Ksh. 67.00", false));
        arrayList.add(new HudumaModel("Blueband 2KG: Ksh. 225.00", false));
        arrayList.add(new HudumaModel("Tissues 10Rolls: Ksh. 125.00", false));
        arrayList.add(new HudumaModel("Geisha soaps 3Pieces: Ksh. 155.00", false));
        arrayList.add(new HudumaModel("Washing bar soap : Ksh. 80.00", false));
        arrayList.add(new HudumaModel("Colgate 100ML: Ksh. 225.00", false));
        arrayList.add(new HudumaModel("Juice 2 LTR: Ksh. 245.00", false));
        arrayList.add(new HudumaModel("Yoghurt 1 LTR: Ksh. 100.00", false));
        arrayList.add(new HudumaModel("Coffee Medium: Ksh. 125.00", false));
        arrayList.add(new HudumaModel("Tea bags 100 PCS: Ksh. 145.00", false));
        arrayList.add(new HudumaModel("Porridge Four 2KG: Ksh. 185.00", false));
        arrayList.add(new HudumaModel("Jam 1KG: Ksh. 225.00", false));

        hudumaShoppingAdapter = new HudumaShoppingAdapter(arrayList, getApplicationContext());

        mListView.setAdapter(hudumaShoppingAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HudumaModel hudumaModel = arrayList.get(i);
                hudumaModel.checked = !hudumaModel.checked;
                hudumaShoppingAdapter.notifyDataSetChanged();

                int total = 0;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Selected Items");

                /*switch (i){
                    case 0:
                        stringBuilder.append("\nMaize flour: 125.00");
                        total +=125;
                        break;
                    case 1:
                        stringBuilder.append("\nWheat flour: 135.00");
                        total += 135;
                        break;
                }*/

                if (i==0){
                    stringBuilder.append("\nMaize flour: 125.00");
                    total +=125;

                } else if (i==1){
                    stringBuilder.append("\nMaize flour: 135.00");
                    total +=135;

                }

                stringBuilder.append("\nSubtotal: Ksh: " + total);
                Toast.makeText(Shopping.this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void calculateSubtotals(){

    }
}
