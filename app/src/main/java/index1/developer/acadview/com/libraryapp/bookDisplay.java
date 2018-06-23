package index1.developer.acadview.com.libraryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class bookDisplay extends AppCompatActivity {
    PDFView pd;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_display);

        pd=(PDFView) findViewById(R.id.pdfView);
            pd.fromAsset("Adolf Hitler - Mein Kampf.pdf").load();


    }
}
