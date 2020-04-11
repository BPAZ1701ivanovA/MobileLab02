package com.example.bpaz1701ivanovlab02;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity
{
    //Переменные.
    private double amount = 0.0; // Сумма счёта.
    private double percent = 0.15; // Процент чаевых по умолчанию.
    private EditText et_amount; // Поле для ввода суммы счёта.
    private TextView tv_percent; // Поле для значения процента.
    private SeekBar sb_percent; // Ползунок для процентов.
    private TextView tv_tip; // Поле для суммы чаевых.
    private TextView tv_total; // Поле для итоговой суммы.
    // Форматировщики денежных сумм и процентов
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Связь.
        et_amount = findViewById(R.id.et_amount);
        sb_percent = findViewById(R.id.sb_percent);
        tv_percent = findViewById(R.id.tv_percent);
        tv_tip = findViewById(R.id.tv_tip);
        tv_total = findViewById(R.id.tv_total);
        //Предустановленные значения.
        tv_tip.setText("0.0");
        tv_total.setText("0.0");
        final TextWatcher amountTextWatcher = new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }
            // Вызывается при изменении пользователем величины счета.
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                amount = Double.parseDouble(s.toString());
                // Обновление полей с чаевыми и общей суммой.
                tv_tip.setText(currencyFormat.format(tipCalc.calculateTip(amount,percent)));
                tv_total.setText(currencyFormat.format(tipCalc.calculateTotal(amount, percent)));
            }
            @Override
            public void afterTextChanged(Editable s)
            {

            }
        };
        //Слушатель.
        et_amount.addTextChangedListener(amountTextWatcher);
        final SeekBar.OnSeekBarChangeListener sbListener = new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }
            // Обновление процента чаевых и итоговой суммы
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                percent = progress / 100.0; // Назначение процента чаевых.
                // Вычисление чаевых и общей суммы. Вывод их на экран.
                tv_percent.setText(percentFormat.format(percent));
                tv_tip.setText(currencyFormat.format(tipCalc.calculateTip(amount,percent)));
                tv_total.setText(currencyFormat.format(tipCalc.calculateTotal(amount, percent)));
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        };
        //Слушатель.
        sb_percent.setOnSeekBarChangeListener(sbListener);
    }
}