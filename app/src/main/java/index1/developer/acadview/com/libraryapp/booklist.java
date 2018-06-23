package index1.developer.acadview.com.libraryapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class booklist extends AppCompatActivity implements AdapterView.OnItemClickListener {


    DatabaseHelper db;
    ArrayList<String> list1;
    ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booklist);
        lst=(ListView)findViewById(R.id.listview3);
        db= new DatabaseHelper(this);
        list1=new ArrayList<>();
        fetchdata();



        ArrayAdapter<String> adapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list1);
        lst.setAdapter(adapter);
        lst.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(booklist.this, bookDisplay.class);
        startActivity(intent);

    }



    public void fetchdata()
    {
        db = new DatabaseHelper(this);
        try {

            db.createDatabase();
            db.openDataBase();

        } catch (Exception e) {
            e.printStackTrace();
        }
        int i;
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM book", null);
        i = cursor.getColumnIndex("bookname");
        list1 = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(i);
            list1.add(name);
        }
        cursor.close();
    }
}
