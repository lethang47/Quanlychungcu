package com.example.quan_ly_chung_cu.fragment_cudan;

import static java.lang.Integer.compare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.quan_ly_chung_cu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Hoso extends Fragment {
    ListView list_thanhvien_hodan;
    //ArrayList<contenthoso> contenthosoArrayList;
    contenthossoApdater contenthossoapdater;
    TextView name;
    TextView apartment;
    String host = "192.168.1.196";
    Button thanhvien;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getActivity().getIntent().getExtras();
        int id_account = bundle.getInt("id_account");

        View view = inflater.inflate(R.layout.fragment_hoso_cudan,container, false);
        list_thanhvien_hodan = view.findViewById(R.id.id_listview_thanhvien_hodan);

        name = view.findViewById(R.id.textView_name);
        apartment = view.findViewById(R.id.textView_apartment);
        ArrayList<contenthoso> contenthosoArrayList = new ArrayList<>();

        String url = "http://" + host + "/androidwebservice/getdata_user.php";
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(id_account-1);
                            int id_aparment = jsonObject.getInt("Id_apartment");

                            name.setText(jsonObject.getString("Name"));
                            apartment.setText("Căn hộ: " + String.valueOf(id_aparment));
                            for (int j = 0; j < response.length(); j++){

                                JSONObject jsonObject1 = response.getJSONObject(j);
                                String name = jsonObject1.getString("Name");
                                int id = jsonObject1.getInt("Id_apartment");
                                if (compare(id, id_aparment) == 0) {
                                    contenthosoArrayList.add(new contenthoso(name));
                                }
                            }
                            contenthossoapdater.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
        contenthossoapdater = new contenthossoApdater(getActivity(), R.layout.content_hoso_cudan, contenthosoArrayList);
        list_thanhvien_hodan.setAdapter(contenthossoapdater);

        list_thanhvien_hodan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Button button = view.findViewById(R.id.id_bt_thanhvien_hodan);
                String tenhodan = button.getText().toString();
                Fragment fragment = new Hoso_thanhvien();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame_cudan, fragment);
                transaction.addToBackStack("hoso_thanhvien");
                transaction.commit();
                Bundle bundle_fragment = new Bundle();
                bundle_fragment.putString("tenhodan", tenhodan);
                bundle_fragment.putInt("id_account", id_account);
                fragment.setArguments(bundle_fragment);
                //Toast.makeText(getActivity(), button.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
