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

package com.blongho.countrydata.adapter;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.annimon.stream.Stream;
import com.blongho.country_data.Country;
import com.blongho.countrydata.BR;
import com.blongho.countrydata.adapter.CountryAdapter.CountryViewHolder;
import com.blongho.countrydata.databinding.CurrencyItemBinding;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Bernard Longho
 * @since 2020-05-30
 * <p>This class is the Recycler Adapter. It implements Filterable because we intend to
 * implement search in the view</p>
 */
public class CountryAdapter extends ListAdapter<Country, CountryViewHolder> implements Filterable {

  private static final String TAG = "CountryAdapter";
  private OnCountryClickListener listener;
  private final static boolean debug = false;
  private final Filter FILTER = new Filter() {

    @Override
    protected FilterResults performFiltering(final CharSequence constraint) {
      List<Country> filteredList = new ArrayList<>();
      if (constraint == null || constraint.length() == 0) {
        filteredList = new ArrayList<>(getListCopy());
      } else {
        final String pattern = constraint.toString().toLowerCase().trim();
        List<Country> copy = getListCopy();
        //Log.d(TAG, "performFiltering: List copy size is " + copy.size());

        // there can be an exception when parsing values, we catch them and proceed as if nothing
        // happened
        try {
          filteredList.addAll(
              Stream.of(copy).filter(c -> c.getAlpha2().toLowerCase().contains(pattern)).toList());
          filteredList.addAll(
              Stream.of(copy).filter(c -> c.getAlpha3().toLowerCase().contains(pattern)).toList());
          filteredList.addAll(
              Stream.of(copy).filter(c -> c.getName().toLowerCase().contains(pattern)).toList());
          filteredList.addAll(
              Stream.of(copy).filter(c -> c.getCapital().toLowerCase().contains(pattern)).toList());
          filteredList.addAll(
              Stream.of(copy).filter(c -> c.getCurrency().getCode().toLowerCase().contains(pattern))
                  .toList());
          filteredList.addAll(
              Stream.of(copy).filter(c -> c.getCurrency().getName().toLowerCase().contains(pattern))
                  .toList());
          filteredList.addAll(Stream.of(copy)
              .filter(c -> c.getCurrency().getSymbol().toLowerCase().contains(pattern)).toList());

          // Avoid parsing number when it is "not possible"
          if (TextUtils.isDigitsOnly(pattern)) {
            filteredList.addAll(
                Stream.of(copy).filter(c -> c.getId() == Integer.parseInt(pattern)).toList());
            filteredList.addAll(
                Stream.of(copy).filter(c -> c.getPopulation() == Double.parseDouble(pattern))
                    .toList());
          }
          filteredList.addAll(
              Stream.of(copy).filter(c -> c.getContinent().toLowerCase().contains(pattern))
                  .toList());
        } catch (Exception ex) {
          if (debug) {
            Log.d(TAG, "performFiltering: Bad number format exception " + ex.getLocalizedMessage());
          }
        }
      }
      //Log.i(TAG, "performFiltering: Filtered list size " + filteredList.size());
      FilterResults results = new FilterResults();
      results.values = filteredList;
      return results;
    }

    @Override
    protected void publishResults(final CharSequence constraint, final FilterResults results) {
      submitList(Collections.unmodifiableList((List<Country>) results.values));
    }
  };

  /**
   * A copy of the items that will be loaded to the adapter
   */
  private List<Country> listCopy = new ArrayList<>();


  public List<Country> getListCopy() {
    return listCopy;
  }

  /**
   * Submit the Country list to the adapter. The submitList method calls notifyDataChange internally so one does not
   * need to do that.
   *
   * @param countries The list of countries to display
   */
  public void setCountries(final List<Country> countries) {
    submitList(countries);
    listCopy = new ArrayList<>(countries);
  }

  public CountryAdapter() {
    super(diffCallback);
  }

  private static final DiffUtil.ItemCallback<Country> diffCallback = new ItemCallback<Country>() {
    @Override
    public boolean areItemsTheSame(@NonNull final Country oldItem, @NonNull final Country newItem) {
      return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull final Country oldItem,
        @NonNull final Country newItem) {
      return oldItem.equals(newItem); // Country is equal if id, alpha2, alpha3 and name are same
    }
  };

  @NonNull
  @Override
  public CountryViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
    CurrencyItemBinding binding = CurrencyItemBinding
        .inflate(LayoutInflater.from(parent.getContext()), parent, false);
    return new CountryViewHolder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull final CountryViewHolder holder, final int position) {
    final Country country = getItem(position);
    holder.bind(country);
    holder.itemBinding.currencyFlag.setImageResource(country.getFlagResource());
    holder.itemView.setOnClickListener(v -> listener.onCountryClick(country));
  }

  public void setListener(
      final OnCountryClickListener listener) {
    this.listener = listener;
  }

  /**
   * <p>Returns a filter that can be used to constrain data with a filtering
   * pattern.</p>
   *
   * <p>This method is usually implemented by {@link Adapter}
   * classes.</p>
   *
   * @return a filter used to constrain data
   */
  @Override
  public Filter getFilter() {
    return FILTER;
  }


  static class CountryViewHolder extends RecyclerView.ViewHolder {

    final CurrencyItemBinding itemBinding;

    CountryViewHolder(@NonNull final CurrencyItemBinding binding) {
      super(binding.getRoot());
      this.itemBinding = binding;
    }

    void bind(final Object obj) {
      itemBinding.setVariable(BR.country, obj);
      itemBinding.executePendingBindings();
    }
  }

  /**
   * Our listener to pass country object when it is clicked
   */
  public interface OnCountryClickListener {

    void onCountryClick(final Country country);
  }
}
