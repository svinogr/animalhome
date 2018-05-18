package info.upump.animalhome;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.upump.animalhome.db.AnimalDao;
import info.upump.animalhome.entity.Animal;

public class GameActivity extends AppCompatActivity {

    @BindView(R.id.game_image_view)
    ImageView imageView;

    @BindView(R.id.game_fab_reset)
    FloatingActionButton fabReset;

    @BindView(R.id.game_fab_one)
    FloatingActionButton fabOne;

    @BindView(R.id.game_fab_two)
    FloatingActionButton fabTwo;


    @BindView(R.id.game_fab_three)
    FloatingActionButton fabThree;


    @BindView(R.id.game_fab_four)
    FloatingActionButton fabFour;

    private MediaPlayer mediaPlayer;

    private List<Animal> animalList = new ArrayList<>();
    private Animal rightAnimal;
    private String[] animalVoice;
    private String[] greetings = new String[]{"1.mp3"};
    private String[] baddings = new String[]{"2.mp3"};
    private int currentVoice;
    private String question;
    private String pause = "pause.mp3";

    private String[] voices = new String[10];

    private AssetManager assetManager;


    public static Intent createInstance(Context context) {
        Intent intent = new Intent(context, GameActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ButterKnife.bind(this);

        assetManager = getAssets();
        mediaPlayer = new MediaPlayer();
        start();


    }

    private void start() {
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                nextVoice();
            }
        });
        currentVoice = 0;
        initFourAnimal();
        setRightAnimal();
        setImage();
        initVoices();
        playQuestion(voices[0]);

    }

    private void setImage() {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .priority(Priority.HIGH);
        int identificator = getResources().getIdentifier(rightAnimal.getImage(), "drawable", getPackageName());

        Glide.with(this).load(identificator).apply(options).into(imageView);
    }

    private void initVoices() {
        voices[0] = question;
        voices[1] = pause;
        for (int i = 2, j = 0; i < voices.length; i++, j++) {
            voices[i] = animalVoice[j];
            i++;
            voices[i] = pause;
        }

    }

    private void nextVoice() {
        currentVoice++;
        if (currentVoice != 10) {

            playSoundWithMP(voices[currentVoice]);
            switch (currentVoice) {
                case 2:
                    fabOne.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    fabTwo.setVisibility(View.VISIBLE);
                    break;
                case 6:
                    fabThree.setVisibility(View.VISIBLE);
                    break;
                case 8:
                    fabFour.setVisibility(View.VISIBLE);
                    break;

            }
        } else return;

    }

    private void setRightAnimal() {
        rightAnimal = animalList.get(getRandom(3));
//        question = rightAnimal.getQuestion();
        question = "2.mp3";
    }

    private void playQuestion(String string) {
        playSoundWithMP(string);
    }

    private void playAnswer(String string) {
        playSoundWithMP("1.mp3");

    }

    private int getRandom(int max) {
        int random = (int) (Math.random() * ++max);
        return random;
    }

    private void initFourAnimal() {
        animalList = new ArrayList<>();
        AnimalDao animalDao = new AnimalDao(this);
        animalList = animalDao.randomFour();
        System.out.println(animalList);
        animalVoice = new String[animalList.size()];
        for (int i = 0; i < animalVoice.length; i++) {
            animalVoice[i] = animalList.get(i).getSoundAnimal();
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
            mediaPlayer.reset();
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), start, end);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }


    @OnClick({R.id.game_fab_one, R.id.game_fab_two, R.id.game_fab_three, R.id.game_fab_four, R.id.game_fab_reset, R.id.game_fab_back})
    void onClick(FloatingActionButton fab) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.reset();
        }
        switch (fab.getId()) {
            case R.id.game_fab_one:
                checkAnswerOut(0);
                break;
            case R.id.game_fab_two:
                checkAnswerOut(1);
                break;
            case R.id.game_fab_three:
                checkAnswerOut(2);
                break;
            case R.id.game_fab_four:
                checkAnswerOut(3);
                break;
            case R.id.game_fab_reset:
                restart();
                break;
            case R.id.game_fab_back:
                finish();
                break;
        }
    }

    private void baddings() {
        setBtnVisible();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                fabReset.setVisibility(View.VISIBLE);
            }
        });
        playSoundWithMP(baddings[getRandom(baddings.length - 1)]);
    }

    private void greetings() {
        setBtnVisible();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                fabReset.setVisibility(View.VISIBLE);
            }
        });
        playSoundWithMP(greetings[getRandom(greetings.length - 1)]);
    }

    private void setBtnVisible() {
        fabOne.setVisibility(View.GONE);
        fabTwo.setVisibility(View.GONE);
        fabThree.setVisibility(View.GONE);
        fabFour.setVisibility(View.GONE);
    }

    private void restart() {
        fabReset.setVisibility(View.GONE);
        start();
    }

    private void checkAnswerOut(int answer) {
        if (animalList.get(answer) == rightAnimal) {
            greetings();
        } else baddings();
    }
}
