package com.rominfo.app.ui.screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.rominfo.app.R;
import com.rominfo.app.data.RomConfig;
import com.rominfo.app.databinding.FragmentAboutBinding;
import com.rominfo.app.utils.ConfigLoader;

public class AboutFragment extends Fragment {

    private FragmentAboutBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAboutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RomConfig config = ConfigLoader.load(requireContext());

        // Header
        binding.tvRomName.setText(config.rom.name);
        binding.tvRomVersion.setText(getString(R.string.version_label, config.rom.version));
        binding.tvBuildType.setText(config.rom.build_type);

        // Device Info
        binding.tvDeviceName.setText(config.rom.device_name);
        binding.tvDeviceCodename.setText(config.rom.device_codename);
        binding.tvArch.setText(config.rom.architecture);
        binding.tvAndroidSecPatch.setText(Build.VERSION.SECURITY_PATCH);
        binding.tvKernel.setText(System.getProperty("os.version", "Unknown"));

        // Maintainer
        binding.tvMaintainerName.setText(config.maintainer.name);
        binding.tvMaintainerTg.setText(config.maintainer.telegram);

        binding.btnTelegram.setOnClickListener(v ->
            openUrl("https://t.me/" + config.maintainer.telegram.replace("@", "")));
        binding.btnGithub.setOnClickListener(v ->
            openUrl("https://github.com/" + config.maintainer.github));
        binding.btnXda.setOnClickListener(v -> openUrl(config.maintainer.xda_thread));

        if (config.maintainer.donation_url != null && !config.maintainer.donation_url.isEmpty()) {
            binding.btnDonate.setVisibility(View.VISIBLE);
            binding.btnDonate.setOnClickListener(v -> openUrl(config.maintainer.donation_url));
        } else {
            binding.btnDonate.setVisibility(View.GONE);
        }

        // ROM Info rows (use hidden TextViews to feed data - simpler approach)
        binding.tvAndroidVersion.setText(config.rom.android_version);
        binding.tvBuildDate.setText(config.rom.build_date);
        binding.tvBuildVariant.setText(config.rom.build_variant);
        binding.tvSecurityPatch.setText(config.rom.security_patch);
    }

    private void openUrl(String url) {
        if (url == null || url.isEmpty()) return;
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
