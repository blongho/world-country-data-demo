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
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import androidx.core.content.ContextCompat;
import com.blongho.countrydata.BuildConfig;
import com.blongho.countrydata.R;
import com.danielstone.materialaboutlibrary.ConvenienceBuilder;
import com.danielstone.materialaboutlibrary.MaterialAboutFragment;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutTitleItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard.Builder;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.danielstone.materialaboutlibrary.util.OpenSourceLicense;
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity;
import com.mikepenz.iconics.IconicsDrawable;

public class AboutFragment extends MaterialAboutFragment {

  private static final String TAG = "AboutFragment";

  private MaterialAboutList createMaterialAboutList(final Context c) {
    Builder appCardBuilder = new Builder();

    appCardBuilder.addItem(new MaterialAboutTitleItem.Builder()
        .text(getString(R.string.app_name))
        .desc("© 2020 " + getString(R.string.author_name))
        .icon(R.mipmap.ic_launcher)
        .build());

    appCardBuilder.addItem(ConvenienceBuilder.createVersionActionItem(c,
        new IconicsDrawable(c)
            .sizeDp(18),
        getString(R.string.app_version),
        false)
        .setIconRes(R.drawable.ic_info));

    appCardBuilder.addItem(ConvenienceBuilder.createWebsiteActionItem(c,
        new IconicsDrawable(c)
            .sizeDp(18),
        getString(R.string.app_source_code),
        false,
        Uri.parse(getString(R.string.app_source_code_url)))
        .setIconRes(R.drawable.ic_source_code));

    appCardBuilder.addItem(ConvenienceBuilder.createWebsiteActionItem(c,
        new IconicsDrawable(c)
            .sizeDp(18),
        getString(R.string.country_data_library),
        false,
        Uri.parse(getString(R.string.country_data_url)))
        .setIconRes(R.drawable.ic_source_code)
        .setSubText(getString(R.string.country_data_version)));

    Builder authorCardBuilder = new Builder();
    authorCardBuilder.title(getString(R.string.app_author));
    authorCardBuilder.titleColor(ContextCompat.getColor(c, R.color.colorAccent));

    authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
        .text(R.string.author_name)
        .subText(R.string.author_location)
        .icon(new IconicsDrawable(c)
            .sizeDp(18))
        .setOnClickAction(
            ConvenienceBuilder.createWebsiteOnClickAction(c,
                Uri.parse(getString(R.string.author_website))))
        .build());

    authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
        .text("LinkedIn")
        .subText(getString(R.string.author_social_handle_linkedIn))
        .icon(R.drawable.ic_linkedin)
        .setOnClickAction(ConvenienceBuilder
            .createWebsiteOnClickAction(c,
                Uri.parse(getString(R.string.author_social_url_linkedIn))))
        .build());
    authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
        .text("Github")
        .subText(getString(R.string.author_social_handle_github))
        .icon(R.drawable.ic_github)
        .setOnClickAction(ConvenienceBuilder
            .createWebsiteOnClickAction(c, Uri.parse(getString(R.string.author_social_url_github))))
        .build());

    authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
        .text("Twitter")
        .subText(getString(R.string.author_social_handle_twitter))
        .icon(R.drawable.ic_twitter)
        .setOnClickAction(ConvenienceBuilder
            .createWebsiteOnClickAction(c,
                Uri.parse(getString(R.string.author_social_url_twitter))))
        .build());

    authorCardBuilder.addItem(ConvenienceBuilder.createEmailItem(c,
        ContextCompat.getDrawable(c, R.drawable.ic_email),
        getString(R.string.app_send_author_email), false, getString(R.string.author_social_email),
        ""));

    Builder countryDataLibrary = new Builder();

    countryDataLibrary.title(getString(R.string.country_data_library));

    countryDataLibrary.addItem(ConvenienceBuilder.createVersionActionItem(c,
        new IconicsDrawable(c)
            .sizeDp(18),
        getString(R.string.app_version),
        false)
        .setSubText(getString(R.string.country_data_version))
    );

    countryDataLibrary.addItem(ConvenienceBuilder.createWebsiteActionItem(c,
        new IconicsDrawable(c)
            .sizeDp(18),
        getString(R.string.visit_library),
        false,
        Uri.parse(getString(R.string.country_data_url))));

    Builder communicationBuilder = new Builder();
    communicationBuilder.title(getString(R.string.communication));
    communicationBuilder.addItem(ConvenienceBuilder.createRateActionItem(c,
        new IconicsDrawable(c)
            .sizeDp(18),
        getString(R.string.rate_this_app),
        null
    )
        .setIconRes(R.drawable.ic_rate));

    communicationBuilder.addItem(new MaterialAboutActionItem.Builder()
        .text(getString(R.string.share_with_friends))
        .icon(R.drawable.ic_share)
        .setOnClickAction(this::shareApp)
        .build()
    );

    communicationBuilder.addItem(ConvenienceBuilder.createWebsiteActionItem(c,
        new IconicsDrawable(c)
            .sizeDp(18),
        getString(R.string.report_an_issue),
        false,
        Uri.parse(getString(R.string.app_issue_report_url)))
        .setIconRes(R.drawable.ic_bug));

    Builder openSourceLibraries = new Builder();
    openSourceLibraries.title(getString(R.string.oss_libraries));
    openSourceLibraries.addItem(new MaterialAboutActionItem.Builder()
        .text(R.string.oss_libraries)
        .setOnClickAction(this::openSources)
        .build());

    MaterialAboutCard materialAboutLibraryLicenseCard = ConvenienceBuilder.createLicenseCard(c,
        new IconicsDrawable(c)
            .sizeDp(18),
        "material-about-library", "2016", "Daniel Stone",
        OpenSourceLicense.APACHE_2);
    Builder otherCardBuilder = new Builder();
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
        communicationBuilder.build(), openSourceLibraries.build());
  }

  private void openSources() {
    startActivity(new Intent(requireContext(), OssLicensesMenuActivity.class));
  }

  private void shareApp() {
    try {
      Intent shareIntent = new Intent(Intent.ACTION_SEND);
      shareIntent.setType("text/plain");
      shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
      String shareString =
          getString(R.string.share_with_friends_text);
      shareString += BuildConfig.APPLICATION_ID;
      shareIntent.putExtra(Intent.EXTRA_TEXT, shareString);
      startActivity(Intent.createChooser(shareIntent, getString(R.string.share_with_title)));
    } catch (Exception e) {
      //e.toString();
    }
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
    return createMaterialAboutList(activityContext);
  }


}