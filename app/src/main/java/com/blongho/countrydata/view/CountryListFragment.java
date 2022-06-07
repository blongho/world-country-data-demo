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
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.blongho.countrydata.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.annimon.stream.Stream;
import com.blongho.country_data.Country;
import com.blongho.countrydata.R;
import com.blongho.countrydata.adapter.CountryAdapter;
import com.blongho.countrydata.databinding.FragmentCountryListBinding;
import com.blongho.countrydata.viewmodel.CountryViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

/**
 * This fragment holds the UI for the country list.
 * <p>When an item in the list is clicked, it is passed to the details fragment using
 * CountryViewModel</p>
 */
public class CountryListFragment extends Fragment implements CountryAdapter.OnCountryClickListener {

  private CountryViewModel viewModel;
  private CountryAdapter countryAdapter;
  private FragmentCountryListBinding binding;
  private ProgressDialog progressDialog;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    viewModel = new ViewModelProvider(requireActivity()).get(CountryViewModel.class);
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    binding = FragmentCountryListBinding.inflate(inflater, container, false);
    progressDialog = new ProgressDialog(requireContext());
    progressDialog.setMessage("Initializing the app...");
    progressDialog.show();
    final RecyclerView recyclerView = (RecyclerView) binding.recyclerView;
    recyclerView.setHasFixedSize(true);
    countryAdapter = new CountryAdapter();
    countryAdapter.setListener(this);
    recyclerView.setAdapter(countryAdapter);
    setHasOptionsMenu(true);
    return binding.getRoot();
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    viewModel.getLiveData().observe(getViewLifecycleOwner(),
        countries -> {
          progressDialog.dismiss();
          countryAdapter.setCountries(Stream.of(countries).distinct().toList());
        });

  }

  @Override
  public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    binding.setLifecycleOwner(requireActivity());
    requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),
        new OnBackPressedCallback(true) {
          @Override
          public void handleOnBackPressed() {
            confirmExit();
          }
        });
  }

  private void confirmExit() {
    new MaterialAlertDialogBuilder(requireContext())
        .setTitle(getString(R.string.title_action_going_back))
        .setMessage(getString(R.string.confirm_message))
        .setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.dismiss())
        .setPositiveButton(android.R.string.ok, (dialog, which) -> {
          requireActivity().finish();
        })
        .create()
        .show();
  }

  @Override
  public void onCreateOptionsMenu(@NonNull final Menu menu, @NonNull final MenuInflater inflater) {
    inflater.inflate(R.menu.country_list_menu, menu);
    MenuItem search = menu.findItem(R.id.action_search);
    SearchView searchView = (SearchView) search.getActionView();
    searchView.setOnQueryTextListener(new OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(final String query) {
        return false;
      }

      @Override
      public boolean onQueryTextChange(final String newText) {
        countryAdapter.getFilter().filter(newText);
        return false;
      }
    });
  }

  @Override
  public void onCountryClick(final Country country) {
    viewModel.setSelectedCountry(country);
    //Snackbar.make(binding.getRoot(), AppUtils.listToString(country.getLanguages()), Snackbar.LENGTH_SHORT).show();
    Navigation.findNavController(requireView())
        .navigate(
            R.id.action_navigation_country_list_fragment_to_navigation_country_details_fragment);
  }
}
