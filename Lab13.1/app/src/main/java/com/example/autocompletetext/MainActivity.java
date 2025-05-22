package com.example.autocompletetext;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView txtSelec;
    MultiAutoCompleteTextView multiAutoCompleteTextView;
    AutoCompleteTextView autoCompleteTextView;
    String arr[] = {"Ha noi","Hue","Sai gon","Ha giang","Hoi an","Lam dong","Long khanh"};

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
        txtSelec = findViewById(R.id.textView2);
        multiAutoCompleteTextView = findViewById(R.id.multiAutoCompleteTextView);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);

        ArrayAdapter myadpter = new ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            arr
        );
        autoCompleteTextView.setAdapter(myadpter);

        multiAutoCompleteTextView.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                arr
        ));
        multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtSelec.setText(autoCompleteTextView.getText());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}