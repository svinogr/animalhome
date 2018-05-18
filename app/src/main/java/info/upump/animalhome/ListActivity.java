package info.upump.animalhome;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.upump.animalhome.db.AnimalDao;
import info.upump.animalhome.entity.Animal;
import info.upump.animalhome.entity.adapter.RecyclerViewAdapter;

public class ListActivity extends AppCompatActivity {

    @BindView(R.id.list_activity_recycler_view)
    RecyclerView  rec_view;

    private RecyclerViewAdapter recyclerViewAdapter;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, ListActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        List<Animal> animals = getAnimal();

        recyclerViewAdapter = new RecyclerViewAdapter(animals);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        rec_view.setLayoutManager(gridLayoutManager);
        rec_view.setAdapter(recyclerViewAdapter);

    }

    @OnClick(R.id.list_activity_fab_back)
    void exit(){
        finish();
    }


    private List<Animal> getAnimal() {
        AnimalDao animalDao = new AnimalDao(this);
        return animalDao.getAll();
    }
}
