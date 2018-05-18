package info.upump.animalhome;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.upump.animalhome.db.AnimalDao;
import info.upump.animalhome.entity.Animal;
import info.upump.animalhome.entity.adapter.SectionsPagerAdapter;

public class TabActivity extends AppCompatActivity {
    @BindView(R.id.tab_activity_view_pager)
    ViewPager mViewPager;

    @BindView(R.id.fab_voice)
    FloatingActionButton fabVoice;

    private static String POSITION = "position";

    private String[] soundAnimal;
    private String[] soundAuthor;
    private String nameTrack;
    private MediaPlayer mediaPlayer;
    private AssetManager assetManager;

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

        createMediaPlayer();
        assetManager = getAssets();
        createSoundsAnimal();
        createSoundsAuthor();
    }

    private void createMediaPlayer() {
        mediaPlayer = new MediaPlayer();
    }

    private void createSoundsAnimal() {
        soundAnimal = new String[animalList.size()];
        for (int i = 0; i < animalList.size(); i++) {
            soundAnimal[i] = (animalList.get(i).getSoundAnimal());
        }
    }

    private void createSoundsAuthor() {
        soundAuthor = new String[animalList.size()];
        for (int i = 0; i < animalList.size(); i++) {
            soundAuthor[i] = animalList.get(i).getSoundAuthor();
        }
    }

    public void playSoundWithMP(String fileName) {
        AssetFileDescriptor descriptor = null;
        try {
            descriptor = assetManager.openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long start = descriptor.getStartOffset();
        long end = descriptor.getLength();

        try {
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), start, end);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.start();
    }


    @OnClick({R.id.fab_voice, R.id.fab_info_voice, R.id.fab_back})
    void onClick(FloatingActionButton fab) {
        switch (fab.getId()) {
            case R.id.fab_voice:
                if (mediaPlayer.isPlaying()) {
                    if (nameTrack.equals(soundAnimal[mViewPager.getCurrentItem()])) {
                        mediaPlayer.reset();
                        nameTrack = null;
                    } else {
                        mediaPlayer.reset();
                        nameTrack = soundAnimal[mViewPager.getCurrentItem()];
                        playSoundWithMP(nameTrack);
                    }
                } else {
                    nameTrack = soundAnimal[mViewPager.getCurrentItem()];
                    playSoundWithMP(nameTrack);
                }
                break;
            case R.id.fab_info_voice:
                if (mediaPlayer.isPlaying()) {
                    System.out.println("1 " + nameTrack);
                    System.out.println("2 " + mViewPager.getCurrentItem());
                    if (nameTrack.equals(soundAuthor[mViewPager.getCurrentItem()])) {
                        mediaPlayer.reset();
                        nameTrack = null;
                        System.out.println(1);
                    } else {
                        System.out.println(2);
                        mediaPlayer.reset();
                        nameTrack = soundAuthor[mViewPager.getCurrentItem()];
                        playSoundWithMP(nameTrack);
                    }
                } else {
                    nameTrack = soundAuthor[mViewPager.getCurrentItem()];
                    playSoundWithMP(nameTrack);
                    System.out.println(3);
                }
                break;
            case R.id.fab_back:
                finish();
                break;
        }
    }

    private List<Animal> getAnimal() {
//        temporary
        AnimalDao animalDao = new AnimalDao(this);
        return animalDao.getAll();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
