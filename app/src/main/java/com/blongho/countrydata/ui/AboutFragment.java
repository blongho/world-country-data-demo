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

package com.blongho.countrydata.ui;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import com.blongho.countrydata.R;
import com.danielstone.materialaboutlibrary.ConvenienceBuilder;
import com.danielstone.materialaboutlibrary.MaterialAboutFragment;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutTitleItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.danielstone.materialaboutlibrary.util.OpenSourceLicense;
import com.mikepenz.iconics.IconicsDrawable;

public class AboutFragment extends MaterialAboutFragment {

  public static MaterialAboutList createMaterialAboutList(final Context c, final int theme) {
    MaterialAboutCard.Builder appCardBuilder = new MaterialAboutCard.Builder();

    // Add items to card

    appCardBuilder.addItem(new MaterialAboutTitleItem.Builder()
        .text("Material About Library")
        .desc("© 2020 Daniel Stone")
        .icon(R.mipmap.ic_launcher)
        .build());

    appCardBuilder.addItem(ConvenienceBuilder.createVersionActionItem(c,
        new IconicsDrawable(c)
            .sizeDp(18),
        "Version",
        false));

    appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
        .text("Changelog")
        .icon(new IconicsDrawable(c)
            .sizeDp(18))
        .setOnClickAction(ConvenienceBuilder.createWebViewDialogOnClickAction(c, "Releases",
            "https://github.com/daniel-stoneuk/material-about-library/releases", true, false))
        .build());

    MaterialAboutCard.Builder authorCardBuilder = new MaterialAboutCard.Builder();
    authorCardBuilder.title("Author");
//        authorCardBuilder.titleColor(ContextCompat.getColor(c, R.color.colorAccent));

    authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
        .text("Daniel Stone")
        .subText("United Kingdom")
        .icon(new IconicsDrawable(c)
            .sizeDp(18))
        .build());

    authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
        .text("Fork on GitHub")
        .icon(new IconicsDrawable(c)
            .sizeDp(18))
        .setOnClickAction(ConvenienceBuilder
            .createWebsiteOnClickAction(c, Uri.parse("https://github.com/daniel-stoneuk")))
        .build());

    MaterialAboutCard.Builder convenienceCardBuilder = new MaterialAboutCard.Builder();

    convenienceCardBuilder.title("Convenience Builder");

    convenienceCardBuilder.addItem(ConvenienceBuilder.createVersionActionItem(c,
        new IconicsDrawable(c)
            .sizeDp(18),
        "Version",
        false));

    convenienceCardBuilder.addItem(ConvenienceBuilder.createWebsiteActionItem(c,
        new IconicsDrawable(c)
            .sizeDp(18),
        "Visit Website",
        true,
        Uri.parse("http://danstone.uk")));

    convenienceCardBuilder.addItem(ConvenienceBuilder.createRateActionItem(c,
        new IconicsDrawable(c)
            .sizeDp(18),
        "Rate this app",
        null
    ));

    convenienceCardBuilder.addItem(ConvenienceBuilder.createEmailItem(c,
        new IconicsDrawable(c)
            .sizeDp(18),
        "Send an email",
        true,
        "apps@danstone.uk",
        "Question concerning MaterialAboutLibrary"));

    convenienceCardBuilder.addItem(ConvenienceBuilder.createPhoneItem(c,
        new IconicsDrawable(c)
            .sizeDp(18),
        "Call me",
        true,
        "+44 12 3456 7890"));

    convenienceCardBuilder.addItem(ConvenienceBuilder.createMapItem(c,
        new IconicsDrawable(c)
            .sizeDp(18),
        "Visit London",
        null,
        "London Eye"));

    MaterialAboutCard.Builder otherCardBuilder = new MaterialAboutCard.Builder();
    otherCardBuilder.title("Other");

    otherCardBuilder.cardColor(Color.parseColor("#c0cfff"));

    otherCardBuilder.addItem(new MaterialAboutActionItem.Builder()
        .icon(new IconicsDrawable(c)
            .sizeDp(18))
        .text("HTML Formatted Sub Text")
        .subTextHtml(
            "This is <b>HTML</b> formatted <i>text</i> <br /> This is very cool because it allows lines to get very long which can lead to all kinds of possibilities. <br /> And line breaks. <br /> Oh and by the way, this card has a custom defined background.")
        .setIconGravity(MaterialAboutActionItem.GRAVITY_TOP)
        .build()
    );

    return new MaterialAboutList(appCardBuilder.build(), authorCardBuilder.build(),
        convenienceCardBuilder.build(), otherCardBuilder.build());
  }

  public static MaterialAboutList createMaterialAboutLicenseList(final Context c) {

    MaterialAboutCard materialAboutLibraryLicenseCard = ConvenienceBuilder.createLicenseCard(c,
        new IconicsDrawable(c)
            .sizeDp(18),
        "material-about-library", "2016", "Daniel Stone",
        OpenSourceLicense.APACHE_2);

    MaterialAboutCard androidIconicsLicenseCard = ConvenienceBuilder.createLicenseCard(c,
        new IconicsDrawable(c)
            .sizeDp(18),
        "Android Iconics", "2016", "Mike Penz",
        OpenSourceLicense.APACHE_2);

    MaterialAboutCard leakCanaryLicenseCard = ConvenienceBuilder.createLicenseCard(c,
        new IconicsDrawable(c)
            .sizeDp(18),
        "LeakCanary", "2015", "Square, Inc",
        OpenSourceLicense.APACHE_2);

    MaterialAboutCard mitLicenseCard = ConvenienceBuilder.createLicenseCard(c,
        new IconicsDrawable(c)
            .sizeDp(18),
        "MIT Example", "2017", "Matthew Ian Thomson",
        OpenSourceLicense.MIT);

    MaterialAboutCard gplLicenseCard = ConvenienceBuilder.createLicenseCard(c,
        new IconicsDrawable(c)
            .sizeDp(18),
        "GPL Example", "2017", "George Perry Lindsay",
        OpenSourceLicense.GNU_GPL_3);

    return new MaterialAboutList(
        materialAboutLibraryLicenseCard,
        androidIconicsLicenseCard,
        leakCanaryLicenseCard,
        mitLicenseCard,
        gplLicenseCard);
  }

  @Override
  protected MaterialAboutList getMaterialAboutList(final Context activityContext) {
    return createMaterialAboutLicenseList(activityContext);
  }
}
