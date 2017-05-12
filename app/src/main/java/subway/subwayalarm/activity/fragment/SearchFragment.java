package subway.subwayalarm.activity.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import subway.subwayalarm.R;
import subway.subwayalarm.adapter.SearchHistoryAdapter;
import subway.subwayalarm.data.SearchHistoryItem;


public class SearchFragment extends Fragment {

    List<SearchHistoryItem> items;
    EditText etSearch;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        etSearch = (EditText) view.findViewById(R.id.search_view);

        InputStream is = getResources().openRawResource(R.raw.line_by_route_info);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int ctr;
        try {
            ctr = is.read();
            while (ctr != -1) {
                byteArrayOutputStream.write(ctr);
                ctr = is.read();
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("ddddddddddddddd", byteArrayOutputStream.toString());

        items = new ArrayList<>();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        setSearchHistory();

        recyclerView.setAdapter(new SearchHistoryAdapter(getContext(), items, R.layout.fragment_search));



        return view;
    }

    private void setSearchHistory() {
        SearchHistoryItem[] item = new SearchHistoryItem[2];

        item[0] = new SearchHistoryItem(R.drawable.bookmark_off_btn, "test1", "test1");
        item[1] = new SearchHistoryItem(R.drawable.bookmark_off_btn, "test2", "test2");

        // TODO: 2017-03-29 최근검색어 삽입

        for (int i = 0; i < item.length; i++) {
            items.add(item[i]);
        }
    }
}