package org.allaymc.javaplugintemplate;

import org.allaymc.api.plugin.Plugin;

public class JavaPluginTemplate extends Plugin {
    @Override
    public void onLoad() {
        this.pluginLogger.info("JavaPluginTemplate is loaded!");
    }

    @Override
    public void onEnable() {
        this.pluginLogger.info("JavaPluginTemplate is enabled!");
    }

    @Override
    public void onDisable() {
        this.pluginLogger.info("JavaPluginTemplate is disabled!");
    }
}