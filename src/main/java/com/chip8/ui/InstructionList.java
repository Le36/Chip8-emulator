package com.chip8.ui;

import lombok.Data;

@Data
public class InstructionList {

    private String seekString;

    public void seek(short opcode) {

        switch (opcode) {
            case 0x0000:
                this.seekString = "0x0000: Empty memory";
                return;
            case 0x00E0: // 00E0
                this.seekString = "00E0: Clears the display";
                return;
            case 0x00EE: // 00EE
                this.seekString = "00EE: Returns from a subroutine";
                return;
        }
        switch (opcode & 0xF0FF) {
            case 0xE09E: // EX9E
                this.seekString = "EX9E: Skip if key contained in V[X] is pressed";
                return;
            case 0xE0A1: // EXA1
                this.seekString = "EXA1: Skip if key contained in V[X] not pressed";
                return;
            case 0xF007: // FX07
                this.seekString = "FX07: Set V[X] to Delay timer value";
                return;
            case 0xF00A: // FX0A
                this.seekString = "FX0A: Wait for key press and store key in V[X]";
                return;
            case 0xF015: // FX15
                this.seekString = "FX15: Set delay timer to V[X]";
                return;
            case 0xF018: // FX18
                this.seekString = "FX18: Set sound timer to V[X]";
                return;
            case 0xF01E: // FX1E
                this.seekString = "FX1E: Add V[X] to index register";
                return;
            case 0xF029: //FX29
                this.seekString = "FX29: Set index to location of a key in V[X]";
                return;
            case 0xF033: // FX33
                this.seekString = "FX33: BCD, store BCD in V[X] to index locations";
                return;
            case 0xF055: // FX55
                this.seekString = "FX55: Dump registers V[0] - V[X] to index locations";
                return;
            case 0xF065: // FX65
                this.seekString = "FX65: Fill registers V[0] - V[X] from index locations";
                return;
        }
        switch (opcode & 0xF000) {
            case 0x1000: // 1NNN
                this.seekString = "1NNN: Jump to location NNN";
                return;
            case 0x2000: // 2NNN
                this.seekString = "2NNN: Calls a subroutine at NNN";
                return;
            case 0x3000: // 3XNN
                this.seekString = "3XNN: Skip next instruction if V[X] == NN";
                return;
            case 0x4000: // 4XNN
                this.seekString = "4XNN: Skip next instruction if V[X] != NN";
                return;
            case 0x5000: // 5XY0
                this.seekString = "5XY0: Skip next instruction if V[X] == V[Y]";
                return;
            case 0x6000: // 6XNN
                this.seekString = "6XNN: Set V[X] to NN";
                return;
            case 0x7000: // 7XNN
                this.seekString = "7XNN: Add NN to V[X]";
                return;
            case 0x9000: // 9XY0
                this.seekString = "9XY0: Skip next instruction if V[X] != V[Y]";
                return;
            case 0xA000: // ANNN
                this.seekString = "ANNN: Set index to NNN";
                return;
            case 0xB000: // BNNN
                this.seekString = "BNNN: Jump with offset, NNN + V[0]";
                return;
            case 0xC000: // CXNN
                this.seekString = "CXNN: Random, sets V[X] to random byte & NN";
                return;
            case 0xD000: // DXYN
                this.seekString = "DXYN: Draw display";
                return;
        }
        switch (opcode & 0xF00F) {
            case 0x8000: // 8XY0
                this.seekString = "8XY0: Set V[X] to V[Y]";
                return;
            case 0x8001: // 8XY1
                this.seekString = "8XY1: Bitwise OR V[X] and V[Y], set to V[X]";
                return;
            case 0x8002: // 8XY2
                this.seekString = "8XY1: Bitwise AND V[X] and V[Y], set to V[X]";
                return;
            case 0x8003: // 8XY3
                this.seekString = "8XY1: Bitwise XOR V[X] and V[Y], set to V[X]";
                return;
            case 0x8004: // 8XY4
                this.seekString = "8XY4: Add V[Y] to V[X], if overflow VF = 1";
                return;
            case 0x8005: // 8XY5
                this.seekString = "8XY5: Subtract V[X] = V[X] - V[Y]";
                return;
            case 0x8007: // 8XY7
                this.seekString = "8XY7: Subtract V[X] = V[Y] - V[X]";
                return;
            case 0x8006: // 8XY6
                this.seekString = "8XY6: Shift Right and divide V[X] by 2";
                return;
            case 0x800E: // 8XYE
                this.seekString = "8XYE: Shift Left and multiply V[X] by 2";
                return;
        }
    }
}
