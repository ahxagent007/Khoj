package com.secretdevelopersltd.dexian.khoj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_persons);

        rv_recyclerView = findViewById(R.id.rv_recyclerView);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_recyclerView.setLayoutManager(mLayoutManager);
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


