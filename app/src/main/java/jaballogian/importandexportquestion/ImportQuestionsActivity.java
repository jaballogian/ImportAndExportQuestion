package jaballogian.importandexportquestion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Random;

import me.anwarshahriar.calligrapher.Calligrapher;

public class ImportQuestionsActivity extends AppCompatActivity {

    private EditText insertQuestion, insertAnswerA, insertAnswerB, insertAnswerC, insertAnswerD, insertAnswerE, insertRightAnswer;
    private Button submit;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private DatabaseReference databaseQuestions;
    private String randomString;
    private ProgressDialog inserting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "PRODUCT_SANS.ttf", true);

        insertQuestion = (EditText) findViewById(R.id.questionEditTextImportActivity);
        insertAnswerA = (EditText) findViewById(R.id.answerAEditTextImportActivity);
        insertAnswerB = (EditText) findViewById(R.id.answerBEditTextImportActivity);
        insertAnswerC = (EditText) findViewById(R.id.answerCEditTextImportActivity);
        insertAnswerD = (EditText) findViewById(R.id.answerDEditTextImportActivity);
        insertAnswerE = (EditText) findViewById(R.id.answerEEditTextImportActivity);
        submit = (Button) findViewById(R.id.submitButtonImportActivity);
        radioGroup = (RadioGroup) findViewById(R.id.rigthAnswerRadioGroupImportActivity);

        randomString = random();

        inserting = new ProgressDialog(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String question = insertQuestion.getText().toString();
                String answerA = insertAnswerA.getText().toString();
                String answerB = insertAnswerB.getText().toString();
                String answerC = insertAnswerC.getText().toString();
                String answerD = insertAnswerD.getText().toString();
                String answerE = insertAnswerE.getText().toString();

                int radioID = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(radioID);

                String rightAnswer = (String) radioButton.getText();

                if(question.isEmpty() || answerA.isEmpty() || answerB.isEmpty() || answerC.isEmpty() || answerD.isEmpty() || answerE.isEmpty() || rightAnswer.isEmpty()){

                    Toast.makeText(ImportQuestionsActivity.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                }
                else{

                    insertQuestionsToDatabase(question, answerA, answerB, answerC, answerD, answerE, rightAnswer);

                    inserting.setTitle("Inserting data");
                    inserting.setMessage("Please wait");
                    inserting.setCanceledOnTouchOutside(false);
                    inserting.show();
                }
            }
        });

    }

    private void insertQuestionsToDatabase(final String question, final String answerA, final String answerB, final String answerC, final String answerD, final String answerE, final String rightAnswer) {

        databaseQuestions = FirebaseDatabase.getInstance().getReference().child("List of Questions").child(randomString);
        HashMap<String, String> dataOfQuestions = new HashMap<>();
        dataOfQuestions.put("Question ", question);
        dataOfQuestions.put("Answer A ", answerA);
        dataOfQuestions.put("Answer B ", answerB);
        dataOfQuestions.put("Answer C ", answerC);
        dataOfQuestions.put("Answer D ", answerD);
        dataOfQuestions.put("Answer E ", answerE);
        dataOfQuestions.put("Right Answer ", rightAnswer);

        databaseQuestions.setValue(dataOfQuestions).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){

                    inserting.dismiss();

                    Toast.makeText(ImportQuestionsActivity.this, "Data berhasil dimasukan ke dalam database", Toast.LENGTH_LONG).show();

                    Intent toChooseActivity = new Intent(ImportQuestionsActivity.this, ChooseActivity.class);
                    toChooseActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(toChooseActivity);
                    finish();
                }
                else{

                    inserting.hide();

                    Toast.makeText(ImportQuestionsActivity.this, "Failed. Please try again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public static String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(10);
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }
}
