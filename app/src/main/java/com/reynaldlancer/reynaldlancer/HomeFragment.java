package com.reynaldlancer.reynaldlancer;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static final String TAG = "Tugas dummy";
    NavigationView side_nav;
    Button button_nav;
    DrawerLayout drawerLayout;
    ViewFlipper promo_layout;
    RecyclerView RV_tugas, RV_misi, RV_poster, RV_academy;
    Button tambah_saldo;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        side_nav = v.findViewById(R.id.side_nav);
        button_nav = v.findViewById(R.id.sidebar_button);
        drawerLayout = v.findViewById(R.id.drawer_container);
        promo_layout = v.findViewById(R.id.promo);
        RV_tugas = v.findViewById(R.id.tugas_rv);
        RV_misi = v.findViewById(R.id.misi_rv);
        RV_poster = v.findViewById(R.id.poster_rv);
        RV_academy = v.findViewById(R.id.heroac_rv);
        tambah_saldo = v.findViewById(R.id.tambah_saldo);


        //for images promo
        int image[] = {R.mipmap.slide1, R.mipmap.slide2, R.mipmap.slide3};
        for (int i = 0; i < image.length; i++) {
            runpromo(image[i]);
        }

        //for drawer
        button_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });


        //for tugas
        TugasDummy tugasDummy = new TugasDummy();
//        Toast.makeText(getActivity(), tugasDummy.dummy().toString(), Toast.LENGTH_SHORT).show();
        LinearLayoutManager tugaslayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RV_tugas.setLayoutManager(tugaslayoutManager);
        RV_tugas.setItemAnimator(new DefaultItemAnimator());

        TugasRvApapter tugasRvApapter = new TugasRvApapter(tugasDummy.dummy(), getActivity());
        RV_tugas.setAdapter(tugasRvApapter);

        //for misi
        LinearLayoutManager tugaslayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RV_misi.setLayoutManager(tugaslayoutManager2);
        RV_misi.setItemAnimator(new DefaultItemAnimator());

        MisiRVAdapter misiRVAdapter = new MisiRVAdapter(tugasDummy.dummy(), getActivity());
        RV_misi.setAdapter(misiRVAdapter);


        //for poster
        LinearLayoutManager tugaslayoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RV_poster.setLayoutManager(tugaslayoutManager3);
        RV_poster.setItemAnimator(new DefaultItemAnimator());

        PosterRvAdapter posterRvAdapter = new PosterRvAdapter(tugasDummy.dummy(), getActivity());
        RV_poster.setAdapter(posterRvAdapter);

        //for hero academy

        HeroAcademyDummy dummyAc = new HeroAcademyDummy();
        LinearLayoutManager tugaslayoutManager4 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RV_academy.setLayoutManager(tugaslayoutManager4);
        RV_academy.setItemAnimator(new DefaultItemAnimator());

        HeroAcademyRvAdapter heroAcademyRvAdapter = new HeroAcademyRvAdapter(dummyAc.dummy(), getActivity());
        RV_academy.setAdapter(heroAcademyRvAdapter);


        //logic for feature
        //add saldo
        tambah_saldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent saldo = new Intent(getActivity(), SaldoActivity.class);
                startActivity(saldo);
            }
        });

        return v;

    }

    private void runpromo(int Image) {
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(Image);

        promo_layout.addView(imageView);
        promo_layout.setFlipInterval(4000);
        promo_layout.setAutoStart(true);

        promo_layout.setInAnimation(getActivity(), android.R.anim.slide_in_left);
        promo_layout.setOutAnimation(getActivity(), android.R.anim.slide_out_right);

    }

}
