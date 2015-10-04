/**
 * Copyright 2015 John Slevinsky
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ca.ualberta.slevinsk.gameshow;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.NumberPicker;

public class GameshowBuzzerActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    private Integer numberOfPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameshow_buzzer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        BuzzerCounterManager.initManager(getApplicationContext());

        NumberPickerFragment n = new NumberPickerFragment();
        n.show(getSupportFragmentManager(), "numberPicker");

        setNumberOfPlayers(2);

    }

    public void setNumberOfPlayers(Integer numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag("myFragmentTag");


        FragmentTransaction ft = fm.beginTransaction();
        fragment = new GameshowButtonsFragment();
        Bundle b = new Bundle();
        b.putInt("numberOfPlayers", numberOfPlayers);

        fragment.setArguments(b);

        ft.replace(R.id.fgContainer, fragment, "myFragmentTag");
        ft.commit();

    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        setNumberOfPlayers(newVal);
    }

}
//http://stackoverflow.com/questions/14439941/passing-data-between-fragments-to-activity
