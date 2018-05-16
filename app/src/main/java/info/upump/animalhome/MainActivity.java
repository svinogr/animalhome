package info.upump.animalhome;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.fab_pager)
    FloatingActionButton fabPager;

    @BindView(R.id.fab_game)
    FloatingActionButton fabGame;

    @BindView(R.id.fab_list)
    FloatingActionButton fabList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.fab_pager, R.id.fab_game, R.id.fab_list})
    void OnClick(FloatingActionButton fab) {
        Intent intent = null;
        switch (fab.getId()) {
            case R.id.fab_pager:
                intent = TabActivity.createInstance(this, 0);
                Toast.makeText(this, "fab_pager", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab_game:
                intent = GameActivity.createInstance(this);
                Toast.makeText(this, "fab_game", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab_list:
                intent = ListActivity.createIntent(this);
                Toast.makeText(this, "fab_list", Toast.LENGTH_SHORT).show();
                break;
        }
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
