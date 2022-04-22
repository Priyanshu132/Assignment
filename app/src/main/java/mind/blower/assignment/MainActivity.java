package mind.blower.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private ArrayList<Model> list = new ArrayList<>();
    private RequestQueue requestQueue;

    private static final String path = "http://adminapp.tech/sharefeelings/api/posts?category=12&subcategory=15";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);

        loadList();





    }

    private void loadList() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, path, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray jsonArray = response.getJSONArray("result");

                    for (int i = 0 ; i < jsonArray.length() ; i++){

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Model model = new Model();
                        model.setTitle(jsonObject.getString("title"));
                        model.setDescription(jsonObject.getString("description"));
                        model.setImgpath(jsonObject.getString("imgpath"));

                        list.add(model);


                    }
                    recyclerView = findViewById(R.id.recycler_view);
                    adapter = new Adapter(list);
                    recyclerView.setAdapter(adapter);


                }
                catch (Exception e){

                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error  "+error.getMessage());
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}