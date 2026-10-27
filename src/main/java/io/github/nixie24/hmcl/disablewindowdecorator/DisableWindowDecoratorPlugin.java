package io.github.nixie24.hmcl.disablewindowdecorator;

import org.jackhuang.hmcl.plugin.Plugin;
import org.jackhuang.hmcl.plugin.PluginContext;
import org.jackhuang.hmcl.plugin.PluginManifest;

/// Lifecycle entry point for the window-decoration Mixin plugin.
///
/// The visible change is applied by the Mixin classes before the launcher UI loads; this class
/// only participates in the standard HMCL plugin lifecycle so the package can be installed and
/// enabled like any other plugin.
public final class DisableWindowDecoratorPlugin implements Plugin {
    private PluginContext context;

    @Override
    public void onLoad(PluginContext context) {
        this.context = context;
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public PluginManifest getManifest() {
        return context.getManifest();
    }
}
