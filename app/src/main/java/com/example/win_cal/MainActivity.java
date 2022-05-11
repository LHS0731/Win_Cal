package com.example.win_cal;

//2017142032 이현섭
//최종수정일 2022/05/10

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText workWindow, resultWindow;
    private Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9;
    private Button btn_add, btn_sub, btn_mul, btn_div, btn_equal, btn_plsmin, btn_dot;
    private Button btn_CE, btn_C, btn_bcksp;
    private Button btn_MC, btn_MR, btn_Mpls, btn_Mmin, btn_MS;
    private Button btn_perc, btn_root, btn_power, btn_frac;

    private boolean ADD, SUB, MUL, DIV, check, set, dot_ok;
    private BigDecimal A, B, temp_Num, Memory, minus, temp_mem;
    private float num1, num2;
    private String RESULT;

    //ADD, SUB, MUL, DIV 는 사칙연산 제어를 위한 용도
    //check은 연산기호, = 을 눌렀을때 그 다음에 숫자를 누르면 새로 쓰게 해주는 용도
    //set은 =을 눌렀을때 그 다음에 숫자를 누르면 workwindow가 초기화 됨
    //dot_ok는 소숫점 제어를 위한 용도 재사용 방지

    //A,B는 두 입력된 수의 계산을 위한 변수
    //temp_Num은 +-를 위한 임시 저장 변수
    //Memory는 메모리를 나타내기 위한 변수
    //minus는 -1을 곱할때 사용.
    //temp_mem은 메모리 덧셈,뺄셈시 이용
    //num1, num2 나눗셈 계산에 이용

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        workWindow = (EditText) findViewById(R.id.screen1);
        resultWindow = (EditText) findViewById(R.id.screen2);

        btn_0 = (Button)findViewById(R.id.btn_0);
        btn_0.setOnClickListener(this);
        btn_1 = (Button)findViewById(R.id.btn_1);
        btn_1.setOnClickListener(this);
        btn_2 = (Button)findViewById(R.id.btn_2);
        btn_2.setOnClickListener(this);
        btn_3 = (Button)findViewById(R.id.btn_3);
        btn_3.setOnClickListener(this);
        btn_4 = (Button)findViewById(R.id.btn_4);
        btn_4.setOnClickListener(this);
        btn_5 = (Button)findViewById(R.id.btn_5);
        btn_5.setOnClickListener(this);
        btn_6 = (Button)findViewById(R.id.btn_6);
        btn_6.setOnClickListener(this);
        btn_7 = (Button)findViewById(R.id.btn_7);
        btn_7.setOnClickListener(this);
        btn_8 = (Button)findViewById(R.id.btn_8);
        btn_8.setOnClickListener(this);
        btn_9 = (Button)findViewById(R.id.btn_9);
        btn_9.setOnClickListener(this);
        btn_8 = (Button)findViewById(R.id.btn_8);
        btn_8.setOnClickListener(this);
        btn_9 = (Button)findViewById(R.id.btn_9);
        btn_9.setOnClickListener(this);

        btn_add = (Button)findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
        btn_sub = (Button)findViewById(R.id.btn_sub);
        btn_sub.setOnClickListener(this);
        btn_mul = (Button)findViewById(R.id.btn_mul);
        btn_mul.setOnClickListener(this);
        btn_div = (Button)findViewById(R.id.btn_div);
        btn_div.setOnClickListener(this);
        btn_equal = (Button)findViewById(R.id.btn_equal);
        btn_equal.setOnClickListener(this);
        btn_plsmin = (Button)findViewById(R.id.btn_plsmin);
        btn_plsmin.setOnClickListener(this);
        btn_dot = (Button)findViewById(R.id.btn_dot);
        btn_dot.setOnClickListener(this);

        btn_CE = (Button)findViewById(R.id.btn_CE);
        btn_CE.setOnClickListener(this);
        btn_C = (Button)findViewById(R.id.btn_C);
        btn_C.setOnClickListener(this);
        btn_bcksp = (Button)findViewById(R.id.btn_bcksp);
        btn_bcksp.setOnClickListener(this);

        btn_MC = (Button)findViewById(R.id.btn_MC);
        btn_MC.setOnClickListener(this);
        btn_MR= (Button)findViewById(R.id.btn_MR);
        btn_MR.setOnClickListener(this);
        btn_Mpls= (Button)findViewById(R.id.btn_Mpls);
        btn_Mpls.setOnClickListener(this);
        btn_Mmin= (Button)findViewById(R.id.btn_Mmin);
        btn_Mmin.setOnClickListener(this);
        btn_MS= (Button)findViewById(R.id.btn_MS);
        btn_MS.setOnClickListener(this);

        btn_perc= (Button)findViewById(R.id.btn_perc);
        btn_perc.setOnClickListener(this);
        btn_root= (Button)findViewById(R.id.btn_root);
        btn_root.setOnClickListener(this);
        btn_power= (Button)findViewById(R.id.btn_power);
        btn_power.setOnClickListener(this);
        btn_frac= (Button)findViewById(R.id.btn_frac);
        btn_frac.setOnClickListener(this);

        ADD = false;
        SUB = false;
        MUL = false;
        DIV = false;
        check = false;
        set = false;
        dot_ok = false;

        A = new BigDecimal("0");
        B = new BigDecimal("0");
        minus = new BigDecimal("-1");
        Memory = new BigDecimal("0");
        temp_Num = new BigDecimal("0");
        temp_mem = new BigDecimal("0");

        num1 = 0;
        num2 = 0;

        RESULT = "";

        resultWindow.setText("0");
        }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_0: {
                if(resultWindow.getText().toString().equals("0")){
                    resultWindow.setText("");
                }
                if (set == true) {
                    workWindow.setText("");
                    set = false;
                }
                if (check == false) {
                    resultWindow.setText(resultWindow.getText() + "0");
                }
                if (check == true) {
                    resultWindow.setText("");
                    resultWindow.setText(resultWindow.getText() + "0");
                    check = false;
                }
                break;
            }
            case R.id.btn_1:{
                if(resultWindow.getText().toString().equals("0")){
                    resultWindow.setText("");
                }
                if (set == true) {
                    workWindow.setText("");
                    set = false;
                }
                if (check == false) {
                    resultWindow.setText(resultWindow.getText() + "1");
                }
                if (check == true) {
                    resultWindow.setText("");
                    resultWindow.setText(resultWindow.getText() + "1");
                    check = false;
                }
                break;
            }
            case R.id.btn_2:{
                if(resultWindow.getText().toString().equals("0")){
                    resultWindow.setText("");
                }
                if (set == true) {
                    workWindow.setText("");
                    set = false;
                }
                if (check == false) {
                    resultWindow.setText(resultWindow.getText() + "2");
                }
                if (check == true) {
                    resultWindow.setText("");
                    resultWindow.setText(resultWindow.getText() + "2");
                    check = false;
                }
                break;
            }
            case R.id.btn_3:{
                if(resultWindow.getText().toString().equals("0")){
                    resultWindow.setText("");
                }
                if (set == true) {
                    workWindow.setText("");
                    set = false;
                }
                if (check == false) {
                    resultWindow.setText(resultWindow.getText() + "3");
                }
                if (check == true) {
                    resultWindow.setText("");
                    resultWindow.setText(resultWindow.getText() + "3");
                    check = false;
                }
                break;
            }
            case R.id.btn_4:{
                if(resultWindow.getText().toString().equals("0")){
                    resultWindow.setText("");
                }
                if (set == true) {
                    workWindow.setText("");
                    set = false;
                }
                if (check == false) {
                    resultWindow.setText(resultWindow.getText() + "4");
                }
                if (check == true) {
                    resultWindow.setText("");
                    resultWindow.setText(resultWindow.getText() + "4");
                    check = false;
                }
                break;
            }
            case R.id.btn_5:{
                if(resultWindow.getText().toString().equals("0")){
                    resultWindow.setText("");
                }
                if (set == true) {
                    workWindow.setText("");
                    set = false;
                }
                if (check == false) {
                    resultWindow.setText(resultWindow.getText() + "5");
                }
                if (check == true) {
                    resultWindow.setText("");
                    resultWindow.setText(resultWindow.getText() + "5");
                    check = false;
                }
                break;
            }
            case R.id.btn_6:{
                if(resultWindow.getText().toString().equals("0")){
                    resultWindow.setText("");
                }
                if (set == true) {
                    workWindow.setText("");
                    set = false;
                }
                if (check == false) {
                    resultWindow.setText(resultWindow.getText() + "6");
                }
                if (check == true) {
                    resultWindow.setText("");
                    resultWindow.setText(resultWindow.getText() + "6");
                    check = false;
                }
                break;
            }
            case R.id.btn_7:{
                if(resultWindow.getText().toString().equals("0")){
                    resultWindow.setText("");
                }
                if (set == true) {
                    workWindow.setText("");
                    set = false;
                }
                if (check == false) {
                    resultWindow.setText(resultWindow.getText() + "7");
                }
                if (check == true) {
                    resultWindow.setText("");
                    resultWindow.setText(resultWindow.getText() + "7");
                    check = false;
                }
                break;
            }
            case R.id.btn_8:{
                if(resultWindow.getText().toString().equals("0")){
                    resultWindow.setText("");
                }
                if (set == true) {
                    workWindow.setText("");
                    set = false;
                }
                if (check == false) {
                    resultWindow.setText(resultWindow.getText() + "8");
                }
                if (check == true) {
                    resultWindow.setText("");
                    resultWindow.setText(resultWindow.getText() + "8");
                    check = false;
                }
                break;
            }
            case R.id.btn_9:{
                if(resultWindow.getText().toString().equals("0")){
                    resultWindow.setText("");
                }
                if (set == true) {
                    workWindow.setText("");
                    set = false;
                }
                if (check == false) {
                    resultWindow.setText(resultWindow.getText() + "9");
                }
                if (check == true) {
                    resultWindow.setText("");
                    resultWindow.setText(resultWindow.getText() + "9");
                    check = false;
                }
                break;
            }

            case R.id.btn_add: {
                A = new BigDecimal((resultWindow.getText().toString()));
                workWindow.setText(resultWindow.getText() + "+");
                check = true;
                set = false;
                ADD = true;
                dot_ok = false;
                break;
            }

            case R.id.btn_sub: {
                A = new BigDecimal((resultWindow.getText().toString()));
                workWindow.setText(resultWindow.getText() + "-");
                check = true;
                set = false;
                SUB = true;
                dot_ok = false;
                break;
            }

            case R.id.btn_mul:{
                A = new BigDecimal((resultWindow.getText().toString()));
                workWindow.setText(resultWindow.getText() + "*");
                check = true;
                set = false;
                MUL = true;
                dot_ok = false;
                break;
            }

            case R.id.btn_div:{
                num1 = Float.parseFloat(resultWindow.getText().toString());
                workWindow.setText(resultWindow.getText() + "/");
                check = true;
                set = false;
                DIV = true;
                dot_ok = false;
                break;
            }

            case R.id.btn_equal: {
                B = new BigDecimal((resultWindow.getText().toString()));
                workWindow.append(resultWindow.getText() + "=");

                if (ADD == true) {
                    resultWindow.setText(A.add(B)+"");
                    ADD = false;
                }
                if (SUB == true) {
                    resultWindow.setText(A.subtract(B)+"");
                    SUB = false;
                }
                if (MUL == true) {
                    resultWindow.setText(A.multiply(B)+"");
                    MUL = false;
                }
                if (DIV == true) {
                    num2 = Float.parseFloat(resultWindow.getText().toString());
                    resultWindow.setText(num1/num2+"");
                    String str = resultWindow.getText().toString();
                    if(str.substring(str.length()-2, str.length()).equals(".0")) {
                        str = str.substring(0, str.length() - 2);
                        resultWindow.setText(str);
                    }
                    DIV = false;
                }
                check = true;
                set = true;
                dot_ok = false;
                break;
            }
            case R.id.btn_C:{
                resultWindow.setText("0");
                workWindow.setText("");
                A = new BigDecimal("0");
                B = new BigDecimal("0");
                num1 = 0;
                num2 = 0;
                ADD = false;
                SUB = false;
                MUL = false;
                DIV = false;
                check = false;
                set = false;
                dot_ok = false;
                break;
            }
            case R.id.btn_CE:{
                resultWindow.setText("0");
                break;
            }
            case R.id.btn_bcksp:{
                String str = resultWindow.getText().toString();
                if(resultWindow.getText().toString().equals("0")){
                    break;
                }
                if (str.length() >=1 ) {
                    str = str.substring(0, str.length() - 1);
                    resultWindow.setText(str);
                    if(resultWindow.getText().toString().equals("")){
                        resultWindow.setText("0");
                    }
                }
                break;
            }
            case R.id.btn_dot:{
                if(dot_ok == false) {
                    if (check == false) {
                        resultWindow.setText(resultWindow.getText() + ".");
                    }
                    if (check == true) {
                        resultWindow.setText("");
                        resultWindow.setText(resultWindow.getText() + "0.");
                        check = false;
                    }
                    dot_ok = true;
                }
                break;
            }

            case R.id.btn_plsmin:{
                if(resultWindow.getText().toString().equals("0")){
                    break;
                }
                temp_Num = new BigDecimal(resultWindow.getText().toString());
                temp_Num = temp_Num.multiply(minus);
                resultWindow.setText(temp_Num+"");
                break;
            }
            case R.id.btn_MC:{
                Memory = new BigDecimal("0");
                check = true;
                break;
            }
            case R.id.btn_MR:{
                resultWindow.setText(Memory+"");
                check = true;
                break;
            }
            case R.id.btn_Mpls:{
                temp_mem = new BigDecimal(resultWindow.getText().toString());
                Memory = Memory.add(temp_mem);
                check = true;
                break;
            }
            case R.id.btn_Mmin:{
                temp_mem = new BigDecimal(resultWindow.getText().toString());
                Memory = Memory.subtract(temp_mem);
                check = true;
                break;
            }
            case R.id.btn_MS:{
                Memory = new BigDecimal(resultWindow.getText().toString());
                check = true;
                break;
            }
            case R.id.btn_perc:{
                break;
            }
            case R.id.btn_root:{
                double result_root;
                result_root = Double.parseDouble(resultWindow.getText().toString());
                result_root = Math.sqrt(result_root);
                resultWindow.setText(result_root+"");
                RESULT = resultWindow.getText().toString();
                if(RESULT.substring(RESULT.length()-2,RESULT.length()-0).equals(".0")){
                    resultWindow.setText(RESULT.substring(0,RESULT.length()-2));
                }
            }
            case R.id.btn_power:{
                double result_power;
                result_power = Double.parseDouble(resultWindow.getText().toString());
                result_power = Math.pow(result_power, 2);
                resultWindow.setText(result_power+"");
                RESULT = resultWindow.getText().toString();
                if(RESULT.substring(RESULT.length()-2,RESULT.length()-0).equals(".0")){
                    resultWindow.setText(RESULT.substring(0,RESULT.length()-2));
                }
                break;
            }
            case R.id.btn_frac:{
                double result_frac;
                result_frac = Double.parseDouble(resultWindow.getText().toString());
                result_frac = 1/result_frac;
                resultWindow.setText(result_frac+"");
                break;
            }
        }
    }
}