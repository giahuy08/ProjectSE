package com.example.projectse.luyennghe;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectse.R;
import com.example.projectse.taikhoan.DatabaseAccess;
import com.example.projectse.taikhoan.NguoiDung;
import com.example.projectse.ui.home.Database;

public class ListeningActivity extends AppCompatActivity {
    final  String DATABASE_NAME = "HocNgonNgu.db";
    SQLiteDatabase database;
    DatabaseAccess DB;
    TextView txtscore,txtquestcount,txtquestion;
    RadioGroup rdgchoices;
    RadioButton btnop1,btnop2,btnop3,btnop4;
    Button btnconfirm;
    ImageView imHA;
    private MediaPlayer mediaPlayer;
    private ImageButton ImgBT;

    NguoiDung user;

    String URL = "https://github.com/Lap2000/songs/raw/main/Bay-Giua-Ngan-Ha-Nam-Cuong.mp3";
    int questioncurrent = 0;
    int questiontrue = 0;
    int answer=0;
    int score=0;
    int idbo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_listening);
        DB = DatabaseAccess.getInstance(getApplicationContext());
        Anhxa();
        LayUser();
        Intent intent=getIntent();
        idbo=intent.getIntExtra("Bo",0);
        shownextquestion(questioncurrent);

        // Create MediaPlayer.
        mediaPlayer=  new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {mp.start();}
        });
        CountDownTimer countDownTimer=new CountDownTimer(3000,300) {
            @Override
            public void onTick(long millisUntilFinished) {
                showanswer();
            }

            @Override
            public void onFinish() {

                btnconfirm.setEnabled(true);
                shownextquestion(questioncurrent);
            }
        };
        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkans();
                questioncurrent++;
                countDownTimer.start();

            }
        });


        // When the video file ready for playback.
        this.ImgBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // http://example.coom/mysong.mp3
                //String mediaURL = MediaPlayerUtils.URL_MEDIA_SAMPLE;
                String mediaURL = URL;
                MediaPlayerUtils.playURLMedia(ListeningActivity.this, mediaPlayer, mediaURL);
                //doStart();
            }
        });
    }
    public void Anhxa(){
        txtscore= findViewById(R.id.txtscore);
        txtquestcount= findViewById(R.id.txtquestcount);
        txtquestion= findViewById(R.id.txtquestion);
        rdgchoices= findViewById(R.id.radiochoices);
        btnop1=findViewById(R.id.op1);
        btnop2=findViewById(R.id.op2);
        btnop3=findViewById(R.id.op3);
        btnop4=findViewById(R.id.op4);
        btnconfirm=findViewById(R.id.btnconfirm);
        imHA=findViewById(R.id.imgHinh);
        ImgBT=findViewById(R.id.ImgBT);
    }

    public void LayUser()
    {
        database = Database.initDatabase(ListeningActivity.this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM NguoiDung WHERE TaiKhoan = ?",new String[]{String.valueOf(DB.username)});
        cursor.moveToNext();
        int Iduser = cursor.getInt(0);
        String HoTen = cursor.getString(1);
        String TaiKhoan = cursor.getString(2);
        String MatKhau = cursor.getString(3);
        int Point = cursor.getInt(4);
        String Email = cursor.getString(5);
        String SDT = cursor.getString(6);
        user = new NguoiDung(Iduser,HoTen,TaiKhoan,MatKhau,Point,Email,SDT);
    }

    public void shownextquestion(int pos){

        if(pos > 0 ) doStop();
        database= Database.initDatabase(ListeningActivity.this,DATABASE_NAME);
        String a=null;
        Cursor cursor=database.rawQuery("SELECT * FROM LuyenNghe WHERE ID_Bo=?",new String[]{String.valueOf(idbo)});
        txtquestcount.setText("Question: "+(questioncurrent+1)+"/"+cursor.getCount()+"");
        rdgchoices.clearCheck();
        btnop1.setTextColor(ColorStateList.valueOf(Color.BLACK));
        btnop2.setTextColor(ColorStateList.valueOf(Color.BLACK));
        btnop3.setTextColor(ColorStateList.valueOf(Color.BLACK));
        btnop4.setTextColor(ColorStateList.valueOf(Color.BLACK));
        if(pos==cursor.getCount()){
            DB.capnhatdiem(DB.username,user.getPoint(),score);
            Intent intent=new Intent(ListeningActivity.this, FinishQuizLSActivity.class);
            intent.putExtra("score",score);
            intent.putExtra("questiontrue", questiontrue);
            intent.putExtra("qcount", pos);
            startActivity(intent);
        }
        for(int i=pos;i<cursor.getCount();i++){
            cursor.moveToPosition(i);
            byte[] anh = cursor.getBlob(7);
            Bitmap img= BitmapFactory.decodeByteArray(anh,0,anh.length);
            imHA.setImageBitmap(img);
            String URLaudio = cursor.getString(8);
            URL = URLaudio;
            //String questcontent=cursor.getString(8);
            String op1=cursor.getString(2);
            String op2=cursor.getString(3);
            String op3=cursor.getString(4);
            String op4=cursor.getString(5);
            String ans=cursor.getString(6);
            answer=Integer.valueOf(ans);
            btnop1.setText(op1);
            btnop2.setText(op2);
            btnop3.setText(op3);
            btnop4.setText(op4);

            break;
        }



    }
    public void checkans(){
        btnconfirm.setEnabled(false);
        if(btnop1.isChecked()){
            if(1==answer) {

                score+=5;
                questiontrue++;
            }
        }
        if(btnop2.isChecked()){
            if(2==answer) {

                score+=5;
                questiontrue++;
            }
        }
        if(btnop3.isChecked()){
            if(3==answer) {

                score+=5;
                questiontrue++;
            }
        }
        if(btnop4.isChecked()){
            if(4==answer) {

                score+=5;
                questiontrue++;
            }
        }

        txtscore.setText("Score: "+score+"");
    }
    public void showanswer(){
        if(1==answer) {
            btnop1.setTextColor(ColorStateList.valueOf(Color.GREEN));
            btnop2.setTextColor(ColorStateList.valueOf(Color.RED));
            btnop3.setTextColor(ColorStateList.valueOf(Color.RED));
            btnop4.setTextColor(ColorStateList.valueOf(Color.RED));

        }
        if(2==answer) {
            btnop2.setTextColor(ColorStateList.valueOf(Color.GREEN));
            btnop1.setTextColor(ColorStateList.valueOf(Color.RED));
            btnop3.setTextColor(ColorStateList.valueOf(Color.RED));
            btnop4.setTextColor(ColorStateList.valueOf(Color.RED));

        }
        if(3==answer) {
            btnop3.setTextColor(ColorStateList.valueOf(Color.GREEN));
            btnop2.setTextColor(ColorStateList.valueOf(Color.RED));
            btnop1.setTextColor(ColorStateList.valueOf(Color.RED));
            btnop4.setTextColor(ColorStateList.valueOf(Color.RED));

        }
        if(4==answer) {
            btnop4.setTextColor(ColorStateList.valueOf(Color.GREEN));
            btnop2.setTextColor(ColorStateList.valueOf(Color.RED));
            btnop3.setTextColor(ColorStateList.valueOf(Color.RED));
            btnop1.setTextColor(ColorStateList.valueOf(Color.RED));

        }
    }
    private void doStart( )  {
        if(this.mediaPlayer.isPlaying()) {
            //this.mediaPlayer.stop();
            this.mediaPlayer.pause();
            this.mediaPlayer.reset();
        }
        else {this.mediaPlayer.start();}
    }
    private void doStop()  {
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
    }
}
