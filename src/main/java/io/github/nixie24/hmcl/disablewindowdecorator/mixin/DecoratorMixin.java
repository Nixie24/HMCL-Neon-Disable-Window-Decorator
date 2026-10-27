package io.github.nixie24.hmcl.disablewindowdecorator.mixin;

import org.jackhuang.hmcl.ui.decorator.Decorator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/// Forces the decorator into its edge-to-edge state for every window state.
///
/// `Decorator.updateWindowDecoration(boolean)` normally reserves `SHADOW_INSETS` around the
/// content and installs a `DropShadow` unless the stage is maximized or full-screen. Forcing the
/// flag to `true` removes that padding and effect, so the scene fills the transparent stage.
@Mixin(Decorator.class)
public abstract class DecoratorMixin {
    private DecoratorMixin() {
    }

    @ModifyVariable(
            method = "updateWindowDecoration",
            at = @At("HEAD"),
            argsOnly = true,
            index = 1
    )
    private boolean disableWindowDecorator$forceEdgeToEdge(boolean edgeToEdge) {
        return true;
    }
}
