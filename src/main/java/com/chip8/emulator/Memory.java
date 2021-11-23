package com.chip8.emulator;

import lombok.Data;

import java.util.ArrayDeque;

@Data
public class Memory {

    private byte[] v; // 16x 8-bit variable registers
    private short i; // 16-bit index register
    private short pc; // program counter
    private byte[] ram; // 4 kB memory, 0x0 - 0x1FF reserved for font data etc.
    private byte delayTimer; // 8-bit delay timer
    private byte soundTimer; // 8-bit sound timer
    private ArrayDeque<Short> stack; // stack for 16-bit addresses used by 00EE and 2NNN

    public Memory() {
        this.ram = new byte[4096];
        this.pc = 0x200; // starts at 0x200 since it's where the roms first byte is loaded in RAM
        this.v = new byte[16];
        this.loadFontToRAM();
        this.stack = new ArrayDeque<>();
    }

    public void timerDecrement() {
        this.delayTimer--;
        this.soundTimer--;
        if (delayTimer == -1) {
            delayTimer++;
        }
        if (soundTimer == -1) {
            delayTimer++;
        }
    }

    public void initializeMemory(short address, byte b) {
        this.ram[address] = b;
    }

    public void varReg(int index, int value) {
        this.v[index] = (byte) value;
    }

    private void loadFontToRAM() {
        int[] fontData = this.fontData();
        short address = 0x50;
        int pointer = 0;
        // 16 different characters, each character 5 bytes
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 5; j++, address++, pointer++) {
                this.initializeMemory(address, (byte) fontData[pointer]);
            }
        }
    }

    private int[] fontData() {
        return new int[]{
                0xF0, 0x90, 0x90, 0x90, 0xF0, // 0
                0x20, 0x60, 0x20, 0x20, 0x70, // 1
                0xF0, 0x10, 0xF0, 0x80, 0xF0, // 2
                0xF0, 0x10, 0xF0, 0x10, 0xF0, // 3
                0x90, 0x90, 0xF0, 0x10, 0x10, // 4
                0xF0, 0x80, 0xF0, 0x10, 0xF0, // 5
                0xF0, 0x80, 0xF0, 0x90, 0xF0, // 6
                0xF0, 0x10, 0x20, 0x40, 0x40, // 7
                0xF0, 0x90, 0xF0, 0x90, 0xF0, // 8
                0xF0, 0x90, 0xF0, 0x10, 0xF0, // 9
                0xF0, 0x90, 0xF0, 0x90, 0x90, // A
                0xE0, 0x90, 0xE0, 0x90, 0xE0, // B
                0xF0, 0x80, 0x80, 0x80, 0xF0, // C
                0xE0, 0x90, 0x90, 0x90, 0xE0, // D
                0xF0, 0x80, 0xF0, 0x80, 0xF0, // E
                0xF0, 0x80, 0xF0, 0x80, 0x80  // F
        };
    }
}
