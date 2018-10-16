package jaballogian.importandexportquestion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import me.anwarshahriar.calligrapher.Calligrapher;

public class ChooseActivity extends AppCompatActivity {

    private Button importQuestions, showQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "PRODUCT_SANS.ttf", true);

        importQuestions = (Button) findViewById(R.id.importQuestionsButtonChooseActivity);
        showQuestions = (Button) findViewById(R.id.showQuestionsButtonChooseActivity);

        importQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toImportQuestionsActivity = new Intent(ChooseActivity.this, ImportQuestionsActivity.class);
                startActivity(toImportQuestionsActivity);
                finish();
            }
        });

        showQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toShowQuestionsActivity = new Intent(ChooseActivity.this, ShowQuestionsActivity.class);
                startActivity(toShowQuestionsActivity);
                finish();
            }
        });
    }
}
