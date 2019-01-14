package bkm.com.propellerassessment.networkcalls.assesstmentTask;

import bkm.com.propellerassessment.model.UserData;

public interface AssesstmentTaskView {
    void successAssesstmentTask(UserData userData);
    void errorAssesstmentTask(String errorMessage);
}
