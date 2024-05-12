package com.example.jegyzet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class JegyzetItemAdapter extends RecyclerView.Adapter<JegyzetItemAdapter.ViewHolder> implements Filterable {
    private ArrayList<jegyzet> jegyzetData;
    private ArrayList<jegyzet> jegyzetDataAll;
    private Context cContext;
    private int lastPosition=-1;
    JegyzetItemAdapter(Context context, ArrayList<jegyzet> itemsData){
        this.jegyzetData=itemsData;
        this.jegyzetDataAll=itemsData;
        this.cContext=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(cContext).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        jegyzet currentItem = jegyzetData.get(position);

        holder.bindTo(currentItem);

        if(holder.getAdapterPosition()>lastPosition){

        }
    }

    @Override
    public int getItemCount() {
        return jegyzetData.size();
    }

    @Override
    public Filter getFilter() {
        return jegyzetFilter;
    }

    private Filter jegyzetFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<jegyzet> filteredList=new ArrayList<>();
            FilterResults result= new FilterResults();

            result.count=jegyzetDataAll.size();
            result.values=jegyzetDataAll;

            return null;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            jegyzetData=(ArrayList) results.values;
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView cim;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cim=itemView.findViewById(R.id.listItemCim);

            itemView.findViewById(R.id.listItemCim).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }

        public void bindTo(jegyzet currentitem) {
            cim.setText(currentitem.getCim());
        }
    };

};
