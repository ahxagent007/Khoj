package com.secretdevelopersltd.dexian.khoj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewPersonsActivity extends AppCompatActivity {

    String TAG = "XIAN";

    //RecycleView
    RecyclerView rv_recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mRecycleAdapter;

    ArrayList<Person> pList;

    //Firebase
    private DatabaseReference mDatabase;

    String CATEGORY;

    private static final int REQUEST_PHONE_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_persons);

        CATEGORY = getIntent().getStringExtra("CAT");
        Log.i(TAG, "CATEGORY = "+CATEGORY);

        rv_recyclerView = findViewById(R.id.rv_recyclerView);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_recyclerView.setLayoutManager(mLayoutManager);


        //FIREBASE ****************************************************************************************************************************
        pList = new ArrayList<Person>();

        //Firebase Database
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mDatabase = FirebaseDatabase.getInstance().getReference("DATA");

        Query query = mDatabase.child(CATEGORY);

        /*myTopPostsQuery.addChildEventListener(new ChildEventListener() {
            // TODO: implement the ChildEventListener methods as documented above
            // ...
        });*/

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getPersonFromFirebase(dataSnapshot, pList);

                mRecycleAdapter = new RecycleViewAdapterForAddStock(getApplicationContext(), pList);
                rv_recyclerView.setAdapter(mRecycleAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void getPersonFromFirebase(DataSnapshot dataSnapshot, ArrayList<Person> PersonList){
        PersonList.clear();

        //Log.i(TAG,"First Product List size "+productList.size());
        for(DataSnapshot ds : dataSnapshot.getChildren()){

            Person p = ds.getValue(Person.class);
            PersonList.add(p);

            //Log.i(TAG,p.getID()+" "+p.getProductName());
        }
        Log.i(TAG,"Person List size "+PersonList.size());

    }

    private void makeCall(String number){

        Intent intent = new Intent(Intent.ACTION_CALL);

        intent.setData(Uri.parse("tel:" + number));

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ViewPersonsActivity.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);

        }
        else
        {
            startActivity(intent);
        }
        //startActivity(intent);

    }

    public class RecycleViewAdapterForAddStock extends RecyclerView.Adapter<RecycleViewAdapterForAddStock.ViewHolder> {

        String TAG = "XIAN";

        ArrayList<Person> PersonList;
        Context context;

        public RecycleViewAdapterForAddStock(Context context, ArrayList<Person> pList) {
            super();
            this.context = context;
            this.PersonList = pList;
            Log.i(TAG,"RECYCLE VIEW Constructor");
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.single_person_list_view, viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int i) {

            //Log.i(TAG,i+" RECYCLE VIEW "+(productList.get(i).getProductName()));

            /*if(!PigeonList.get(i).getPigeonPicture().equalsIgnoreCase("Null")){
                viewHolder.IV_pigeonPicture.setImageBitmap(decodeBase64Image(PigeonList.get(i).getPigeonPicture()));


            }else{

            }*/
            viewHolder.TV_pName.setText(PersonList.get(i).getNAME());
            viewHolder.TV_pSpec.setText(PersonList.get(i).getSPEC());
            viewHolder.TV_pLocation.setText(PersonList.get(i).getLOCATION());
            viewHolder.TV_pNumber.setText(PersonList.get(i).getNUMBER());

            viewHolder.setClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isLongClick) {
                    if (isLongClick) {
                        //Toast.makeText(context, "#" + position + " (Long click)", Toast.LENGTH_SHORT).show();

                        //DeleteProduct(PigeonList.get(position));

                    } else {
                        //Toast.makeText(context, "#" + position + " Not Long Click", Toast.LENGTH_SHORT).show();
                        //AddQuantity(PigeonList.get(position));
                        //ShowPigeonDetails(PersonList.get(position));

                        //*****Call HERE
                        Log.i(TAG, "TEL:"+PersonList.get(position).getNUMBER());
                        makeCall(PersonList.get(position).getNUMBER());

                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return PersonList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {


            private TextView TV_pName, TV_pSpec, TV_pLocation, TV_pNumber;


            private ItemClickListener clickListener;

            public ViewHolder(View itemView) {
                super(itemView);

                TV_pName = itemView.findViewById(R.id.TV_pName);
                TV_pSpec = itemView.findViewById(R.id.TV_pSpec);
                TV_pLocation = itemView.findViewById(R.id.TV_pLocation);
                TV_pNumber = itemView.findViewById(R.id.TV_pNumber);

                itemView.setOnClickListener(this);
                itemView.setOnLongClickListener(this);
            }

            public void setClickListener(ItemClickListener itemClickListener) {
                this.clickListener = itemClickListener;
            }

            @Override
            public void onClick(View view) {
                clickListener.onClick(view, getPosition(), false);
            }

            @Override
            public boolean onLongClick(View view) {
                clickListener.onClick(view, getPosition(), true);
                return true;
            }
        }

    }




}


