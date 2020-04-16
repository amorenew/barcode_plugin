package com.hsm.barcode;

public class SymbologyConfig {
    public int Flags;
    public int Mask;
    public int MaxLength;
    public int MinLength;
    public int symID;

    public SymbologyConfig(int symbologyID) {
        this.symID = symbologyID;
    }
}
