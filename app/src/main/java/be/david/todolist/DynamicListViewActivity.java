package be.david.todolist;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DynamicListViewActivity extends AppCompatActivity {

    private EditText editText;
    private ImageButton button;
    private ListView listView;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_list_view);

        editText = (EditText) findViewById(R.id.itemEditText);
        button = (ImageButton) findViewById(R.id.add_item_button);
        listView = (ListView) findViewById(R.id.itemsListView);

        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(DynamicListViewActivity.this,android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoItem = editText.getText().toString();
                if (todoItem.length() > 0 ) {

                    list.add(todoItem);
                    adapter.notifyDataSetChanged();
                    editText.setText("");
                }
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                list.remove(position);
                adapter.notifyDataSetChanged();


                return true;
            }
        });


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.);
//        return true;
//    }

    @Override
    protected void onStop() {
        super.onStop();

        saveList(list);
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.list.clear();
        this.list.addAll(loadList());
        adapter.notifyDataSetChanged();

    }

    public ArrayList<String> loadList () {

        ArrayList<String> list = new ArrayList<>();

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

        list.addAll(sharedPref.getStringSet("Todo",new HashSet<String>()));

        return list;

    }

    public void saveList(ArrayList<String> list) {


        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        Set<String> values = new HashSet<>();

        values.addAll(list);

        editor.putStringSet("Todo", values);

        editor.commit();

    }
}
