/*
 * MIT License
 *
 * Copyright (c) 2020 - 2022 Bernard Che Longho
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

package com.blongho.countrydata.utils;

import android.util.Log;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public final class AppUtils {

  private static final String TAG = "AppUtils";

  public static String formatNumber(final double number) {
    NumberFormat numberFormat = NumberFormat.getNumberInstance();
    numberFormat.setMaximumFractionDigits(2);
    numberFormat.setGroupingUsed(true);
    return numberFormat.format(number);
  }

  public static String formatNumber(final long number) {

    NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
    numberFormat.setMaximumFractionDigits(2);
    numberFormat.setGroupingUsed(true);
    return numberFormat.format(number);
  }

  public static String listToString(final List<String> items) {
    Log.d(TAG, "listToString: items are " + items);
    if (items == null || items.size() == 0) {
      return "";
    } else if (items.size() == 1) {
      return items.get(0);
    } else {
      final StringBuilder builder = new StringBuilder();
      int idx = 1;
      for (final String item : items) {
        builder.append(idx).append(". ").append(item).append("\n");
        idx += 1;
      }
      return builder.toString();

    }
  }
}