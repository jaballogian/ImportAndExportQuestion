package jaballogian.importandexportquestion;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.anwarshahriar.calligrapher.Calligrapher;

public class ShowQuestionsActivity extends AppCompatActivity {

    private ListView answerList;
    private List<Answer> answersList = new ArrayList<>();
    private AnswerAdapter answerAdapter = new AnswerAdapter(this, answersList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_questions);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "PRODUCT_SANS.ttf", true);

        answerList = findViewById(R.id.listQuestionListViewShowQuestionActivity);

        DatabaseReference databaseQuestions = FirebaseDatabase.getInstance().getReference("List of Questions");
        databaseQuestions.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<String, Object> map = (Map<String, Object>) ds.getValue();

                    Answer answer = new Answer();
                    if (map != null) {
                        answer.setQuestions(map.get("Question ").toString());
                        answer.setAnswerA(map.get("Answer A ").toString());
                        answer.setAnswerB(map.get("Answer B ").toString());
                        answer.setAnswerC(map.get("Answer C ").toString());
                        answer.setAnswerD(map.get("Answer D ").toString());
                        answer.setAnswerE(map.get("Answer E ").toString());
                        answer.setRightAnswer(map.get("Right Answer ").toString());
                        answer.setDescription(map.get("Description").toString());
                        answersList.add(answer);
                    }
                }
                answerList.setAdapter(answerAdapter);
                answerAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),"Jumlah pertanyaan: " + answerAdapter.getCount(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
