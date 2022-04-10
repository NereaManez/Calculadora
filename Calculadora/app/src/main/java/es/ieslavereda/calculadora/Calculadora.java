package es.ieslavereda.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Calculadora extends AppCompatActivity implements View.OnClickListener {

    private Button c_button, del_button, pc_button, div_button, mult_button, sum_button, res_button,
            ac_button, decimal_button, calc_button, b9, b8, b7, b6, b5, b4, b3, b2, b1, b0;
    private TextView tv_res;
    private char oper = 0;
    private CheckBox checkBox;
    private RadioGroup radioGroup;
    private RadioButton rb_suma, rb_resta, rb_mult, rb_div;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        setButtons();
        tv_res = findViewById(R.id.resultado);
        tv_res.setText("0");

        b0.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        c_button.setOnClickListener(this);
        del_button.setOnClickListener(this);
        sum_button.setOnClickListener(this);
        res_button.setOnClickListener(this);
        mult_button.setOnClickListener(this);
        div_button.setOnClickListener(this);
        calc_button.setOnClickListener(this);
        decimal_button.setOnClickListener(this);

        checkBox = findViewById(R.id.checkBox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked())
                    radioGroup.setVisibility(View.VISIBLE);
                else {
                    rb_suma.setChecked(false);
                    rb_resta.setChecked(false);
                    rb_mult.setChecked(false);
                    rb_div.setChecked(false);

                    sum_button.setClickable(true);
                    res_button.setClickable(true);
                    div_button.setClickable(true);
                    mult_button.setClickable(true);

                    radioGroup.setVisibility(View.GONE);
                }
            }
        });
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setVisibility(View.GONE);
        rb_suma = findViewById(R.id.rb_suma);
        rb_suma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum_button.setClickable(false);
                res_button.setClickable(true);
                div_button.setClickable(true);
                mult_button.setClickable(true);
            }
        });
        rb_resta = findViewById(R.id.rb_resta);
        rb_resta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum_button.setClickable(true);
                res_button.setClickable(false);
                div_button.setClickable(true);
                mult_button.setClickable(true);
            }
        });
        rb_mult = findViewById(R.id.rb_mult);
        rb_mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum_button.setClickable(true);
                res_button.setClickable(true);
                div_button.setClickable(true);
                mult_button.setClickable(false);
            }
        });
        rb_div = findViewById(R.id.rb_div);
        rb_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum_button.setClickable(true);
                res_button.setClickable(true);
                div_button.setClickable(false);
                mult_button.setClickable(true);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v instanceof Button) {
            Button b = (Button) v;
            if (b == c_button) {
                tv_res.setText("0");
                decimal_button.setClickable(true);
            }
            else if (b == del_button) {
                if (tv_res.getText().charAt(tv_res.getText().length() - 1) == '.')
                    decimal_button.setClickable(true);
                else if (tv_res.getText().charAt(tv_res.getText().length() - 1) == oper)
                    oper = 0;

                if (tv_res.getText().length() != 1)
                    tv_res.setText(tv_res.getText().subSequence(0,tv_res.getText().length()-1).toString());
                else
                    tv_res.setText("0");
            } else if (b == sum_button || b == res_button || b == mult_button || b == div_button) {
                if (tv_res.getText().toString() != "0") {
                    char compare = tv_res.getText().charAt(tv_res.getText().length()-1);
                    if (oper == 0) {
                        oper = b.getTag().toString().charAt(0);
                        tv_res.setText(tv_res.getText().toString() + oper);
                    } else if (compare == '.') {
                        tv_res.setText(tv_res.getText().toString() + "0");
                        tv_res.setText(calcNumbers() + b.getTag().toString().charAt(0));
                        oper = b.getTag().toString().charAt(0);
                    } else if (compare == '+' || compare == '-' || compare == '/' || compare == 'x') {
                        oper = b.getTag().toString().charAt(0);
                        tv_res.setText(tv_res.getText().subSequence(0,tv_res.getText().length()-1).toString() + oper);
                    } else {
                        tv_res.setText(calcNumbers() + b.getTag().toString().charAt(0));
                        oper = b.getTag().toString().charAt(0);
                    }
                    decimal_button.setClickable(true);
                }
            } else if (b == calc_button) {
                if (oper != 0) {
                    tv_res.setText(calcNumbers());
                    oper = 0;
                    decimal_button.setClickable(false);
                }
            } else if (b == decimal_button) {
                char compare = tv_res.getText().charAt(tv_res.getText().length()-1);
                if (compare == '0' || compare == '1' || compare == '2' || compare == '3' || compare == '4'
                        || compare == '5' || compare == '6' || compare == '7' || compare == '8' || compare == '9') {
                    tv_res.setText(tv_res.getText().toString() + b.getTag().toString().charAt(0));
                    decimal_button.setClickable(false);
                }
            } else
                setTv_resText(b);
        }
    }

    private String calcNumbers() {
        String num1 = "", num2 = "";
        int i = 0;
        String res;
        while (tv_res.getText().toString().charAt(i) != oper) {
            num1 += tv_res.getText().toString().charAt(i);
            i++;
        }

        while (tv_res.getText().toString().length() > i) {
            num2 += tv_res.getText().toString().charAt(i);
            i++;
        }

        if (!num2.equals("")) {
            res = String.valueOf(calcula(num1, num2, oper));
            oper = 0;
        } else
            res = tv_res.getText().toString();

        return res;
    }

    private float calcula(String a, String b, char oper) {
        float res = 0f;
        float float_a = Float.parseFloat(a.replace("null", ""));
        float float_b = Float.parseFloat(b.replace("null", ""));

        switch (oper) {
            case '+' :
                res = float_a + float_b;
                break;

            case '-' :
                res = float_a - float_b;
                break;

            case 'x' :
                res = float_a * float_b;
                break;

            case '/' :
                res = float_a / float_b;
                break;
        }

        return res;
    }

    private void setTv_resText (Button b) {
        if (tv_res.getText() == "0")
            tv_res.setText(b.getTag().toString());
        else
            tv_res.setText(tv_res.getText() + b.getTag().toString());
    }

    private void setButtons() {
        c_button = findViewById(R.id.borrar_todo);
        del_button = findViewById(R.id.borrar_uno);
        pc_button = findViewById(R.id.porcentaje);
        div_button = findViewById(R.id.division);
        mult_button = findViewById(R.id.multiplicacion);
        sum_button = findViewById(R.id.suma);
        res_button = findViewById(R.id.resta);
        ac_button = findViewById(R.id.borrar_historial);
        decimal_button = findViewById(R.id.decimal);
        calc_button = findViewById(R.id.igual);
        b9 = findViewById(R.id.bt9);
        b8 = findViewById(R.id.bt8);
        b7 = findViewById(R.id.bt7);
        b6 = findViewById(R.id.bt6);
        b5 = findViewById(R.id.bt5);
        b4 = findViewById(R.id.bt4);
        b3 = findViewById(R.id.bt3);
        b2 = findViewById(R.id.bt2);
        b1 = findViewById(R.id.bt1);
        b0 = findViewById(R.id.bt0);
    }
}