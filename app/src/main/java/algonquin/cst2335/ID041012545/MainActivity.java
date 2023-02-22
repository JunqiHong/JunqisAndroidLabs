package algonquin.cst2335.ID041012545;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * @author: Junqi Hong
 * @version 1.0
 * MainActivity of the app, which provides the user interface and check the password whether it is valid.
 */
public class MainActivity extends AppCompatActivity {
    /**This set the textView to private */
    private TextView tv = null;
    /**This set the editText to private */
    private EditText et = null;
    /**This set the button to private */
    private  Button btn = null;



    @Override  //This starts the application
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.textView);
        EditText et = findViewById(R.id.editText);
        Button btn = findViewById(R.id.button);

        btn.setOnClickListener(clk->{
            String password = et.getText().toString();

            checkPasswordComplexity(password);

            boolean validPassword =checkPasswordComplexity(password);

            if(validPassword){
                tv.setText("Your password meets the requirements");
            }
            else{
                tv.setText("You shall not pass!");
            }

        });


    }

    /**The function is to check the complexity of the password
     *
     * @param pw The String object that we are checking
     */
    boolean checkPasswordComplexity(String pw) {
        boolean foundUpperCase, foundLowerCase, foundNumber, foundSpecial;

        foundUpperCase = foundLowerCase = foundNumber = foundSpecial = false;

        for (int i = 0; i < pw.length(); i++) {
            char c = pw.charAt(i);

            if (Character.isUpperCase(c)) {
                foundUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                foundLowerCase = true;
            } else if (Character.isDigit(c)) {
                foundNumber = true;
            } else if (isSpecialCharacter(c)) {
                foundSpecial = true;
            }
        }

        if( ! foundUpperCase)
        {

            Toast.makeText(getApplicationContext(), "missing a upper case letter", Toast.LENGTH_SHORT).show();

            return false;

        }

        else if( ! foundLowerCase)
        {
            Toast.makeText(getApplicationContext(), "missing a lower case letter", Toast.LENGTH_SHORT).show();

            return false;

        }

        else if( ! foundNumber) {

            Toast.makeText(getApplicationContext(), "missing a number", Toast.LENGTH_SHORT).show();

            return false;
        }

        else if(! foundSpecial) {

            Toast.makeText(getApplicationContext(), "missing a special character", Toast.LENGTH_SHORT).show();

            return false;
        }

        else

            return true; //only get here if they're all true

    }

    private boolean isSpecialCharacter(char c) {
        switch (c){
            case '!':
            case '@':
            case '#':
            case '$':
            case '%':
            case '^':
            case '&':
            case '*':
                return true;
            default:
                return false;
        }
    }


}