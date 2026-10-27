package io.github.nixie24.hmcl.disablewindowdecorator.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/// Keeps the main-window clip square regardless of the edge-to-edge flag.
///
/// `MainWindowPane` is package-private, so the mixin target is referenced by name instead of a
/// class literal. `DecoratorMixin` already forces the flag, but this guard also covers any future
/// caller that reaches `setWindowEdgeToEdge(false)` directly.
@Mixin(targets = "org.jackhuang.hmcl.ui.decorator.MainWindowPane")
public abstract class MainWindowPaneMixin {
    private MainWindowPaneMixin() {
    }

    @ModifyVariable(
            method = "setWindowEdgeToEdge",
            at = @At("HEAD"),
            argsOnly = true,
            index = 1
    )
    private boolean disableWindowDecorator$forceSquareCorners(boolean edgeToEdge) {
        return true;
    }
}
