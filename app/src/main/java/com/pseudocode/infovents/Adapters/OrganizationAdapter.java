package com.pseudocode.infovents.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pseudocode.infovents.Classes.Organization;
import com.pseudocode.infovents.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Baymax on 09/10/2015.
 */
public class OrganizationAdapter extends RecyclerView.Adapter<OrganizationAdapter.CustomViewHolder> {
    private List<Organization> mDataset;
    private SparseBooleanArray selectedItems;
    private Context mContext;


    public OrganizationAdapter(Context context, List<Organization> myDataset) {
        mContext = context;
        mDataset = myDataset;
        selectedItems = new SparseBooleanArray();

    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_organization, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Organization organization = mDataset.get(position);
//        Picasso.with(mContext).load(organization.getOrgImage())
//                .error(R.drawable.ic_business_24dp)
//                .placeholder(R.drawable.ic_business_24dp)
//                .into(holder.imageCover);

        Picasso.with(mContext).load(organization.getOrgImage())
                .error(R.drawable.ic_business_24dp)
                .placeholder(R.drawable.ic_business_24dp)
                .into(holder.imageView);

        holder.textViewOrg.setOnClickListener(clickListener);
        holder.imageView.setOnClickListener(clickListener);

        holder.textViewOrg.setTag(holder);
        holder.textViewOrgCode.setTag(holder);
        holder.imageView.setTag(holder);
        holder.imageCover.setTag(holder);

        //Setting text view title
        holder.textViewOrgCode.setText(organization.getOrgDesc());
        holder.textViewOrg.setText(organization.getOrgName());

    }


    @Override
    public int getItemCount() {

        return (null != mDataset ? mDataset.size() : 0);

    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageView imageCover;
        TextView textViewOrg;
        TextView textViewOrgCode;

        public CustomViewHolder(View view) {
            super(view);
            this.imageCover = (ImageView) view.findViewById(R.id.org_cover);
            this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
            this.textViewOrg = (TextView) view.findViewById(R.id.org);;
            this.textViewOrgCode = (TextView) view.findViewById(R.id.orgdesc);
        }
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            CustomViewHolder holder = (CustomViewHolder) view.getTag();
            int position = holder.getAdapterPosition();

            Organization organization = mDataset.get(position);

        }
    };
}