package instructions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MachineTest {
    public static void main(String[] args) {
        // Sample instructions as specified
        Instructions[] instructions = new Instructions[]{
        	new LoadConstant(0, 5),
            new LoadConstant(1, 3),
            new LoadConstant(2, 1),
            new JumpIfZero(1, 7),
            new Multiply(2, 0),
            new Decrement(1),
            new Jump(3),
            new Halt()
        };

        // Initialize registers with sample values
        int[] registers = new int[]{1, 1, 1};

        // Create instance of Machine
        Machine machine = new Machine();

        // Execute instructions
        machine.execute(registers, instructions);

        // Display final state of registers
        System.out.println("\nFinal state of registers:");
        for (int i = 0; i < registers.length; i++) {
            System.out.println("Register " + i + ": " + registers[i]);
        }
    }
}

