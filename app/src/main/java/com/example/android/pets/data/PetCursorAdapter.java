package com.example.android.pets.data;

import android.content.Context;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.example.android.pets.R;
import com.example.android.pets.databinding.ListItemBinding;

/**
 * {@link PetCursorAdapter} is an adapter for a list or grid view
 * that uses a {@link Cursor} of pet data as its data source. This adapter knows
 * how to create list items for each row of pet data in the {@link Cursor}.
 */
public class PetCursorAdapter extends CursorAdapter {

    /**
     * Constructs a new {@link PetCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public PetCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        ListItemBinding binding = ListItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return binding.getRoot();
    }

    /**
     * This method binds the pet data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current pet can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        ListItemBinding binding = DataBindingUtil.getBinding(view);
        if (binding != null) {
            binding.name.setText(cursor.getString(cursor.getColumnIndexOrThrow("name")));
//            Show "Unknown breed" if breed is not specified.
            String breed = cursor.getString(cursor.getColumnIndexOrThrow("breed"));
            binding.summary.setText(TextUtils.isEmpty(breed) ? context.getString(R.string.unknown_breed) : breed);
        }
        view.setTag(binding);
    }
}