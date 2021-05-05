package com.example.brochure.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brochure.R;
import com.example.brochure.model.Course;

import java.util.ArrayList;
import java.util.List;

public class SearchSuggestionAdapter extends ArrayAdapter {

    private Context context;
    private List<Course> data = new ArrayList<>();
    private List<Course> temp;
    protected List<Course> suggestions;

    public SearchSuggestionAdapter(Context context, List<Course> courses) {
        super(context, android.R.layout.simple_dropdown_item_1line, courses);
        data = courses;
        this.context = context;
        this.temp = new ArrayList<>(courses);
        this.suggestions = new ArrayList<>(courses);
    }

    public List<Course> getSuggestions() {
        return suggestions;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.layout_dropdown_search_suggestion_item, null);
        }
        Course course = data.get(position);
        TextView textView = convertView.findViewById(R.id.search_suggestion_item);
        textView.setText(course.getName());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return myFilter;
    }
    Filter myFilter = new Filter() {

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            Course course = (Course) resultValue;
//            Toast.makeText(context,course.getName(),Toast.LENGTH_LONG).show();
            return course.getName();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (Course course : temp) {
                    if (course.toString().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        suggestions.add(course);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<Course> values = (List<Course>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (Course course : values) {
                    add(course);
                    notifyDataSetChanged();
                }
            }
            else{
                clear();
                notifyDataSetChanged();
            }
        }
    };

}