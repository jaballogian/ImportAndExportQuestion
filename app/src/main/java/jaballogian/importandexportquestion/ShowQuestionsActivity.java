package jaballogian.importandexportquestion;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;

public class ShowQuestionsActivity extends AppCompatActivity {

    private ListView answerList;
    private DatabaseReference databaseQuestions;
    private ArrayList<Answer> answersList;
    private ArrayAdapter<Answer> answerAdapter;
    private Answer answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_questions);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "PRODUCT_SANS.ttf", true);

        answerList = (ListView) findViewById(R.id.listQuestionListViewShowQuestionActivity);

        answersList = new ArrayList<>();
        answerAdapter = new ArrayAdapter<Answer>(this, R.layout.answer_info, answersList);

        answer = new Answer();

        databaseQuestions = FirebaseDatabase.getInstance().getReference("List of Questions");
        databaseQuestions.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds: dataSnapshot.getChildren()){

                    answer = ds.getValue(Answer.class);
                    answersList.add(answer);
                }
                answerList.setAdapter(answerAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
