package tcc_zanza.no_ip.biz.thirtyminutes.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import tcc_zanza.no_ip.biz.thirtyminutes.VO.Generator;
import tcc_zanza.no_ip.biz.thirtyminutes.R;

public class ConfigurationActivity extends AppCompatActivity {

    private CheckBox capitalLetter;
    private CheckBox lowerCase;
    private CheckBox number;
    private CheckBox simbols;
    private EditText passwordSize;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        key = (String)getIntent().getSerializableExtra("pincode");
        ImageButton save = (ImageButton) findViewById(R.id.save_config);

        capitalLetter = (CheckBox)findViewById(R.id.cb_capital_letter);
        capitalLetter.setChecked(Generator.isFlegLETTERS());

        lowerCase = (CheckBox)findViewById(R.id.cb_lower_case);
        lowerCase.setChecked(Generator.isFlegLetters());

        number = (CheckBox)findViewById(R.id.cb_number);
        number.setChecked(Generator.isFlegNumbers());

        simbols = (CheckBox)findViewById(R.id.cb_simbols);
        simbols.setChecked(Generator.isFlegSimbols());

        Integer size = Generator.getSize();
        passwordSize = (EditText)findViewById(R.id.edit_pass_size);
        passwordSize.setText(size.toString());

        save.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(ConfigurationActivity.this, R.string.text_save_config, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Generator.setFlegLETTERS(capitalLetter.isChecked());
                Generator.setFlegLetters(lowerCase.isChecked());
                Generator.setFlegNumbers(number.isChecked());
                Generator.setFlegSimbols(simbols.isChecked());
                Generator.setSize(Integer.parseInt(passwordSize.getText().toString()));

                Intent intent = new Intent(ConfigurationActivity.this, MainActivity.class);

                intent.putExtra("pincode", key);
                startActivity(intent);

                finish();
            }
        });



    }

}
