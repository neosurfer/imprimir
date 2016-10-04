package com.example.bsolanoa.imprimir;

import android.content.Context;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = new WebView(this);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                imprimir(view);
            }
        });

        String pagina = "<html><body><p>Este es el contenido que se va a imprimir</p></body></html>";
        webView.loadDataWithBaseURL(null,pagina,"text/HTML","UTF-8",null);
        myWebView = webView;
    }

    private void imprimir(WebView webView){
        PrintManager printManager= (PrintManager)this.getSystemService(Context.PRINT_SERVICE);
        PrintDocumentAdapter printDocumentAdapter= webView.createPrintDocumentAdapter("My Documento");
        String jobName = getString(R.string.app_name) + " Print Test";
        printManager.print(jobName,printDocumentAdapter, new PrintAttributes.Builder().build());
    }
}
