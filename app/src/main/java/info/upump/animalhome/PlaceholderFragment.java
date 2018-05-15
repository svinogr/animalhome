package info.upump.animalhome;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import info.upump.animalhome.entity.Animal;

/**
 * Created by explo on 15.05.2018.
 */

public class PlaceholderFragment extends Fragment{
    @BindView(R.id.textView)
    TextView textView;

    private Unbinder unbinder;

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
        unbinder = ButterKnife.bind(this, rootView);
        textView.setText(String.valueOf(animal.getId()));
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle arguments = getArguments();
        int id = arguments.getInt(ID);
        animal = getAnimal(id);
    }

    private Animal getAnimal(int id) {
//        temporaru
        Animal animal = new Animal();
        animal.setId(id);
        return animal;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
