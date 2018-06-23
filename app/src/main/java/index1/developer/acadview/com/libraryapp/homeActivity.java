package index1.developer.acadview.com.libraryapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class homeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    DatabaseHelper db;
    private ArrayList<String> list;
    private LinkedHashMap<String, List<String>> mapbook;
    private ExpandableListAdapter adapter;
    private ExpandableListView ls;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ls=(ExpandableListView)findViewById(R.id.listView);
        button = (Button)findViewById(R.id.button);
        db = new DatabaseHelper(this);
        list = new ArrayList<>();
        fetchall();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in= new Intent(homeActivity.this, booklist.class);
                startActivity(in);
            }
        });

        adapter = new index1.developer.acadview.com.libraryapp.ExpandableListAdapter(this, list, mapbook);
        ls.setAdapter(adapter);
        ls.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                TextView tx = (TextView)view.findViewById(R.id.listchild);
                String data = tx.getText().toString();
                Intent intent = new Intent(homeActivity.this, bookDisplay.class);
                intent.putExtra("name", data);
                startActivity(intent);
                return true;
            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(this, bookDisplay.class);
            startActivity(intent);
    }

    public void fetchall() {
        db = new DatabaseHelper(this);
        try {

            db.createDatabase();
            db.openDataBase();

        } catch (Exception e) {
            e.printStackTrace();
        }
        int i, j;
        int ctg;
        mapbook= new LinkedHashMap<>();
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM cate_gory ORDER BY category", null);
        i = cursor.getColumnIndex("category");
        list = new ArrayList<>();
        while (cursor.moveToNext()) {
            String type = cursor.getString(i);
            list.add(type);
        }
        cursor.close();
       ArrayList<String> mot = new ArrayList<>();
        ArrayList<String> ed = new ArrayList<>();
        ArrayList<String> fic = new ArrayList<>();
        ArrayList<String> aut = new ArrayList<>();
        ArrayList<String> my = new ArrayList<>();
        Cursor cursor1 = database.rawQuery("SELECT category, bookname FROM book INNER JOIN cate_gory ON cate_gory._id = book.cate_id", null);
        j = cursor1.getColumnIndex("bookname");
        ctg = cursor1.getColumnIndex("category");
        while (cursor1.moveToNext()) {

            if (cursor1.getString(ctg).equals(list.get(0))) {
                aut.add(cursor1.getString(j));

            } else if (cursor1.getString(ctg).equals(list.get(1))) {
                ed.add(cursor1.getString(j));

            } else if (cursor1.getString(ctg).equals(list.get(2))) {
                fic.add(cursor1.getString(j));

            } else if (cursor1.getString(ctg).equals(list.get(3))) {
                mot.add(cursor1.getString(j));

            } else if (cursor1.getString(ctg).equals(list.get(4))) {
                my.add(cursor1.getString(j));

            }

        }

        cursor1.close();
        mapbook.put(list.get(0), aut);
        mapbook.put(list.get(1), ed);
        mapbook.put(list.get(2), fic);
        mapbook.put(list.get(3), mot);
        mapbook.put(list.get(4), my);
    }
}
