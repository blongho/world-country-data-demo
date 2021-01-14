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

package com.blongho.countrydata.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.blongho.countrydata.R;
import com.blongho.countrydata.databinding.FragmentCountryDetailsBinding;
import com.blongho.countrydata.viewmodel.CountryViewModel;

public class CountryDetailsFragment extends Fragment {

  private FragmentCountryDetailsBinding binding;

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState
  ) {
    // Inflate the layout for this fragment
    binding = FragmentCountryDetailsBinding.inflate(inflater, container, false);
    //View root = inflater.inflate(R.layout.fragment_country_details, container, false);
    return binding.getRoot();
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    CountryViewModel countryViewModel = new ViewModelProvider(requireActivity())
        .get(CountryViewModel.class);
    binding.setFragment(this);
    countryViewModel.getSelectedCountry().observe(getViewLifecycleOwner(),
        country -> {
          binding.setCountry(country);
          ((ImageView) binding.getRoot().findViewById(R.id.flag))
              .setImageResource(country.getFlagResource());
        }
    );

  }

  @Override
  public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    binding.setLifecycleOwner(requireActivity());
    requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),
        new OnBackPressedCallback(true) {
          @Override
          public void handleOnBackPressed() {
            goBack();
          }
        });
  }

  public void goBack() {
    Navigation.findNavController(requireView())
        .navigate(
            R.id.action_navigation_country_details_fragment_to_navigation_country_list_fragment);
  }


}
