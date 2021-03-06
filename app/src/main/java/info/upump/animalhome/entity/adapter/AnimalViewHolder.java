package info.upump.animalhome.entity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.upump.animalhome.R;
import info.upump.animalhome.TabActivity;
import info.upump.animalhome.entity.Animal;


class AnimalViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.item_card_layout_image_view)
    ImageView imageView;

    @BindView(R.id.item_card_layout_text)
    TextView textView;

    private Context context;
    private Animal animal;


    public AnimalViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        context = itemView.getContext();
    }

    public void bind(Animal animal) {
        this.animal = animal;
        RequestOptions options = new RequestOptions()
                .transforms(new GlideCircleTransformation(context))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .priority(Priority.HIGH);
        int identificator = context.getResources().getIdentifier(animal.getImagePrev(), "drawable", context.getPackageName());

        Glide.with(itemView.getContext()).load(identificator).apply(options).into(imageView);
        textView.setText(animal.getName());
    }

    @OnClick()
    void onClick(View view){
        Intent intent = TabActivity.createInstance(context, getAdapterPosition());
        context.startActivity(intent);
    }
}
