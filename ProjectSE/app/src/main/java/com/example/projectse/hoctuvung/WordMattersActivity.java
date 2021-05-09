package com.example.projectse.hoctuvung;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectse.R;
import com.example.projectse.ui.home.Database;

import java.util.ArrayList;
import java.util.Random;

public class WordMattersActivity extends AppCompatActivity {


    final  String DATABASE_NAME = "HocNgonNgu.db";
    SQLiteDatabase database;

    private int presCounter = 0;
    private String[] keys = {"a", "b", "c", "d", "e", "f", "g",
            //"h", "i", "j", "k", "l", "m", "n",
            //"o", "p", "q", "r", "s", "t", "u",
            "v", "w", "x", "y", "z"};



    private String textAnswer = "SUNNYSKY";
    private int maxPresCounter = 0;
    private TextView textScreen, textQuestion, textTitle;
    private TextView tvWordCount, tvScore;
    private ImageView imgview;
    private ArrayList<TuVung> DStuvung;
    private Animation smallbigforth;
    private int idbo;
    private int score = 0;
    private int dem;
    private int tu = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_matters);
        AnhXa();

        Intent intent = getIntent();
        idbo = intent.getIntExtra("idbo",0);
        DStuvung = new ArrayList<>();
        AddArrayTV();

        Bitmap img= BitmapFactory.decodeByteArray(DStuvung.get(0).getAnh(),0,DStuvung.get(0).getAnh().length);
        imgview.setImageBitmap(img);
        textQuestion.setText("("+DStuvung.get(0).getLoaitu()+") - " + "("+DStuvung.get(0).getDichnghia()+")");
        tvWordCount.setText("Word: " + tu + "/" + DStuvung.size());
        tvScore.setText("Score: " + score);

        textAnswer = DStuvung.get(0).getDapan();


        smallbigforth = AnimationUtils.loadAnimation(this, R.anim.smallbigforth);

        for (int i = 0; i < textAnswer.length(); i++) {
            keys[i] = String.valueOf(textAnswer.charAt(i));
        }

        keys = shuffleArray(keys);

        dem = 0;
        while (dem<keys.length)
        {
            if (dem < 4) {
                addView(((LinearLayout) findViewById(R.id.layoutParent1)), keys[dem], ((EditText) findViewById(R.id.editText)));
            }
            else if (dem <8)
            {
                addView(((LinearLayout) findViewById(R.id.layoutParent2)), keys[dem], ((EditText) findViewById(R.id.editText)));
            }
            else addView(((LinearLayout) findViewById(R.id.layoutParent3)), keys[dem], ((EditText) findViewById(R.id.editText)));
            dem++;
        }

        maxPresCounter = textAnswer.length();
    }

    private  void AnhXa()
    {
        textQuestion = (TextView) findViewById(R.id.textQuestion);
        imgview = (ImageView) findViewById(R.id.imgview);
        textScreen = (TextView) findViewById(R.id.textScreen);
        textTitle = (TextView) findViewById(R.id.textTitle);
        tvWordCount = (TextView) findViewById(R.id.tvWord);
        tvScore = (TextView) findViewById(R.id.tvScore);
    }
    private void AddArrayTV(){
        database = Database.initDatabase(WordMattersActivity.this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM TuVung WHERE ID_Bo = ?",new String[]{String.valueOf(idbo)});
        DStuvung.clear();

        for (int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            int idtu = cursor.getInt(0);
            int idbo = cursor.getInt(1);
            String dapan = cursor.getString(2);
            String dichnghia = cursor.getString(3);
            String loaitu = cursor.getString(4);
            String audio = cursor.getString(5);
            byte[] anh = cursor.getBlob(6);

            DStuvung.add(new TuVung(idtu,idbo,dapan,dichnghia,loaitu,audio,anh));
        }
    }


    private String[] shuffleArray(String[] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;
    }


    private void addView(LinearLayout viewParent, final String text, final EditText editText) {
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        linearLayoutParams.rightMargin = 30;

        final TextView textView = new TextView(this);

        textView.setLayoutParams(linearLayoutParams);
        textView.setBackground(this.getResources().getDrawable(R.drawable.bgpink));
        textView.setTextColor(this.getResources().getColor(R.color.colorPurple));
        textView.setGravity(Gravity.CENTER);
        textView.setText(text);
        textView.setClickable(true);
        textView.setFocusable(true);
        textView.setTextSize(35);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FredokaOneRegular.ttf");

//        textQuestion = (TextView) findViewById(R.id.textQuestion);
//        imgview = (ImageView) findViewById(R.id.imgview);
//        textScreen = (TextView) findViewById(R.id.textScreen);
//        textTitle = (TextView) findViewById(R.id.textTitle);

        //textQuestion.setTypeface(typeface);
        textScreen.setTypeface(typeface);
        textTitle.setTypeface(typeface);
        editText.setTypeface(typeface);
        textView.setTypeface(typeface);
        tvScore.setTypeface(typeface);
        tvWordCount.setTypeface(typeface);

        textView.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if(presCounter < maxPresCounter) {
                    if (presCounter == 0)
                        editText.setText("");

                    editText.setText(editText.getText().toString() + text);
                    textView.startAnimation(smallbigforth);
                    textView.animate().alpha(0).setDuration(300);
                    presCounter++;
                    textView.setClickable(false);

                    if (presCounter == maxPresCounter)
                        doValidate();
                }
            }
        });


        viewParent.addView(textView);


    }


    private void doValidate() {
        presCounter = 0;

        EditText editText = findViewById(R.id.editText);
        LinearLayout linearLayout1 = findViewById(R.id.layoutParent1);
        LinearLayout linearLayout2 = findViewById(R.id.layoutParent2);
        LinearLayout linearLayout3 = findViewById(R.id.layoutParent3);

        if(editText.getText().toString().equals(textAnswer)) {
//            Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();

//            Intent a = new Intent(WordMattersActivity.this,FinishHTVActivity.class);
//            startActivity(a);
            if(tu == DStuvung.size())
            {
                Toast.makeText(WordMattersActivity.this, "Hoàn Thành Xuất Sắc!!~(^.^)~", Toast.LENGTH_SHORT).show();
                Intent a = new Intent(WordMattersActivity.this,FinishHTVActivity.class);
                startActivity(a);
                finish();
            }
            else {
                Toast.makeText(WordMattersActivity.this, "Chính xác!!(^.^)", Toast.LENGTH_SHORT).show();
//                textAnswer = listtu.get(tu);
                Bitmap img= BitmapFactory.decodeByteArray(DStuvung.get(tu).getAnh(),0,DStuvung.get(tu).getAnh().length);
                imgview.setImageBitmap(img);
                textQuestion.setText("("+DStuvung.get(tu).getLoaitu()+") - " + "("+DStuvung.get(tu).getDichnghia()+")");
                textAnswer = DStuvung.get(tu).getDapan();
                for (int i = 0; i < textAnswer.length(); i++)
                {
                    keys[i] = String.valueOf(textAnswer.charAt(i));
                }
                maxPresCounter = textAnswer.length();
                editText.setText("");
                score +=5;
                tu++;
                tvWordCount.setText("Word: " + tu + "/" + DStuvung.size());
                tvScore.setText("Score: " + score);
            }

        } else {
            Toast.makeText(WordMattersActivity.this, "Sai rồi!!(T.T)", Toast.LENGTH_SHORT).show();
            editText.setText("");
        }

        keys = shuffleArray(keys);
        linearLayout1.removeAllViews();
        linearLayout2.removeAllViews();
        linearLayout3.removeAllViews();

        dem = 0;
        while (dem<keys.length)
        {
            if (dem < 4) {
                addView(linearLayout1, keys[dem], editText);
            }
            else if (dem < 8)
            {
                addView(linearLayout2, keys[dem], editText);
            }
            else addView(linearLayout3, keys[dem], editText);
            dem++;
        }
//        for (String key : keys) {
//            addView(linearLayout, key, editText);
//        }

    }



}