package com.example.ayurvaidya;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemSpacingDecoration extends RecyclerView.ItemDecoration {
    private final int spacing; // Spacing in pixels

    public ItemSpacingDecoration(Context context, int spacingDp) {
        this.spacing = (int) (spacingDp * context.getResources().getDisplayMetrics().density);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = spacing; // Add left spacing (you can customize for other sides)
        outRect.right = spacing; // Add right spacing (you can customize for other sides)
        outRect.bottom = spacing; // Add bottom spacing (you can customize for other sides)

        // If you don't want spacing at the top of the first item, you can do:
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = 0;
        } else {
            outRect.top = spacing; // Add top spacing for all other items
        }
    }
}
