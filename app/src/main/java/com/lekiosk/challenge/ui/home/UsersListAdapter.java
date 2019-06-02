package com.lekiosk.challenge.ui.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lekiosk.challenge.R;
import com.lekiosk.challenge.models.Utilisateur;

import java.util.List;

/**
 * Created by Badr Elattaoui
 * on 01/06/2019.
 */

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.UserViewHolder> {

    private List<Utilisateur> mUsersList;

    public UsersListAdapter(List<Utilisateur> mUsersList) {
        this.mUsersList = mUsersList;
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new UserViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {

        if(mUsersList != null){
            Utilisateur utilisateur = mUsersList.get(i);
            userViewHolder.mEmail.setText(utilisateur.getmEmail());
            userViewHolder.mName.setText(utilisateur.getmName());
            userViewHolder.mUserName.setText(utilisateur.getmUserName());
        }
    }

    @Override
    public int getItemCount() {
        return mUsersList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        TextView mName;
        TextView mUserName;
        TextView mEmail;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);

            mName  = itemView.findViewById(R.id.name);
            mUserName  = itemView.findViewById(R.id.username);
            mEmail = itemView.findViewById(R.id.email);
        }
    }
}
