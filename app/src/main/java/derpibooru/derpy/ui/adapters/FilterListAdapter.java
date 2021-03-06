package derpibooru.derpy.ui.adapters;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import derpibooru.derpy.R;
import derpibooru.derpy.data.server.DerpibooruFilter;

public class FilterListAdapter extends RecyclerView.Adapter<FilterListAdapter.ViewHolder> {
    private ArrayList<DerpibooruFilter> mFilters;
    private FiltersViewHandler mHandler;

    public FilterListAdapter(DerpibooruFilter currentFilter,
                             ArrayList<DerpibooruFilter> filters,
                             FiltersViewHandler handler) {
        mHandler = handler;
        replaceFilters(filters, currentFilter);
    }

    public void replaceFilters(ArrayList<DerpibooruFilter> newFilters,
                               DerpibooruFilter newCurrentFilter) {
        mFilters = newFilters;
        /* the server, when requested for a list of available filters,
         * does not specify which one of them is currently used. */
        int indexOfCurrentFilterInFilterList = mFilters.indexOf(newCurrentFilter);
        if (indexOfCurrentFilterInFilterList == -1) {
            Log.e("FiltersViewAdapter", "the current filter wasn't found in the filter list");
        } else {
            /* make current filter the first item in the filter list */
            DerpibooruFilter newFilter = mFilters.get(indexOfCurrentFilterInFilterList);
            mFilters.remove(indexOfCurrentFilterInFilterList);
            mFilters.add(0, newFilter);
        }
        super.notifyDataSetChanged();
    }

    @Override
    public FilterListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_filters_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        displayFilterInViewHolder(holder, mFilters.get(position));
        holder.buttonUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.changeFilterTo(mFilters.get(position));
            }
        });
        /* hide the "use" button on the current filter */
        if (position == 0) {
            holder.buttonUse.setVisibility(View.GONE);
        } else {
            holder.buttonUse.setVisibility(View.VISIBLE);
        }
    }

    private void displayFilterInViewHolder(ViewHolder holder, DerpibooruFilter filter) {
        holder.textName.setText(filter.getName());
        holder.textUsedBy.setText(String.format("Used by %d people",
                                                filter.getUserCount()));
        holder.textStatistics.setText(String.format("Spoilers %d tags and hides %d tags",
                                                    filter.getSpoileredTagNames().size(),
                                                    filter.getHiddenTagNames().size()));
        holder.textDescription.setText(filter.getDescription());
    }

    @Override
    public int getItemCount() {
        return (mFilters != null) ? mFilters.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.textName) TextView textName;
        @Bind(R.id.textUsedBy) TextView textUsedBy;
        @Bind(R.id.textStatistics) TextView textStatistics;
        @Bind(R.id.textDescription) TextView textDescription;

        @Bind(R.id.buttonDetails) AppCompatButton buttonDetails;
        @Bind(R.id.buttonUse) AppCompatButton buttonUse;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public interface FiltersViewHandler {
        void changeFilterTo(DerpibooruFilter newFilter);
        /* TODO: a separate activity for filter details/editing */
    }
}