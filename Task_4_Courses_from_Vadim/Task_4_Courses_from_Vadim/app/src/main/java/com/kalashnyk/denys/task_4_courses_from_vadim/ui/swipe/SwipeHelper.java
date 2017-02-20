package com.kalashnyk.denys.task_4_courses_from_vadim.ui.swipe;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.kalashnyk.denys.task_4_courses_from_vadim.ui.adapters.MyAdapter;


/**
 * Created by User on 20.10.2016.
 */
public class SwipeHelper extends ItemTouchHelper.SimpleCallback {
    MyAdapter myAdapter;
    public SwipeHelper(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }

    public SwipeHelper(MyAdapter myAdapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT);
        this.myAdapter = myAdapter;
    }

        @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        myAdapter.dismissPerson(viewHolder.getAdapterPosition());
    }
}
