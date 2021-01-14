/*
 * MIT License
 *
 * Copyright (c) 2020 - 2021 Bernard Longho
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.blongho.countrydata;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.blongho.countrydata.viewmodel.CountryViewModel;
import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

  private AppBarConfiguration appBarConfiguration;
  private NavController navController;

  private static Locale getLocale() {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
      return Resources.getSystem().getConfiguration().getLocales().get(0);
    } else {
      return Resources.getSystem().getConfiguration().locale;
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

// Instantiate the view model here
// This initializes World and starts the filtering in the background
// See CountryViewModel.class
    new ViewModelProvider(MainActivity.this)
        .get(CountryViewModel.class);
    appBarConfiguration = new AppBarConfiguration.Builder(
        R.id.navigation_country_list_fragment)
        .build();
    navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

    if (Build.VERSION.SDK_INT >= 21) {
      getWindow()
          .setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary)); // Navigation
      // bar the soft bottom of some phones like nexus and some Samsung note series
      getWindow()
          .setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary)); //status bar or
      // the time bar at the top
    }


  }

  @Override
  public boolean onSupportNavigateUp() {
    navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    return NavigationUI.navigateUp(navController, appBarConfiguration)
        || super.onSupportNavigateUp();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    if (navController.getCurrentDestination().getId()
        == R.id.navigation_country_list_fragment
    ) {
      getMenuInflater().inflate(R.menu.menu_main, menu);
    }
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      NavHostFragment.findNavController(getSupportFragmentManager().getFragments().get(0))
          .navigate(R.id.action_global_navigation_about_fragment);
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  public static final class AppUtils {

    public static String formatNumber(final double number) {
      NumberFormat numberFormat = NumberFormat.getNumberInstance();
      numberFormat.setMaximumFractionDigits(2);
      numberFormat.setGroupingUsed(true);
      return numberFormat.format(number);
    }

    public static String formatNumber(final long number) {

      NumberFormat numberFormat = NumberFormat.getNumberInstance(getLocale());
      numberFormat.setMaximumFractionDigits(2);
      numberFormat.setGroupingUsed(true);
      return numberFormat.format(number);
    }
  }

}
