package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    private int mCurrentPosition;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract void clear();

    public void onBind(int position) {
        this.mCurrentPosition = position;
        clear();
    }

    public int getCurrentPosition() {
        return this.mCurrentPosition;
    }
}
