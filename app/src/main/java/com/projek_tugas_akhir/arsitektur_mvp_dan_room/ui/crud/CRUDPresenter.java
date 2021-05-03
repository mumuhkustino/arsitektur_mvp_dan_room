package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.DataManager;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base.BasePresenter;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base.MvpView;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class CRUDPresenter<V extends MvpView> extends BasePresenter<V> implements CRUDMvpPresenter<V> {

    private static final String TAG = "CRUDPresenter";

    @Inject
    public CRUDPresenter(DataManager mDataManager,
                         SchedulerProvider mSchedulerProvider,
                         CompositeDisposable mCompositeDisposable) {
        super(mDataManager, mSchedulerProvider, mCompositeDisposable);
    }


//    public void onViewPrepared() {
//        getMvpView().showLoading();
//        getCompositeDisposable().add(getDataManager()
//                .getBlogApiCall()
//                .subscribeOn(getSchedulerProvider().io())
//                .observeOn(getSchedulerProvider().ui())
//                .subscribe(new Consumer<BlogResponse>() {
//                    @Override
//                    public void accept(@NonNull BlogResponse blogResponse)
//                            throws Exception {
//                        if (blogResponse != null && blogResponse.getData() != null) {
//                            getMvpView().updateBlog(blogResponse.getData());
//                        }
//                        getMvpView().hideLoading();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(@NonNull Throwable throwable)
//                            throws Exception {
//                        if (!isViewAttached()) {
//                            return;
//                        }
//
//                        getMvpView().hideLoading();
//
                         // handle the error here
//                        if (throwable instanceof ANError) {
//                            ANError anError = (ANError) throwable;
//                            handleApiError(anError);
//                        }
//                    }
//                }));
//    }
}
