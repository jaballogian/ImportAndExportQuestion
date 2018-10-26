package jaballogian.importandexportquestion;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/*
  Created by galih on 07-Jun-18.
 */
public class AnswerAdapter extends BaseAdapter {

    private List<Answer> mAnswers;
    private Activity mContext;

    public AnswerAdapter(Activity context, List<Answer> Answers) {
        mContext = context;
        mAnswers = Answers;
    }

    private class ViewHolder {

        TextView textQuestion;
        TextView textRightAnswer;
        TextView textAnswer;
        TextView textDescription;
    }

    @Override
    public View getView(int position, View view, ViewGroup container) {
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.answer_info, container, false);
        }

        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if(viewHolder == null){
            viewHolder = new ViewHolder();
            viewHolder.textQuestion = view.findViewById(R.id.questionListViewAnswerInfo);
            viewHolder.textRightAnswer = view.findViewById(R.id.shortAnswerListViewAnswerInfo);
            viewHolder.textAnswer = view.findViewById(R.id.longAnswerListViewAnswerInfo);
            viewHolder.textDescription = view.findViewById(R.id.descriptionListViewAnswerInfo);
            view.setTag(viewHolder);
        }

        // setting here values to the fields of my items from my Answer object
        viewHolder.textQuestion.setText(mAnswers.get(position).getQuestions());
        viewHolder.textRightAnswer.setText(mAnswers.get(position).getRightAnswer());
        switch (mAnswers.get(position).getRightAnswer()){
            case "A":
                viewHolder.textAnswer.setText(mAnswers.get(position).getAnswerA());
                break;
            case "B":
                viewHolder.textAnswer.setText(mAnswers.get(position).getAnswerB());
                break;
            case "C":
                viewHolder.textAnswer.setText(mAnswers.get(position).getAnswerC());
                break;
            case "D":
                viewHolder.textAnswer.setText(mAnswers.get(position).getAnswerD());
                break;
            case "E":
                viewHolder.textAnswer.setText(mAnswers.get(position).getAnswerE());
                break;

        }
        viewHolder.textDescription.setText(mAnswers.get(position).getDescription());

        return view;
    }

    @Override
    public int getCount() {
        if (mAnswers != null) {
            return mAnswers.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return mAnswers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}