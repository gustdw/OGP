package instructions;

abstract class Instructions {
	int[] registers;
	abstract void performAction(Machine machine);
}

class LoadConstant extends Instructions {
	int r;
	int c;
	LoadConstant(int r, int c) {
		this.r = r;
		this.c = c;
	}
	void performAction(Machine machine) {
		machine.registers[r] = c;
		machine.pc++;
	}
}

class Decrement extends Instructions {
	int r;
	Decrement(int r) {
		this.r = r;
	}
	void performAction(Machine machine) {
		machine.registers[r]--;
		machine.pc++;
	}
}

class Multiply extends Instructions {
	int r1;
	int r2;
	Multiply(int r1, int r2) {
		this.r1 = r1;
		this.r2 = r2;
	}
	void performAction(Machine machine) {
		machine.registers[r1] = machine.registers[r1] * machine.registers[r2];
		machine.pc++;
	}
}

class JumpIfZero extends Instructions {
	int r;
	int a;
	JumpIfZero(int r, int a) {
		this.r = r;
		this.a = a;
	}
	void performAction(Machine machine) {
		if (machine.registers[r] == 0)	
			machine.pc = a;
		else
			machine.pc++;
	}
}

class Jump extends Instructions {
	int a;
	Jump(int a) {
		this.a = a;
	}
	void performAction(Machine machine) {
		machine.pc = a;
	}
}

class Halt extends Instructions {
	void performAction(Machine machine) {
		machine.pc = -1;
	}
}

public class Machine {
	
	int[] registers;
	int pc;

	void execute(int[] registers, Instructions[] instructions) {
		this.registers = registers;
		while (pc >= 0) {
			Instructions instruction = instructions[pc];
			instruction.performAction(this);
		} 
	}
}
