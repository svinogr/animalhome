package info.upump.animalhome;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.IOException;
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

    private static String POSITION = "position";

    private int[] soundAnimal;
    private int[] soundAuthor;

    private SoundPool soundPool;
    private AssetManager assetManager;
    private int mStreamID;
    private int sound;

    private List<Animal> animalList = new ArrayList<>();

    public static Intent createInstance(Context context, int position) {
        Intent intent = new Intent(context, TabActivity.class);
        intent.putExtra(POSITION, position);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        ButterKnife.bind(this);
        animalList = getAnimal();
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), animalList);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        int position = getIntent().getIntExtra(POSITION, 0);

        mViewPager.setCurrentItem(position);

        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // Для устройств до Android 5
            createOldSoundPool();
        } else {
            // Для новых устройств
            createNewSoundPool();
        }

        assetManager = getAssets();
        createSoundsAnimal();
        createSoundsAuthor();

        sound = loadSound("m.mp3");

    }

    private void createSoundsAuthor() {
        soundAnimal = new int[animalList.size()];
       for (int i = 0; i< animalList.size(); i++){
           soundAnimal[i] = loadSound(animalList.get(i).getSoundAnimal());
       }
    }

    private void createSoundsAnimal() {
        soundAuthor = new int[animalList.size()];
        for (int i = 0; i< animalList.size(); i++){
            soundAuthor[i] = loadSound(animalList.get(i).getSoundAuthor());
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void createNewSoundPool() {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
    }

    @SuppressWarnings("deprecation")
    private void createOldSoundPool() {
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
    }

    private int loadSound(String fileName) {
        AssetFileDescriptor afd;
        try {
            afd = assetManager.openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Не могу загрузить файл " + fileName,
                    Toast.LENGTH_SHORT).show();
            return -1;
        }
        return soundPool.load(afd, 1);
    }

    private int playSound(int sound) {
        if (sound > 0) {
            mStreamID = soundPool.play(sound, 1, 1, 1, 0, 1);
        }
        return mStreamID;
    }


    @OnClick({R.id.fab_voice, R.id.fab_info_voice, R.id.fab_back})
    void onClick(FloatingActionButton fab) {
        switch (fab.getId()) {
            case R.id.fab_voice:
//                intent = TabActivity.createInstance(this);
                Toast.makeText(this, "fab_voice" + animalList.get(mViewPager.getCurrentItem()).getName(), Toast.LENGTH_SHORT).show();
                playSound(soundAnimal[mViewPager.getCurrentItem()]);
                break;
            case R.id.fab_info_voice:
//                intent = GameActivity.createInstance(this);
                Toast.makeText(this, "fab_info_voice", Toast.LENGTH_SHORT).show();
                playSound(soundAuthor[mViewPager.getCurrentItem()]);
                break;
            case R.id.fab_back:
                finish();
                break;
        }
    }

    private List<Animal> getAnimal() {
//        temporary
        List<Animal> animals = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Animal animal = new Animal();
            animal.setName("Кошка N" + i);
            animal.setId(i);
            animal.setSoundAnimal(i+1+".mp3");
            animal.setSoundAuthor(i+1+".mp3");
            animal.setImage("a1");
            animals.add(animal);
        }
        return animals;
    }
}
