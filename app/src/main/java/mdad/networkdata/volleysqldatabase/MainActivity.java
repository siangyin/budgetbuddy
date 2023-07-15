package mdad.networkdata.volleysqldatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    public static String SERVER_URL  = "http://budgetbuddy.atspace.cc/api/v1";
    public static String[] CATEGORY_LIST = {"Food","Transportation","Groceries","Entertainment","Household","Housing","Clothing","Utilities","Health","Education","Insurance","Tax","Donation","Gift","Other","Misc","Misc2","Misc3"};

    EditText etStartDate, etEndDate;
    MultiAutoCompleteTextView selectedCat;
    Button btnAddExp, btnViewExp;
    private int currYr,currMth, currDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etStartDate = findViewById(R.id.etDate);
        etEndDate = findViewById(R.id.etEndDate);
        selectedCat = findViewById(R.id.selectedCat);
        btnAddExp = findViewById(R.id.btnAddExp);
        btnViewExp = findViewById(R.id.btnViewExp);

        // Creating the instance of ArrayAdapter containing list of categories names
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, CATEGORY_LIST);
        selectedCat.setAdapter(adapter);
        selectedCat.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        // get current date
        final Calendar cal = Calendar.getInstance();
        currYr = cal.get(Calendar.YEAR);
        currMth = cal.get(Calendar.MONTH);
        currDate = cal.get(Calendar.DAY_OF_MONTH);

        // start date event
        etStartDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(etStartDate.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etStartDate.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }},currYr,currMth,currDate);
                datePickerDialog.show();
            }});

        // end date event
        etEndDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(etEndDate.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etEndDate.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }},currYr,currMth,currDate);
                datePickerDialog.show();
            }});

        // view expenses btn event
        btnViewExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // String userId
                String startVal = etStartDate.getText().toString();
                String endVal = etEndDate.getText().toString();
                String catList =selectedCat.getText().toString();

                JSONObject req_body_data = new JSONObject();
                try {
                    // req_body_data.put("userId",userId);
                    req_body_data.put("start",startVal);
                    req_body_data.put("end",endVal);
                    req_body_data.put("categories",catList);
                } catch (JSONException e){}
                System.out.println(req_body_data);
            }
        });

        // add expense btn event

    } // onCreate End



} // MainActivity End