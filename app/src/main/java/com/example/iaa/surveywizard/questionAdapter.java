package com.example.iaa.surveywizard;

import android.content.Context;
import android.content.Intent;
import android.mtp.MtpConstants;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by IAA on 2/20/2018.
 */
public class questionAdapter extends ArrayAdapter<questionsWithAnswers> {

    public questionAdapter(Context context, ArrayList<questionsWithAnswers> questionses) {
        super(context, 0, questionses);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.answerslist, parent, false);
        }


        final questionsWithAnswers currentQuestion;
        currentQuestion = getItem(position);

        TextView question = (TextView) listItemView.findViewById(R.id.textView15);

        question.setText(currentQuestion.getQuestionBody());

        Button button = (Button) listItemView.findViewById(R.id.delete_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseAdapter Dbadapter = new DatabaseAdapter(questionsOfCategory.context);

                Dbadapter.delete_raw(currentQuestion.getQuestionBody());

                Intent intent = new Intent(questionsOfCategory.context, questionsOfCategory.class);
                questionsOfCategory.context.startActivity(intent);
            }
        });

        Button button2 = (Button) listItemView.findViewById(R.id.showanswersButton);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(questionsOfCategory.context, answersofQuestion.class);
                answersofQuestion.question = currentQuestion.getQuestionBody();
                questionsOfCategory.context.startActivity(intent);
            }
        });


        return listItemView;
    }
}
