package com.rominfo.app.data;

import java.util.List;

public class RomConfig {
    public RomInfo rom;
    public MaintainerInfo maintainer;
    public LinksInfo links;
    public List<ChangelogEntry> changelog;

    public static class RomInfo {
        public String name;
        public String version;
        public String android_version;
        public String build_type;
        public String build_date;
        public String build_variant;
        public String device_name;
        public String device_codename;
        public String architecture;
        public String security_patch;
    }

    public static class MaintainerInfo {
        public String name;
        public String telegram;
        public String github;
        public String xda_thread;
        public String donation_url;
    }

    public static class LinksInfo {
        public String telegram_group;
        public String telegram_channel;
        public String github_repo;
        public String sourceforge;
    }

    public static class ChangelogEntry {
        public String version;
        public String date;
        public List<String> changes;
    }
}
