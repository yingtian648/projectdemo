package mine.demo1.expandtab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mine.demo1.R;

public class ExpandTabDemoActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.file_linear)
    LinearLayout fileLinear;
    private int y = 0;
    @BindView(R.id.desc)
    TextView desc;
    @BindView(R.id.layout_only_recyclerview)
    RecyclerView rvs;
    @BindView(R.id.activefile_rv)
    RecyclerView rvf;
    //    @BindView(R.id.linear)
//    RadioGroup radioGroup;
    boolean isdescShow = false, isSShow = false, isFShow = false;
    @BindView(R.id.desc_t)
    CheckBox descT;
    @BindView(R.id.actives_t)
    CheckBox activesT;
    @BindView(R.id.activefile_t)
    CheckBox activefileT;
    List<String> dataList, dataList1;
    private LinearLayoutManager manager;
    private int lastVisibleItem = 0, lastVisibleItem1 = 0;
    private ExapandTabAdapter adapter;
    private GridLayoutManager manager1;
    private ExapandTabAdapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        descT.setOnClickListener(this);
        activesT.setOnClickListener(this);
        activefileT.setOnClickListener(this);
        dataList = new ArrayList<>();
        dataList1 = new ArrayList<>();
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        manager1 = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        adapter = new ExapandTabAdapter(this, dataList, android.R.layout.simple_list_item_1);
        adapter1 = new ExapandTabAdapter(this, dataList1, android.R.layout.simple_list_item_1);
        rvs.setLayoutManager(manager);
        rvf.setLayoutManager(manager1);
        rvs.setAdapter(adapter);
        rvf.setAdapter(adapter1);
        //添加recylerView的监听
        rvs.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == adapter.getItemCount()
                        && adapter.ismShowFooter()) {
                    //加载更多
                    loadData();
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = manager.findLastVisibleItemPosition();
            }
        });
        loadData();
        rvf.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem1 + 1 == adapter1.getItemCount()
                        && adapter1.ismShowFooter() && y < 3) {
                    //加载更多
                    loadData1();
                    y++;
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem1 = manager.findLastVisibleItemPosition();
            }
        });
        loadData1();
    }

    private void loadData1() {
        for (int i = 0; i < 10; i++) {
            dataList1.add("场次:" + i);
        }
        adapter1.setmShowFooter(true);
        adapter1.notifyDataSetChanged();
    }

    private void loadData() {
        for (int i = 0; i < 10; i++) {
            dataList.add("附件:" + i);
        }
        adapter.setmShowFooter(true);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.desc_t:
                if (isdescShow) {
                    desc.setVisibility(View.GONE);
                    isdescShow = false;
                    descT.setChecked(false);
                } else {
                    desc.setVisibility(View.VISIBLE);
                    isdescShow = true;
                }
                rvs.setVisibility(View.GONE);
                rvf.setVisibility(View.GONE);
                activesT.setChecked(false);
                activefileT.setChecked(false);
                break;
            case R.id.actives_t:
                if (isSShow) {
                    rvs.setVisibility(View.GONE);
                    isSShow = false;
                    activesT.setChecked(false);
                } else {
                    rvs.setVisibility(View.VISIBLE);
                    isSShow = true;
                }
                rvs.setVisibility(View.VISIBLE);
                desc.setVisibility(View.GONE);
                rvf.setVisibility(View.GONE);
                descT.setChecked(false);
                activefileT.setChecked(false);
                break;
            case R.id.activefile_t:
                if (isFShow) {
                    isFShow = false;
                    activefileT.setChecked(false);
                    fileLinear.setVisibility(View.GONE);
                } else {
                    fileLinear.setVisibility(View.VISIBLE);
                    isFShow = true;
                }
                desc.setVisibility(View.GONE);
                rvs.setVisibility(View.GONE);
                rvf.setVisibility(View.VISIBLE);
                descT.setChecked(false);
                activesT.setChecked(false);
                break;
        }
    }
}
