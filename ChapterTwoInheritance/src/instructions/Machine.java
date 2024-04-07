package instructions;

abstract class Instructions {
	int[] registers;
	abstract void performAction(int[] registers, Machine machine);
}

class LoadConstant extends Instructions {
	int r;
	int c;
	LoadConstant(int r, int c) {
		this.r = r;
		this.c = c;
	}
	void performAction(int[] registers, Machine machine) {
		registers[r] = c;
		machine.pc++;
	}
}

class Decrement extends Instructions {
	int r;
	Decrement(int r) {
		this.r = r;
	}
	void performAction(int[] registers, Machine machine) {
		registers[r]--;
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
	void performAction(int[] registers, Machine machine) {
		registers[r1] = registers[r1] * registers[r2];
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
	void performAction(int[] registers, Machine machine) {
		if (registers[r] == 0)	
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
	void performAction(int[] registers, Machine machine) {
		machine.pc = a;
	}
}

class Halt extends Instructions {
	void performAction(int[] registers, Machine machine) {
		machine.pc = -1;
	}
}

public class Machine {
	int[] registers;
	Instructions[] instructions;
	int pc;
	Machine(int pc) {
		this.pc = pc;
	}
	void execute(int[] registers, Instructions[] instructions) {
		Machine machine = new Machine(0);
		while (machine.pc >= 0) {
			instructions[machine.pc].performAction(registers, machine);
		} 
	}
}
