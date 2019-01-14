package bkm.com.propellerassessment.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import bkm.com.propellerassessment.R;
import bkm.com.propellerassessment.model.Event;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddEventDialogFragment extends DialogFragment {

    public interface AddEventDialogListener {
        void onDialogPositiveClick(DialogFragment dialog, Event event);

        void onDialogNegativeClick(DialogFragment dialog);
    }

    AddEventDialogListener mListener;
    @BindView(R.id.medication_name)
    EditText medicationNameText;

    @BindView(R.id.medication_time)
    Button medicationTimeButton;

    @BindView(R.id.medication_date)
    Button medicationDateButton;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();

        View view = inflater.inflate(R.layout.new_event_dialog, null);
        builder.setView(view)
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(myCalendar.getTime());
                        Event event = new Event();
                        event.setMedication(medicationNameText.getText().toString());
                        event.setDatetime(dateFormat);
                        mListener.onDialogPositiveClick(AddEventDialogFragment.this, event);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogNegativeClick(AddEventDialogFragment.this);
                    }
                });

        ButterKnife.bind(this, view);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (AddEventDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(this.getActivity().toString()
                    + " must implement AddEventDialogListener");
        }
    }

    @OnClick(R.id.medication_date)
    public void pickDate() {
        showDatePickerDialog();
    }

    @OnClick(R.id.medication_time)
    public void pickTime() {
        showTimePickerDialog();
    }

    final Calendar myCalendar = Calendar.getInstance();

    public void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateStamp(myCalendar);
            }
        };

        new DatePickerDialog(getActivity(), date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    public void showTimePickerDialog() {
        TimePickerDialog.OnTimeSetListener date = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                myCalendar.set(Calendar.HOUR_OF_DAY, hour);
                myCalendar.set(Calendar.MINUTE, minute);
                updateTimeStamp(myCalendar);
            }
        };

        new TimePickerDialog(getActivity(), date, myCalendar
                .get(Calendar.HOUR_OF_DAY), myCalendar
                .get(Calendar.MINUTE),
                DateFormat.is24HourFormat(getActivity())).show();
    }

    private void updateDateStamp(Calendar myCalendar) {
        Date date = myCalendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy", Locale.US);
        medicationDateButton.setText(simpleDateFormat.format(date));
    }

    private void updateTimeStamp(Calendar myCalendar) {
        Date date = myCalendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm aaa", Locale.US);
        medicationTimeButton.setText(simpleDateFormat.format(date));
    }
}