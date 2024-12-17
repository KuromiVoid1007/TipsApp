package com.example.tips;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;

import android.text.TextUtils;
import android.widget.*;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    private EditText editTextAmount, editTextPeople;
    private SeekBar seekBarTip;
    private TextView textTipPercent, textTotalAmount, textPerPersonAmount;
    private Button buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация UI-компонентов
        editTextAmount = findViewById(R.id.editTextAmount);
        editTextPeople = findViewById(R.id.editTextPeople);
        seekBarTip = findViewById(R.id.seekBarTip);
        textTipPercent = findViewById(R.id.textTipPercent);
        textTotalAmount = findViewById(R.id.textTotalAmount);
        textPerPersonAmount = findViewById(R.id.textPerPersonAmount);
        buttonClear = findViewById(R.id.buttonClear);

        // Обработчик изменения процента чаевых
        seekBarTip.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textTipPercent.setText("Процент чаевых: " + progress + "%");
                calculateResults();
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Обработчик очистки
        buttonClear.setOnClickListener(v -> clearFields());
    }

    private void calculateResults() {
        String billInput = editTextAmount.getText().toString();
        String peopleInput = editTextPeople.getText().toString();

        if (TextUtils.isEmpty(billInput) || Double.parseDouble(billInput) <= 0) {
            Toast.makeText(this, "Введите корректную сумму заказа", Toast.LENGTH_SHORT).show();
            return;
        }

        int peopleCount = TextUtils.isEmpty(peopleInput) ? 1 : Integer.parseInt(peopleInput);
        if (peopleCount < 1) {
            Toast.makeText(this, "Количество участников должно быть >= 1", Toast.LENGTH_SHORT).show();
            return;
        }

        double billAmount = Double.parseDouble(billInput);
        int tipPercent = seekBarTip.getProgress();

        double tipAmount = Calculator.calculateTip(billAmount, tipPercent);
        double totalAmount = Calculator.calculateTotal(billAmount, tipAmount);
        double perPerson = Calculator.calculatePerPerson(totalAmount, peopleCount);

        textTotalAmount.setText("Итоговая сумма: " + String.format("%.2f", totalAmount));
        textPerPersonAmount.setText("На одного человека: " + String.format("%.2f", perPerson));
    }

    private void clearFields() {
        editTextAmount.setText("");
        editTextPeople.setText("");
        seekBarTip.setProgress(15);
        textTipPercent.setText("Процент чаевых: 15%");
        textTotalAmount.setText("Итоговая сумма: 0.0");
        textPerPersonAmount.setText("На одного человека: 0.0");
    }


}