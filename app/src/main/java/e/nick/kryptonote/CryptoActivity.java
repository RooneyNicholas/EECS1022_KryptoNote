package e.nick.kryptonote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class CryptoActivity extends AppCompatActivity {
    private Cipher cipher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crypto_layout);
    }

    public void onEncryptButton(View v){
        try {
            cipher = new Cipher(((EditText) findViewById(R.id.keyBox)).getText().toString());
            String result = cipher.encrypt(((EditText) findViewById(R.id.textIOBox)).getText().toString());
            ((EditText) findViewById(R.id.textIOBox)).setText(result);
        }
        catch(Exception e){
            Toast label = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
            label.show();
        }
    }

    public void onDecryptButton(View v){
        try {
            cipher = new Cipher(((EditText) findViewById(R.id.keyBox)).getText().toString());
            String result = cipher.decrypt(((EditText) findViewById(R.id.textIOBox)).getText().toString());
            ((EditText) findViewById(R.id.textIOBox)).setText(result);
        }
        catch(Exception e){
            Toast label = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
            label.show();
        }
    }

    public void onSave(View v){
        try{
            String fileName = (((EditText) findViewById(R.id.fileNameBox)).getText().toString());
            File dir = this.getFilesDir();
            File file = new File(dir, fileName);
            FileWriter fw = new FileWriter(file);
            fw.write(((EditText) findViewById(R.id.textIOBox)).getText().toString());
            fw.close();
            Toast.makeText(this, "Note Saved.",Toast.LENGTH_LONG).show();
        }
        catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public void onLoad(View v){
        try{
            File dir = this.getFilesDir();
            File file = new File(dir,((EditText) findViewById(R.id.fileNameBox)).getText().toString());
            FileReader fr = new FileReader(file);
            String output = "";
            for(int i = fr.read(); i != -1; i=fr.read()){
                output += (char) i;
            }
            ((EditText) findViewById(R.id.textIOBox)).setText(output);
        }
        catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
