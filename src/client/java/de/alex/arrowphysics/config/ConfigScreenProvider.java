package de.alex.arrowphysics.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import de.alex.arrowphysics.RevampedArrowPhysics;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.*;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.List;

public class ConfigScreenProvider implements ConfigScreenFactory {
    @Override
    public Screen create(Screen parent) {
        ArrowPhysicsConfig config = ConfigManager.CONFIG;
        ArrowPhysicsConfig temp = new ArrowPhysicsConfig();
        temp.copy(config);

        RevampedArrowPhysics.LOGGER.info("Temp effectStrength value before building screen: {}", temp.effectStrength);

        return YetAnotherConfigLib.createBuilder()
                .title(Text.literal("Revamped Arrow Physics Config"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Settings"))
                        .options(List.of(
                                Option.<Double>createBuilder()
                                        .name(Text.literal("Effect Strength"))
                                        .description(OptionDescription.of(Text.literal("0 = Vanilla, 1 = Default, 2 = no gravity")))
                                        .binding(
                                                1.0,
                                                () -> temp.effectStrength,
                                                val -> {
                                                    temp.effectStrength = val;
                                                }
                                        )
                                        .controller(opt -> DoubleSliderControllerBuilder.create(opt)
                                                .range(0.0, 2.0)
                                                .step(0.01)
                                        )
                                        .build()
                        ))
                        .build()
                )
                .save(() -> {
                    config.copy(temp);
                    ConfigManager.save();
                })
                .build()
                .generateScreen(parent);
    }
}