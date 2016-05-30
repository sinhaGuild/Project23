package com.example.sinhaguild.project23;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.mxn.soul.flowingdrawer_core.MenuFragment;
import com.squareup.picasso.Picasso;

public class CustomMenuFragment extends MenuFragment {

    private ImageView ivMenuUserProfilePhoto;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ivMenuUserProfilePhoto = (ImageView) view.findViewById(R.id.ivMenuUserProfilePhoto);
        if (ivMenuUserProfilePhoto!=null){
            ivMenuUserProfilePhoto.setImageResource(R.drawable.gm_round_shadow_icon);
        }
        return  setupReveal(view) ;
    }

    private void setupHeader() {
        //int avatarSize = getResources().getDimensionPixelSize(R.dimen.global_menu_avatar_size);
        ivMenuUserProfilePhoto.setImageResource(R.drawable.gm_round_shadow_icon);
//        Picasso.with(getActivity())
//                .load(R.drawable.gm_round_shadow_icon)
//                .placeholder(R.drawable.img_circle_placeholder)
//                .resize(avatarSize, avatarSize)
//                .centerCrop()
//                .into(ivMenuUserProfilePhoto);
    }

    public void onOpenMenu(){
        Toast.makeText(getActivity(),"onOpenMenu",Toast.LENGTH_SHORT).show();
    }
    public void onCloseMenu(){
        Toast.makeText(getActivity(),"onCloseMenu",Toast.LENGTH_SHORT).show();
    }
}
