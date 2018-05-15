package info.upump.animalhome.entity.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import info.upump.animalhome.PlaceholderFragment;
import info.upump.animalhome.entity.Animal;

/**
 * Created by explo on 15.05.2018.
 */

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
    private List<Animal> animals;

    public SectionsPagerAdapter(FragmentManager fm, List<Animal> animals) {
        super(fm);
        this.animals = animals;
    }

    @Override
    public Fragment getItem(int position) {
        return  PlaceholderFragment.newInstance(animals.get(position));
    }

    @Override
    public int getCount() {
        return animals.size();
    }
}
