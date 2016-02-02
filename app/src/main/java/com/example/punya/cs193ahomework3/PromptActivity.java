package com.example.punya.cs193ahomework3;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Scanner;

public class PromptActivity extends AppCompatActivity {
    Story story;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.madlib1_tarzan));
        story = new Story(scan);
        newWord();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }

    public void newWord() {
        EditText enterWordET = (EditText) findViewById(R.id.EnterWordET);
        enterWordET.setText("");
        TextView wordsLeftTV = (TextView) findViewById(R.id.WordsLeftTV);
        wordsLeftTV.setText(story.getWordsLeft() + " word(s) left");
        if (story.getWordsLeft() == 0) {
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("sb", story);
            startActivity(intent);
        } else {

            String nextPlaceholder = story.getNextPlaceholder();
            TextView wordTypeTV = (TextView) findViewById(R.id.WordTypeTV);
            wordTypeTV.setText("Please type a(n) " + nextPlaceholder);
            enterWordET.setHint(nextPlaceholder);
        }
    }

    public void OKButton_click(View view) {
        EditText enterWordET = (EditText) findViewById(R.id.EnterWordET);
        String userInput = enterWordET.getText().toString();
        story.fillInPlaceholder(userInput);
        if (story.getWordsLeft() != 0) {
            Toast.makeText(this, "Great! Keep going!", Toast.LENGTH_SHORT).show();
        }
        newWord();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Prompt Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.punya.cs193ahomework3/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Prompt Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.punya.cs193ahomework3/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
