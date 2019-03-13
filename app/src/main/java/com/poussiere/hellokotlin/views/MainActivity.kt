
/*Copyright (C) <2018>  <Nicolas BOUTIN>
        This program is free software: you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation, either version 3 of the License, or
        (at your option) any later version.
        This program is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        GNU General Public License for more details.
        You should have received a copy of the GNU General Public License
        along with this program.  If not, see <http://www.gnu.org/licenses/
        contact us : von.linnasta@gmail.com
*/

package com.poussiere.hellokotlin.views

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import com.poussiere.hellokotlin.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    companion object {
        val SHAREDPREFERENCES_PLAYERS_KEY: String = "player_number"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        main_text_view.setText(R.string.main_text_view_content)


        main_text_view.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            //  player_nb.setText("")
            var options: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity,
                    main_text_view,
                    ViewCompat.getTransitionName(main_text_view));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                startActivity(intent, options.toBundle())
            }; else startActivity(intent)


        }
        var prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        var playerNumber: Int = prefs.getInt(SHAREDPREFERENCES_PLAYERS_KEY, 1)

        when (playerNumber) {
            1 -> player_nb.setText(R.string.un_joueur)
            2 -> player_nb.setText(R.string.deux_joueurs)
        }


        player_nb.setOnClickListener() {
            playerNumber = prefs.getInt(SHAREDPREFERENCES_PLAYERS_KEY, 1)

            if (playerNumber == 1) {

                player_nb.setText(R.string.deux_joueurs)
                prefs.edit().putInt(SHAREDPREFERENCES_PLAYERS_KEY, 2).apply()
            } else if (playerNumber == 2) {
                player_nb.setText(R.string.un_joueur)
                prefs.edit().putInt(SHAREDPREFERENCES_PLAYERS_KEY, 1).apply()
            }
        }

    }

    override fun onResume() {
        super.onResume()

    }

}