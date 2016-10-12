package be.david.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class DynamicListViewActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private ListView listView;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_list_view);

        editText = (EditText) findViewById(R.id.itemEditText);
        button = (Button) findViewById(R.id.itemEditButton);
        listView = (ListView) findViewById(R.id.itemsListView);

        list = new ArrayList<String>();
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
}
