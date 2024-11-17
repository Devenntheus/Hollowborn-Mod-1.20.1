package net.bird.hollowborn.util;

import net.minecraft.entity.player.PlayerEntity;

// Define the SpellAttributes class
public class SpellAttributes {
    public float damageModifier;
    public PlayerEntity player;
    public String magicSchool;

    public SpellAttributes(float damageModifier, PlayerEntity player, String magicSchool) {
        this.damageModifier = damageModifier;
        this.player = player;
        this.magicSchool = magicSchool;
    }

    @Override
    public String toString() {
        return "SpellAttributes{damageModifier=" + damageModifier + ", player=" + player + ", magicSchool=" + magicSchool + "}";
    }
}