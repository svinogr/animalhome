package info.upump.animalhome.entity.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import info.upump.animalhome.R;
import info.upump.animalhome.entity.Animal;

/**
 * Created by explo on 15.05.2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<AnimalViewHolder> {
    private List<Animal> animalsList;

    public RecyclerViewAdapter(List<Animal> animalsList) {
        this.animalsList = animalsList;
    }

    @Override
    public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_layout, parent, false);
        return new AnimalViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(AnimalViewHolder holder, int position) {
        holder.bind(animalsList.get(position));

    }

    @Override
    public int getItemCount() {
        return animalsList.size();
    }
}
