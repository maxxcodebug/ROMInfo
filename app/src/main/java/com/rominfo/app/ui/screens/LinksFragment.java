package com.rominfo.app.ui.screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.rominfo.app.data.RomConfig;
import com.rominfo.app.databinding.FragmentLinksBinding;
import com.rominfo.app.utils.ConfigLoader;

public class LinksFragment extends Fragment {

    private FragmentLinksBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLinksBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RomConfig config = ConfigLoader.load(requireContext());

        setupLink(binding.btnTgGroup, config.links.telegram_group);
        setupLink(binding.btnTgChannel, config.links.telegram_channel);
        setupLink(binding.btnGithubRepo, config.links.github_repo);
        setupLink(binding.btnSourceforge, config.links.sourceforge);
    }

    private void setupLink(com.google.android.material.button.MaterialButton btn, String url) {
        if (url == null || url.isEmpty()) {
            btn.setVisibility(View.GONE);
            return;
        }
        btn.setOnClickListener(v -> {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
