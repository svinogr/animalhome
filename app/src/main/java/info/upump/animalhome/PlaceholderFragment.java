package info.upump.animalhome;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.upump.animalhome.entity.Animal;

/**
 * Created by explo on 15.05.2018.
 */

public class PlaceholderFragment extends Fragment {
    private static final String ID = "id";

    private Animal animal;

    public static PlaceholderFragment newInstance(Animal animal) {
        Bundle bundle = new Bundle();
        bundle.putInt(ID, animal.getId());
        PlaceholderFragment placeholderFragment = new PlaceholderFragment();
        placeholderFragment.setArguments(bundle);
        return placeholderFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
