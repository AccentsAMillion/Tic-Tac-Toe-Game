package com.example.jpineda.tic;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * INSTRUCTIONS:
 *
 * This code is complete.  No changes necessary.
 *
 * Build a UI that will support this code. Read the code that will help
 * you determine how to name the controls on the page and the layout file.
 * (HINT:  Look at what is in red, this is whats missing)
 *
 * Create 9 buttons that will be used for the Tic Tac Toe Game
 *
 */
public class MainActivity extends AppCompatActivity {

    boolean player1, winnerFound; // X player
    int moves = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_you);
        player1 = true;
        winnerFound = false;
    }

    public void move (View v){
        Button btn = (Button)findViewById(v.getId());
        if (setButton(btn)) {
            checkWinner(v);
        }
    }

    private boolean setButton(Button btn){
        if(btn.getText().equals("X") || btn.getText().equals("O"))
            return false;

        if(player1)
        {
            btn.setText("X");
            player1 =false;
        }
        else
        {
            btn.setText("O");
            player1=true;
        }
        moves++;
        return true;

    }

    private void checkWinner(View v){
        String b0 = ((Button)findViewById(R.id.button0)).getText().toString();
        String b1 = ((Button)findViewById(R.id.button1)).getText().toString();
        String b2 = ((Button)findViewById(R.id.button2)).getText().toString();
        String b3 = ((Button)findViewById(R.id.button3)).getText().toString();
        String b4 = ((Button)findViewById(R.id.button4)).getText().toString();
        String b5 = ((Button)findViewById(R.id.button5)).getText().toString();
        String b6 = ((Button)findViewById(R.id.button6)).getText().toString();
        String b7 = ((Button)findViewById(R.id.button7)).getText().toString();
        String b8 = ((Button)findViewById(R.id.button8)).getText().toString();

        isWinner(v,b0,b1,b2);
        isWinner(v,b3,b4,b5);
        isWinner(v,b6,b7,b8);
        isWinner(v,b0,b3,b6);
        isWinner(v,b1,b4,b7);
        isWinner(v,b2,b5,b8);
        isWinner(v,b0,b4,b8);
        isWinner(v,b2,b4,b6);

        if(moves==9)
            displayWinner(v);


    }

    private void displayWinner(View v){
        String player = player1 ? "O" : "X";
        String message = "Player " + player + " wins!";
        String title = "Winner";

        if(moves==9 && !winnerFound) {
            message = "Its a draw!!!";
            title = "Draw";
        }

        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

        dlgAlert.setMessage(message);
        dlgAlert.setTitle(title);

        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        dlgAlert.create().show();
    }


    private boolean isWinner(View v, String s1, String s2, String s3){

        if ((s1.equals(s2) && s2.equals(s3)) && (!s1.isEmpty() || !s2.isEmpty() || !s3.isEmpty())) {
            winnerFound = true;
            displayWinner(v);
        }
        return false;
    }
}
