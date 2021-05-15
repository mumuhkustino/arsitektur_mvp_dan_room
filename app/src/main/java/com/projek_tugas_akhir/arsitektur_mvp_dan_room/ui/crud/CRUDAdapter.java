//package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud;
//
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.projek_tugas_akhir.arsitektur_mvp_dan_room.R;
//import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.others.Medical;
//import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base.BaseViewHolder;
//
//import java.util.List;
//
//public class CRUDAdapter extends RecyclerView.Adapter<BaseViewHolder> {
//
//    public static final int VIEW_TYPE_EMPTY = 0;
//    public static final int VIEW_TYPE_NORMAL = 1;
//
//    private Callback mCallback;
//    private List<Medical> medicalList;
//
//    public CRUDAdapter(List<Medical> medicalList) {
//        this.medicalList = medicalList;
//        Log.d("CRUDA", "CRUDAdapter: " + medicalList.size());
//    }
//
//    public void setMedicalList(List<Medical> medicalList) {
//        this.medicalList.addAll(medicalList);
//    }
//
//    public void setCallback(Callback mCallback) {
//        this.mCallback = mCallback;
//    }
//
//    @NonNull
//    @Override
//    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        switch (viewType) {
//            case VIEW_TYPE_NORMAL:
//                return new ViewHolder(
//                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_update_view, parent, false));
//            case VIEW_TYPE_EMPTY:
//            default:
//                return new EmptyViewHolder(
//                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_update_empty_view, parent, false));
//        }
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        if (this.medicalList != null && this.medicalList.size() > 0) {
//            return VIEW_TYPE_NORMAL;
//        } else {
//            return VIEW_TYPE_EMPTY;
//        }
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
//        holder.onBind(position);
//    }
//
//    @Override
//    public int getItemCount() {
//        if (this.medicalList != null && this.medicalList.size() > 0) {
//            return this.medicalList.size();
//        } else {
//            return 1;
//        }
//    }
//
//    public void addItems(List<Medical> medicalList) {
//        this.medicalList.addAll(medicalList);
//        notifyDataSetChanged();
//        Log.d("CRUDA", "addItems: " + medicalList.size());
//    }
//
//    public void selectItems(List<Medical> medicalList) {
//        this.medicalList.addAll(medicalList);
//        notifyDataSetChanged();
//        Log.d("CRUDA", "selectItems: " + medicalList.size());
//    }
//
//    public void updateItems(List<Medical> medicalList) {
//        this.medicalList.addAll(medicalList);
//        notifyDataSetChanged();
//        Log.d("CRUDA", "updateItems: " + medicalList.size());
//    }
//
//    public void deleteItems(List<Medical> medicalList) {
//        this.medicalList.addAll(medicalList);
//        notifyDataSetChanged();
//        Log.d("CRUDA", "deleteItems: " + medicalList.size());
//    }
//
//    public void crudItems(List<Medical> medicalList) {
//        clearItems();
//        this.medicalList.addAll(medicalList);
//        notifyDataSetChanged();
////        Log.d("CRUDA", "crudItems: " + medicalList.size());
//    }
//
//    public void clearItems() {
//        this.medicalList.clear();
//    }
//
//    public interface Callback {
//        void onEmptyViewRetryClick();
//    }
//
//    public class ViewHolder extends BaseViewHolder {
//
//        private TextView id;
//
//        private TextView hospitalName;
//
//        private TextView medicineName;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            this.id = itemView.findViewById(R.id.medicalIdTextView);
//            this.hospitalName = itemView.findViewById(R.id.hospitalNameTextView);
//            this.medicineName = itemView.findViewById(R.id.medicineNameTextView);
//        }
//
//        protected void clear() {
//            this.id.setText("");
//            this.hospitalName.setText("");
//            this.medicineName.setText("");
//        }
//
//        public void onBind(int position) {
//            super.onBind(position);
//
//            final Medical medical = medicalList.get(position);
//
//            if (position >= 0) {
//                this.id.setText(String.valueOf(position));
//            }
//
//            if (medical.getHospitalName() != null) {
//                this.hospitalName.setText(medical.getHospitalName());
//            }
//
//            if (medical.getMedicineName() != null) {
//                this.medicineName.setText(medical.getMedicineName());
//            }
//        }
//    }
//
//    public class EmptyViewHolder extends BaseViewHolder {
//
//        Button retryButton;
//
//        TextView messageTextView;
//
//        public EmptyViewHolder(View itemView) {
//            super(itemView);
//            retryButton = itemView.findViewById(R.id.btnRetry);
//            messageTextView = itemView.findViewById(R.id.tvMessage);
//
//            retryButton.setOnClickListener(view -> onRetryClick());
//        }
//
//        @Override
//        protected void clear() {
//            messageTextView.setText("");
//        }
//
//        void onRetryClick() {
//            if (mCallback != null)
//                mCallback.onEmptyViewRetryClick();
//        }
//    }
//}
