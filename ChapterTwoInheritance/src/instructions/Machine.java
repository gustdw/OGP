package instructions;

abstract class Instructions {}

class LoadConstant extends Instructions {
	int r;
	int c;
	LoadConstant(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

class Decrement extends Instructions {
	int r;
	Decrement(int r) {
		this.r = r;
	}
}

class Multiply extends Instructions {
	int r1;
	int r2;
	Multiply(int r1, int r2) {
		this.r1 = r1;
		this.r2 = r2;
	}
}

class JumpIfZero extends Instructions {
	int r;
	int a;
	JumpIfZero(int r, int a) {
		this.r = r;
		this.a = a;
	}
}

class Jump extends Instructions {
	int a;
	Jump(int a) {
		this.a = a;
	}
}

class Halt extends Instructions {
	Halt() {
		System.out.print("Program finished");
	}
}

public class Machine {
	int[] registers;
	Instructions[] instructions;
	void execute(int[] registers, Instructions[] instructions) {
		int pc = 0;
		while (pc < instructions.length) {
			if (instructions[pc] instanceof LoadConstant lc) {
				registers[lc.r] = lc.r;
				pc++;
			}
			else if (instructions[pc] instanceof Decrement dc) {
				registers[dc.r]--;
				pc++;
			}
			else if (instructions[pc] instanceof Multiply mul) {
				registers[mul.r1] = registers[mul.r1] * registers[mul.r2];
				pc++;
			}
			else if (instructions[pc] instanceof JumpIfZero beqz) {
				if (registers[beqz.r] == 0)	
					pc = beqz.a;
			}
			else if (instructions[pc] instanceof Jump j) {
				pc = j.a;
			}
			else if (instructions[pc] instanceof Halt nop) {
				System.out.print("Program finished");
				pc++;
			}
			else
				throw new AssertionError();
		} 
	}
}
