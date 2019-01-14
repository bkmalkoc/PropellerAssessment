package bkm.com.propellerassessment.activity;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import bkm.com.propellerassessment.R;
import bkm.com.propellerassessment.adapters.EventAdapter;
import bkm.com.propellerassessment.fragment.AddEventDialogFragment;
import bkm.com.propellerassessment.model.Event;
import bkm.com.propellerassessment.model.UserData;
import bkm.com.propellerassessment.networkcalls.assesstmentTask.AssesstmentTaskPresenter;
import bkm.com.propellerassessment.networkcalls.assesstmentTask.AssesstmentTaskPresenterImpl;
import bkm.com.propellerassessment.networkcalls.assesstmentTask.AssesstmentTaskView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements AssesstmentTaskView, AddEventDialogFragment.AddEventDialogListener {

    @BindView(R.id.eventsView)
    RecyclerView eventsView;

    EventAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();

        AssesstmentTaskPresenter assesstmentTaskPresenter = new AssesstmentTaskPresenterImpl(MainActivity.this, this);
        assesstmentTaskPresenter.fetchAssesstmentTask();
    }

    private void init() {
        eventsView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        eventsView.setLayoutManager(linearLayoutManager);
    }

    @OnClick(R.id.fab)
    void fabClick() {
        DialogFragment dialog = new AddEventDialogFragment();
        dialog.show(getSupportFragmentManager(), "AddEventDialog");
    }

    @Override
    public void successAssesstmentTask(UserData userData) {
        if (userData.getEvents() != null && userData.getEvents().size() > 0) {
            populateData(userData.getEvents());
        }
    }

    @Override
    public void errorAssesstmentTask(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    public void populateData(List<Event> userEvents) {
        eventAdapter = new EventAdapter(MainActivity.this, userEvents);
        eventsView.setAdapter(eventAdapter);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, Event event) {
        updateData(event);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
    }

    private void updateData(Event event) {
        eventAdapter.insert(event);
    }
}
