package bkm.com.propellerassessment.networkcalls.assesstmentTask;

import android.content.Context;

import bkm.com.propellerassessment.model.UserData;
import bkm.com.propellerassessment.networkcalls.ApiClient;
import bkm.com.propellerassessment.networkcalls.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssesstmentTaskPresenterImpl implements AssesstmentTaskPresenter {
    Context mContext;
    AssesstmentTaskView mView;
    Call<UserData> callback;

    public AssesstmentTaskPresenterImpl(Context context, AssesstmentTaskView view) {
        this.mContext = context;
        this.mView = view;
    }

    @Override
    public void fetchAssesstmentTask() {
        try {
            ApiInterface apiInterface = ApiClient.with(mContext).getClient().create(ApiInterface.class);
            callback = apiInterface.getData();
            callback.enqueue(new Callback<UserData>() {
                @Override
                public void onResponse(Call<UserData> call, Response<UserData> response) {
                    UserData userData = response.body();
                    if (userData != null) {
                        mView.successAssesstmentTask(userData);
                    } else {
                        mView.errorAssesstmentTask("No data");
                    }
                }

                @Override
                public void onFailure(Call<UserData> call, Throwable t) {
                    mView.errorAssesstmentTask(t.getMessage());
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
            mView.errorAssesstmentTask(ex.getMessage());
        }
    }
}
