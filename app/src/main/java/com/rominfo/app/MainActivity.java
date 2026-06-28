package com.rominfo.app;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rominfo.app.databinding.ActivityMainBinding;
import com.rominfo.app.ui.screens.AboutFragment;
import com.rominfo.app.ui.screens.ChangelogFragment;
import com.rominfo.app.ui.screens.LinksFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        // Load default fragment
        if (savedInstanceState == null) {
            loadFragment(new AboutFragment());
            binding.toolbar.setTitle(R.string.tab_about);
        }

        binding.bottomNav.setOnItemSelectedListener(item -> {
            Fragment fragment;
            int id = item.getItemId();
            if (id == R.id.nav_about) {
                fragment = new AboutFragment();
                binding.toolbar.setTitle(R.string.tab_about);
            } else if (id == R.id.nav_changelog) {
                fragment = new ChangelogFragment();
                binding.toolbar.setTitle(R.string.tab_changelog);
            } else if (id == R.id.nav_links) {
                fragment = new LinksFragment();
                binding.toolbar.setTitle(R.string.tab_links);
            } else {
                return false;
            }
            loadFragment(fragment);
            return true;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
