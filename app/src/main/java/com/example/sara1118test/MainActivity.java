package com.example.sara1118test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etHeight;
    private EditText etWeight;
    private Button btnCaculate;
    private TextView tvResult;
    private JSONArray dataJsonArray;
    private JSONObject jsonObject;
    private ArrayList<HealthData> healthDats;
    private TextView tvName;
    private TextView tvHeight;
    private TextView tvWeight;
    private TextView tvBmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setBtnCaculate();
    }

    public void init(){
        etName = findViewById(R.id.main_et_name);
        etHeight = findViewById(R.id.main_et_height);
        etWeight = findViewById(R.id.main_et_weight);
        btnCaculate = findViewById(R.id.main_btn_caculate);
        tvName = findViewById(R.id.main_tv_name);
        tvHeight = findViewById(R.id.main_tv_height);
        tvWeight = findViewById(R.id.main_tv_weight);
        tvBmi = findViewById(R.id.main_tv_bmi);
        healthDats = new ArrayList<>();
        dataJsonArray = new JSONArray();
    }
    public void setBtnCaculate(){
        btnCaculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etName.getText().toString().equals("") || etHeight.getText().toString().equals("") || etWeight.getText().toString().equals("")){
                    showToast(R.string.main_msg_notComplete);
                }else{
                    String name = etName.getText().toString();
                    double height = Double.parseDouble(etHeight.getText().toString());
                    double weight = Double.parseDouble(etWeight.getText().toString());

                    HealthData healthData = new HealthData(name, height, weight);
                    storeToJsonObject(healthData);
                    healthDats = getFromJsonObject();

                    String nameStr = "";
                    String heightStr = "";
                    String weightStr = "";
                    String bmiStr = "";
                    for (int i = 0; i < healthDats.size(); i++) {
                        nameStr += "姓名: " + healthDats.get(i).getName() + "\n";
                        heightStr += " 身高: " + healthDats.get(i).getHeight() + "\n";
                        weightStr += "體重: " + healthDats.get(i).getWeight() + "\n";
                        bmiStr += "BMI: " + healthDats.get(i).getBmi() + "\n";
                    }
                    tvName.setText(nameStr);
                    tvHeight.setText(heightStr);
                    tvWeight.setText(weightStr);
                    tvBmi.setText(bmiStr);
                    etName.setText("");
                    etHeight.setText("");
                    etWeight.setText("");
                }
            }
        });
    }
    private void showToast(int msgId){
        Toast.makeText(this, msgId, Toast.LENGTH_LONG).show();
    }
    public JSONObject storeToJsonObject(HealthData healthData){
        try{
            jsonObject = healthData.getJsonObject();
        }catch(JSONException e){
            e.printStackTrace();
        }
        dataJsonArray.put(jsonObject);
        return jsonObject;
    }
    public ArrayList<HealthData> getFromJsonObject(){
        try{
            healthDats.add(HealthData.genByJsonObject(jsonObject));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return healthDats;
    }




}
