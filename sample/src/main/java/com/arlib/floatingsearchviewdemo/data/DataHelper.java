package com.arlib.floatingsearchviewdemo.data;

/**
 * Copyright (C) 2015 Ari C.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.widget.Filter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DataHelper {

    private static final String COLORS_FILE_NAME = "line_by_route_info.json";

    private static List<Wrapper> sWrappers = new ArrayList<>();
    // TODO: 2017-05-10 데이터 삽입 
    private static List<Suggestion> sSuggestions =
            new ArrayList<>(Arrays.asList(
                    new Suggestion("green"),
                    new Suggestion("blue"),
                    new Suggestion("pink"),
                    new Suggestion("purple"),
                    new Suggestion("brown"),
                    new Suggestion("gray"),
                    new Suggestion("Granny Smith Apple"),
                    new Suggestion("Indigo"),
                    new Suggestion("Periwinkle"),
                    new Suggestion("Mahogany"),
                    new Suggestion("Maize"),
                    new Suggestion("Mahogany"),
                    new Suggestion("Outer Space"),
                    new Suggestion("Melon"),
                    new Suggestion("Yellow"),
                    new Suggestion("Orange"),
                    new Suggestion("Red"),
                    new Suggestion("Orchid")));

    public interface OnFindColorsListener {
        void onResults(List<Wrapper> results);
    }

    public interface OnFindSuggestionsListener {
        void onResults(List<Suggestion> results);
    }

    public static List<Suggestion> getHistory(Context context, int count) {

        List<Suggestion> suggestionList = new ArrayList<>();
        return suggestionList;
    }

    public static void resetSuggestionsHistory() {
        for (Suggestion suggestion : sSuggestions) {
            suggestion.setIsHistory(false);
        }
    }

    public static void findSuggestions(Context context, String query, final int limit, final long simulatedDelay,
                                       final OnFindSuggestionsListener listener) {
        new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                try {
                    Thread.sleep(simulatedDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                DataHelper.resetSuggestionsHistory();
                List<Suggestion> suggestionList = new ArrayList<>();
                if (!(constraint == null || constraint.length() == 0)) {

                    for (Suggestion suggestion : sSuggestions) {
                        if (suggestion.getBody().toUpperCase()
                                .startsWith(constraint.toString().toUpperCase())) {

                            suggestionList.add(suggestion);
                            if (limit != -1 && suggestionList.size() == limit) {
                                break;
                            }
                        }
                    }
                }

                FilterResults results = new FilterResults();
                Collections.sort(suggestionList, new Comparator<Suggestion>() {
                    @Override
                    public int compare(Suggestion lhs, Suggestion rhs) {
                        return lhs.getIsHistory() ? -1 : 0;
                    }
                });
                results.values = suggestionList;
                results.count = suggestionList.size();

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                if (listener != null) {
                    listener.onResults((List<Suggestion>) results.values);
                }
            }
        }.filter(query);

    }


    public static void findColors(Context context, String query, final OnFindColorsListener listener) {
        initColorWrapperList(context);

        new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {


                List<Wrapper> suggestionList = new ArrayList<>();

                if (!(constraint == null || constraint.length() == 0)) {

                    for (Wrapper color : sWrappers) {
                        if (color.getName().toUpperCase()
                                .startsWith(constraint.toString().toUpperCase())) {

                            suggestionList.add(color);
                        }
                    }

                }

                FilterResults results = new FilterResults();
                results.values = suggestionList;
                results.count = suggestionList.size();

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                if (listener != null) {
                    listener.onResults((List<Wrapper>) results.values);
                }
            }
        }.filter(query);

    }

    private static void initColorWrapperList(Context context) {

        if (sWrappers.isEmpty()) {
            String jsonString = loadJson(context);
            sWrappers = deserializeColors(jsonString);
        }
    }

    private static String loadJson(Context context) {

        String jsonString;

        try {
            InputStream is = context.getAssets().open(COLORS_FILE_NAME);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return jsonString;
    }

    private static List<Wrapper> deserializeColors(String jsonString) {

        Gson gson = new Gson();

        Type collectionType = new TypeToken<List<Wrapper>>() {
        }.getType();
        return gson.fromJson(jsonString, collectionType);
    }

}