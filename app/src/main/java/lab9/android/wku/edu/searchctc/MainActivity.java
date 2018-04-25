//Tyler Carrico
//App uses web view and text to speech
package lab9.android.wku.edu.searchctc;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    //init vars
    Button b1;
    EditText ed1;
    TextToSpeech t1;

    private WebView wv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create button and edit text
        b1=(Button)findViewById(R.id.button);
        ed1=(EditText)findViewById(R.id.editText);

        //create text to speech
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                }
            }
        });

        wv1=(WebView)findViewById(R.id.webView);
        wv1.setWebViewClient(new MyBrowser());

        //onclick for search
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create url
                String term = ed1.getText().toString();
                String google = "https://www.google.com/search?q=";
                String url = google + term;
                wv1.getSettings().setLoadsImagesAutomatically(true);
                wv1.getSettings().setJavaScriptEnabled(true);
                wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                wv1.loadUrl(url);
                //Call Search term
                t1.speak("Searching "+term, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}