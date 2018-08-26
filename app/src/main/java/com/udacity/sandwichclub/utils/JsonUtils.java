package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String UTIL_TAG = JsonUtils.class.getSimpleName();

    public static Sandwich parseSandwichJson(String json) {
        final Sandwich sandwich = new Sandwich();
        List<String> differentNames = new ArrayList<>();
        List<String> ingredients = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            sandwich.setImage(jsonObject.getString("image"));
            String name = jsonObject.getString("name");
            JSONObject nameOfSandwich = new JSONObject(name);
            String mainName = nameOfSandwich.getString("mainName");
            JSONArray jsonArray = nameOfSandwich.getJSONArray("alsoKnownAs");
            JSONArray ingrediantsArray = jsonObject.getJSONArray("ingredients");
            sandwich.setMainName(mainName);
            for(int i = 0; i < jsonArray.length(); i++) {
                differentNames.add(jsonArray.getString(i));
            }

            for(int i = 0; i < ingrediantsArray.length(); i++) {
                ingredients.add(ingrediantsArray.getString(i));
            }
            sandwich.setAlsoKnownAs(differentNames);
            sandwich.setDescription(jsonObject.getString("description"));
            sandwich.setPlaceOfOrigin(jsonObject.getString("placeOfOrigin"));
            sandwich.setIngredients(ingredients);
            Log.i(UTIL_TAG, sandwich.toString());
        } catch (final JSONException e) {
            Log.e(UTIL_TAG, "parseSandwichJson: "+ e.getLocalizedMessage());
        }

        return sandwich;
    }
}
