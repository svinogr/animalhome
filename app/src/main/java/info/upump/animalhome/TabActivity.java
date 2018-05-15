package info.upump.animalhome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.upump.animalhome.entity.Animal;
import info.upump.animalhome.entity.adapter.SectionsPagerAdapter;

public class TabActivity extends AppCompatActivity {
    @BindView(R.id.tab_activity_view_pager)
    ViewPager mViewPager;

/*    @BindView(R.id.fab_voice)
    FloatingActionButton fabVoice;*/

    private List<Animal> animals = new ArrayList<>();

    public static Intent createInstance(Context context) {
        Intent intent = new Intent(context, TabActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        ButterKnife.bind(this);
        animals = getAnimal();
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), animals);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    @OnClick({R.id.fab_voice})
    void onClick(FloatingActionButton fab) {
        switch (fab.getId()) {
            case R.id.fab_voice:
//                intent = TabActivity.createInstance(this);
                Toast.makeText(this, "fab_voice"+animals.get(mViewPager.getCurrentItem()).getName(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab_info_voice:
//                intent = GameActivity.createInstance(this);
                Toast.makeText(this, "fab_info_voice", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab_back:
//                intent = ListActivity.createIntent(this);
                Toast.makeText(this, "fab_back", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private List<Animal> getAnimal() {
//        temporary
        List<Animal> animals = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Animal animal = new Animal();
            animal.setName("Кошка N" + i);
            animal.setId(i);
            animals.add(animal);
        }
        return animals;
    }
}
