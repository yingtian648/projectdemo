package mine.demo1.pulltorefresh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import mine.demo1.R;

public class PullToRefreshActivity extends AppCompatActivity {
    private List<String> list;
    private RecyclerView recyclerView;
    private RecyAdapter adapter;
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh);
        recyclerView = (RecyclerView) findViewById(R.id.recy);
        initData();
        setPullLayout();
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("列表项"+i);
        }
        adapter = new RecyAdapter(list,this);
        manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }

    private void setPullLayout() {

    }

    private void loadMore(){

    }
}
