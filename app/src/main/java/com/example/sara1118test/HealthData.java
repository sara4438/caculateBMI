package com.example.sara1118test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HealthData {
    private String name;
    private double height;
    private double weight;
    private double bmi;

    public HealthData(String a, double height, double weight){
        this.name = a;
        this.height = height;
        this.weight = weight;
        this.bmi = Math.round((weight/ Math.pow(height/100, 2))*10/10);
    }
    public String getName(){
        return name;
    }
    public double getHeight(){
        return height;
    }
    public double getWeight(){
        return weight;
    }
    public double getBmi(){
        return bmi;
    }
    public String toString(){
        String str ="";
        str = "姓名: " + getName() + "           " + "身高: " + height +"           " + "體重: " + weight +"           "+ "BMI: "+bmi+"           ";
        return str;
    }
    public JSONObject getJsonObject() throws JSONException {
        JSONObject jsonObject= new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("height", height);
        jsonObject.put("weight", weight);
        jsonObject.put("bmi", bmi);
        return jsonObject;
    }
    public static HealthData genByJsonObject (JSONObject jsonObject) throws JSONException{
        String name = jsonObject.getString("name");
        double height = jsonObject.getDouble("height");
        double weight = jsonObject.getDouble("weight");
        double bmi =jsonObject.getDouble("bmi");
        HealthData healthData = new HealthData(name, height, weight);
        return healthData;
    }


}
