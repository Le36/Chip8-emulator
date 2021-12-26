package com.chip8.configs;

import lombok.Data;

/**
 * some configurable settings for emulator and ui
 */
@Data
public class Configs {

    private boolean printToConsole;
    private boolean disableUiUpdates;
    private String printSymbol;
    private boolean spriteExtracting;
    private boolean roundPixels;
    private boolean blur;
    private boolean glow;
    private double blurValue;
    private double glowValue;
    private String bgColor;
    private String spriteColor;

    public Configs() {
        try {
            ConfigsSaver configsSaver = new ConfigsSaver();
            this.printToConsole = configsSaver.loadState("printToConsole:");
            this.disableUiUpdates = configsSaver.loadState("disableUiUpdates:");
            this.roundPixels = configsSaver.loadState("roundPixels:");
            this.printSymbol = configsSaver.loadSymbol();
            this.blur = configsSaver.loadState("blur:");
            this.glow = configsSaver.loadState("glow:");
            this.blurValue = configsSaver.loadValue("blurValue:");
            this.glowValue = configsSaver.loadValue("glowValue:");
        } catch (Exception ignored) {
            DefaultValues d = new DefaultValues();
            this.printToConsole = d.isPrintToConsole();
            this.disableUiUpdates = d.isDisableUiUpdates();
            this.printSymbol = d.getPrintSymbol();
            this.roundPixels = d.isRoundPixels();
            this.blur = d.isBlur();
            this.glow = d.isGlow();
            this.blurValue = d.getBlurValue();
            this.glowValue = d.getGlowValue();
        }
        this.spriteExtracting = false;
    }

}
