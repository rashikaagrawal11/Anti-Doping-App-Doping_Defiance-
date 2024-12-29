package com.example.doping_defiance;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class MainActivity2 extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Set up the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);

        // Initialize DrawerLayout and NavigationView
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Set up the ActionBarDrawerToggle for the hamburger menu
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Set up the report button to navigate to speakup activity
        Button reportButton = findViewById(R.id.buttonspeak);
        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the new activity
                Intent intent = new Intent(MainActivity2.this, speakup.class);
                startActivity(intent);
            }
        });

        // Set up CardViews with URLs
        setupCardView(R.id.c1, "https://nadaindia.yas.gov.in/wp-content/uploads/Amendment-to-NADA-Rules-2021.pdf");
        setupCardView(R.id.c2, "https://nadaindia.yas.gov.in/wp-content/uploads/AthleteRR.pdf");
        setupCardView(R.id.c3, "https://nadaindia.yas.gov.in/wp-content/uploads/2024list_en_final_22_september_2023.pdf");

        // Set up CardView to navigate to kymactivity
        setupCardView(R.id.c5, kym.class);
        setupCardView(R.id.c6, tue.class);

        // Navigation menu item click handling
        navigationView.setNavigationItemSelectedListener(item -> {
            handleNavigationMenuClick(item.getItemId());
            drawerLayout.closeDrawers(); // Close the drawer after selecting an item
            return true;
        });
    }

    /**
     * Set up a CardView to open a URL when clicked.
     *
     * @param cardViewId The ID of the CardView in the layout.
     * @param url        The URL to open.
     */
    private void setupCardView(int cardViewId, String url) {
        CardView cardView = findViewById(cardViewId);
        cardView.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        });
    }

    /**
     * Set up a CardView to navigate to a new activity when clicked.
     *
     * @param cardViewId The ID of the CardView in the layout.
     * @param clazz      The class of the new activity.
     */
    private void setupCardView(int cardViewId, Class<?> clazz) {
        CardView cardView = findViewById(cardViewId);
        cardView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, clazz);
            startActivity(intent);
        });
    }

    /**
     * Handle navigation menu item clicks using if-else.
     *
     * @param itemId The ID of the menu item clicked.
     */
    private void handleNavigationMenuClick(int itemId) {
        Intent intent;

        if (itemId == R.id.nav_home) {
            intent = new Intent(MainActivity2.this, MainActivity2.class);
            startActivity(intent);
        } else if (itemId == R.id.quiz) {
            intent = new Intent(MainActivity2.this, quiz.class);
            startActivity(intent);

        }
        else if (itemId == R.id.AboutUs) {
            intent = new Intent(MainActivity2.this, vision.class);
            startActivity(intent);

        } else if (itemId == R.id.Engage) {
            intent = new Intent(MainActivity2.this, Engage.class);
            startActivity(intent);
        } else if (itemId == R.id.news) {
            intent = new Intent(MainActivity2.this, News.class);
            startActivity(intent);
        } else if (itemId == R.id.Community) {
            intent = new Intent(MainActivity2.this, community.class);
            startActivity(intent);
        }
        else if (itemId == R.id.podcast) {
            intent = new Intent(MainActivity2.this, podcast.class);
            startActivity(intent);
        }else if (itemId == R.id.dopingmenu) {
            intent = new Intent(MainActivity2.this, antidoping.class);
            startActivity(intent);
        } else if (itemId == R.id.Healthcare) {
            intent = new Intent(MainActivity2.this, healthcare.class);
            startActivity(intent);
        }
    }
}
