package info.upump.animalhome;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

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
    @BindView(R.id.fragment_tab_image_view)
    ImageView imageView;

    private Unbinder unbinder;

    private static final String IMAGE = "image";
    private static final String NAME = "name";

    private Animal animal;

    public static PlaceholderFragment newInstance(Animal animal) {
        Bundle bundle = new Bundle();
        bundle.putString(IMAGE, animal.getImage());
        bundle.putString(NAME, animal.getImage());
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
        setImage();
//        textView.setText(String.valueOf(animal.getName()));
        return rootView;
    }

    private void setImage() {
        RequestOptions options = new RequestOptions()
//                .transforms(new RoundedCorners(50))
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .priority(Priority.HIGH);
        int identificator = getContext().getResources().getIdentifier(animal.getImage(), "drawable", getContext().getPackageName());

        Glide.with(getContext()).load(identificator).apply(options).into(imageView);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle arguments = getArguments();
        animal = getAnimal(arguments);
    }

    private Animal getAnimal(Bundle arguments) {
        String image = arguments.getString(IMAGE);
        String name = arguments.getString(NAME);
        Animal animal = new Animal();
        animal.setImage(image);
        animal.setName(name);
        return animal;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
