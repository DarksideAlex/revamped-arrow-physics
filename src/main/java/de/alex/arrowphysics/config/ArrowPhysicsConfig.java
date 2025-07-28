package de.alex.arrowphysics.config;

import net.minecraft.nbt.NbtCompound;

public class ArrowPhysicsConfig {
    public double effectStrength;

    public void copy(ArrowPhysicsConfig other) {
        this.effectStrength = other.effectStrength;
    }

    public void fromNbt(NbtCompound tag) {
        effectStrength = tag.getInt("effectStrength");
    }

    public NbtCompound toNbt() {
        NbtCompound tag = new NbtCompound();
        tag.putDouble("effectStrength", effectStrength);
        return tag;
    }
}