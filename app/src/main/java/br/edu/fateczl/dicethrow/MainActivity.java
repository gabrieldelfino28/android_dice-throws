package br.edu.fateczl.dicethrow;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Spinner spDados;
    private TextView output;
    private RadioButton rbAmount1;
    private RadioButton rbAmount2;
    private RadioButton rbAmount3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rbAmount1 = findViewById(R.id.rbAmount1);
        rbAmount1.setChecked(true);
        rbAmount2 = findViewById(R.id.rbAmount2);
        rbAmount3 = findViewById(R.id.rbAmount3);
        output = findViewById(R.id.tvOutput);
        spDados = findViewById(R.id.spDados);
        fillSpinner();

        Button btnJogar = findViewById(R.id.btnJogar);
        btnJogar.setOnClickListener(op -> computeDices());
    }

    private void computeDices() {

        switch (selectRButton()) {
            case 1:
                computeGame(1);
                break;
            case 2:
                computeGame(2);
                break;
            default:
                computeGame(3);
                break;
        }
    }

    private void computeGame(int diceThrows) {
        Random rnd = new Random();
        StringBuilder buffer = new StringBuilder();
        buffer.append("D").append(selectDice()).append(" resultado(s) do dado(s): ");
        int result;

        for(int i = 1; i <= diceThrows; i++) {
            result = rnd.ints(1, selectDice() + 1).findFirst().getAsInt();
            buffer.append(result).append(" ");
        }
        output.setText(buffer.toString());
    }

    private int selectDice() {
        String selectedDice = (String) spDados.getSelectedItem();
        switch (selectedDice) {
            case "D4":
                return 4;
            case "D6":
                return 6;
            case "D8":
                return 8;
            case "D10":
                return 10;
            case "D12":
                return 12;
            case "D20":
                return 20;
            case "D100":
                return 100;
            default:
                return 0;
        }
    }

    private int selectRButton() {
        if (rbAmount1.isChecked()) {
            return 1;
        }
        if (rbAmount2.isChecked()) {
            return 2;
        }
        return 3;
    }

    private void fillSpinner() {
        List<String> list = new ArrayList<>();

        list.add(getString(R.string.D4));
        list.add(getString(R.string.D6));
        list.add(getString(R.string.D8));
        list.add(getString(R.string.D10));
        list.add(getString(R.string.D12));
        list.add(getString(R.string.D20));
        list.add(getString(R.string.D100));

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDados.setAdapter(adapter);
    }
}