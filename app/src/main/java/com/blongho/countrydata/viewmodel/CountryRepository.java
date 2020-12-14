/*
 * MIT License
 *
 * Copyright (c) 2020 Bernard Longho
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

package com.blongho.countrydata.viewmodel;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.annimon.stream.Stream;
import com.blongho.country_data.Country;
import com.blongho.country_data.World;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * @author Bernard Longho
 * @since 2020-05-30
 * <p>This class plays the role of a database repository. It initializes World and loads the
 * data of interest(countries)</p>
 * <b><p>After this project, some pitfalls has been identified in the library see
 * #setCountries</p></b>
 */
class CountryRepository {

  private static final String TAG = "CountryRepository";
  private final static MutableLiveData<List<Country>> countries = new MutableLiveData<>();
  private final static boolean debug = false;

  CountryRepository(Context context) {
    World.init(context);
    Executors.newCachedThreadPool().submit(CountryRepository::setCountries);
  }


  LiveData<List<Country>> getAllCountries() {
    return countries;
  }

  /**
   * After 'playing' around, we noticed that some country details are not complete. We filter them
   * here
   */
  private static void setCountries() {
    final List<Country> countryList = World.getAllCountries();

    List<Country> filteredCountries =
        Stream.of(countryList)
            .filterNot(
                country -> country.getAlpha2().equalsIgnoreCase("hm"))// this has weired currency
            .filterNot(
                country -> country.getAlpha2().equalsIgnoreCase("sj"))// this has weired currency
            .filterNot(country -> country.getAlpha2().equalsIgnoreCase("xx")) // world
            .filterNot(country -> country.getCurrency() == null)
            .filterNot(country -> country.getCurrency().getCode() == null)
            .filterNot(country -> country.getCurrency().getSymbol() == null)
            .filterNot(country -> country.getCapital() == null || country.getCapital().isEmpty())
            .sorted(((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName())))
            .distinct()
            .toList();
    if (debug) {
      Log.i(TAG, "setCountries: Filtered countries " + filteredCountries.size());
    }
    countries.postValue(filteredCountries); // Use post() as this method will be called in a
    // background thread

  }
}
