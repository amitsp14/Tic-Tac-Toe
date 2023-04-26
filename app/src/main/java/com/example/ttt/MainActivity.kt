package com.example.ttt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() , View.OnClickListener{
    var PLAYER = true;
    var TURN_COUNT = 0;

    var board_status = Array(3) { IntArray(3)}
    private lateinit var board : Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(
            arrayOf(button1, button2, button3),
            arrayOf(button4, button5, button6),
            arrayOf(button7, button8, button9)
        )

        for (i in board){

            for (button in i){

                button.setOnClickListener(this)
            }
        }

        initializBoardStatus ()

        resetBTN.setOnClickListener(){
            PLAYER = true
            TURN_COUNT = 0
            initializBoardStatus ()


        }
    }

    private fun initializBoardStatus (){

        for (i in 0..2){

            for( j in 0..2){
                board_status[i][j] = -1

            }
        }

        for (i in board){
            for (button in i){
                button.isEnabled= true
                button.text= ""
            }
        }
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.button1 ->{
                updateValue(row = 0, col = 0, player =  PLAYER )

            }
            R.id.button2 ->{

                updateValue(row = 0, col = 1, player =  PLAYER )
            }
            R.id.button3 ->{

                updateValue(row = 0, col = 2, player =  PLAYER )
            }
            R.id.button4 ->{

                updateValue(row = 1, col = 0, player =  PLAYER )
            }
            R.id.button5 ->{

                updateValue(row = 1, col = 1, player =  PLAYER )
            }
            R.id.button6 ->{

                updateValue(row = 1, col = 2, player =  PLAYER )
            }
            R.id.button7 ->{

                updateValue(row = 2, col = 0, player =  PLAYER )
            }
            R.id.button8 ->{

                updateValue(row = 2, col = 1, player =  PLAYER )
            }
            R.id.button9 ->{

                updateValue(row = 2, col = 2, player =  PLAYER )
            }

        }
        TURN_COUNT++
        PLAYER = !PLAYER

        if (PLAYER){
            update_display("Player X turn")
        }
        else{
            update_display("Player O turn")
        }

        if(TURN_COUNT==9){
            update_display("Game Draw")
        }

        checkWinner()


    }

    private fun checkWinner() {

        //Horizontal rows

        for (i in 0..2){

            if (board_status[i][0]== board_status[i][1] && board_status[i][0]==board_status[i][2]){

                if(board_status[i][0]==1){
                    update_display("Player X is Winner")
                    break
                }
                else if (board_status[i][0]==0) {

                    update_display("Player O is Winner")
                    break
                }
            }

        }

        //vertical Columns
        for (i in 0..2){

            if (board_status[0][i]== board_status[1][i] && board_status[0][i]==board_status[2][i]){

                if(board_status[0][i]==1){
                    update_display("Player X is Winner")
                    break
                }
                else if (board_status[0][i]==0) {

                    update_display("Player O is Winner")
                    break
                }
            }

        }

        // for 1st diagonal
        if (board_status[0][0]== board_status[1][1] && board_status[0][0]==board_status[2][2]){

            if(board_status[0][0]==1){
                update_display("Player X is Winner")

            }
            else if (board_status[0][0]==0) {

                update_display("Player O is Winner")

            }
        }

        // for 2nd diagonal
        if (board_status[0][2]== board_status[1][1] && board_status[0][2]==board_status[2][0]){

            if(board_status[0][2]==1){
                update_display("Player X is Winner")

            }
            else if (board_status[0][2]==0) {

                update_display("Player O is Winner")

            }
        }


    }

    private fun update_display(text: String) {
        displaybar.text= text

        if (text.contains("Winner")){
            disable_button()
        }

    }

    private fun disable_button(){

        for (i in board){

            for (button in i){

                button.isEnabled= false
            }
        }


    }


    private fun updateValue(row: Int, col: Int, player: Boolean) {

        val text = if(player) "X" else "O"
        val value = if(player) 1 else 0


        board[row][col].apply {
            isEnabled= false
            setText(text)
        }

        board_status[row][col]= value
    }
}