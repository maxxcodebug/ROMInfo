package com.rominfo.app.ui.screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.card.MaterialCardView;
import com.rominfo.app.R;
import com.rominfo.app.data.RomConfig;
import com.rominfo.app.databinding.FragmentChangelogBinding;
import com.rominfo.app.utils.ConfigLoader;

public class ChangelogFragment extends Fragment {

    private FragmentChangelogBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentChangelogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RomConfig config = ConfigLoader.load(requireContext());

        if (config.changelog == null || config.changelog.isEmpty()) {
            binding.tvNoChangelog.setVisibility(View.VISIBLE);
            return;
        }

        for (RomConfig.ChangelogEntry entry : config.changelog) {
            // Create card for each version
            MaterialCardView card = new MaterialCardView(requireContext());
            LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            cardParams.setMargins(0, 0, 0, dpToPx(12));
            card.setLayoutParams(cardParams);
            card.setRadius(dpToPx(16));
            card.setCardElevation(0);
            card.setStrokeWidth(dpToPx(1));
            card.setStrokeColor(requireContext().getColor(com.google.android.material.R.color.material_on_background_stroke));

            LinearLayout inner = new LinearLayout(requireContext());
            inner.setOrientation(LinearLayout.VERTICAL);
            inner.setPadding(dpToPx(16), dpToPx(16), dpToPx(16), dpToPx(16));

            // Version header
            TextView tvVersion = new TextView(requireContext());
            tvVersion.setText("Version " + entry.version);
            tvVersion.setTextAppearance(com.google.android.material.R.style.TextAppearance_Material3_TitleMedium);
            tvVersion.setTextColor(requireContext().getColor(R.color.md_theme_primary));
            inner.addView(tvVersion);

            // Date
            TextView tvDate = new TextView(requireContext());
            tvDate.setText(entry.date);
            tvDate.setTextAppearance(com.google.android.material.R.style.TextAppearance_Material3_BodySmall);
            tvDate.setPadding(0, 0, 0, dpToPx(8));
            inner.addView(tvDate);

            // Changes
            if (entry.changes != null) {
                for (String change : entry.changes) {
                    TextView tvChange = new TextView(requireContext());
                    tvChange.setText("• " + change);
                    tvChange.setTextAppearance(com.google.android.material.R.style.TextAppearance_Material3_BodyMedium);
                    tvChange.setPadding(0, dpToPx(2), 0, dpToPx(2));
                    inner.addView(tvChange);
                }
            }

            card.addView(inner);
            binding.changelogContainer.addView(card);
        }
    }

    private int dpToPx(int dp) {
        return (int) (dp * requireContext().getResources().getDisplayMetrics().density);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
