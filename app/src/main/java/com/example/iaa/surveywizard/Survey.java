package com.example.iaa.surveywizard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Survey extends AppCompatActivity {

    public static String sessionCode;
    public static String sessionName;
    TextView SessionName;
    TextView SessionCode;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef= database.getReference(sessionCode);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        startService(new Intent(this, ClosingService.class));

        SessionName = (TextView) findViewById(R.id.sessionnametextView);
        SessionCode = (TextView) findViewById(R.id.sessioncodetextView);

        SessionName.setText(sessionName);
        SessionCode.setText(sessionCode);


    }

    public void UploadSurvey(View view) {

        Intent startSessionIntent = new Intent(Survey.this, MyQuestionsSecond.class);

        MyQuestionsSecond.MysessionName = Survey.sessionName;
        MyQuestionsSecond.MysessionCode = Survey.sessionCode;

        // Start the new activity
        startActivity(startSessionIntent);

    }

    public void UploadExam(View view) {
        Intent startSessionIntent = new Intent(Survey.this, Create_Exam.class);

        Create_Exam.MysessionName = Survey.sessionName;
        Create_Exam.MysessionCode = Survey.sessionCode;

        // Start the new activity
        startActivity(startSessionIntent);
    }

    protected void ondestroy() {

        myRef.removeValue();

    }


}
