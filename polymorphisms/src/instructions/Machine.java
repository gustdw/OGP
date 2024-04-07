package instructions;

abstract class Instructions {
	int[] instructions;
	Instructions() {
	}
	Instructions(int[] instructions) {
		this.instructions = instructions;
	}
}

class LoadConstant extends Instructions {
	int r;
	int c;
	LoadConstant(int r, int c) {
		this.r = r;
		this.c = c;
	}
	int getR() {
		return r;
	}
	int getC() {
		return c;
	}
}

class Decrement extends Instructions {
	int r;
	Decrement(int r) {
		this.r = r;
	}
	int getR() {
		return r;
	}
}

class Multiply extends Instructions {
	int r1;
	int r2;
	Multiply(int r1, int r2) {
		this.r1 = r1;
		this.r2 = r2;
	}
	int getR1() {
		return r1;
	}
	int getR2() {
		return r2;
	}
}

class JumpIfZero extends Instructions {
	int r;
	int a;
	JumpIfZero(int r, int a) {
		this.r = r;
		this.a = a;
	}
	int getR() {
		return r;
	}
	int getA() {
		return a;
	}
}

class Jump extends Instructions {
	int a;
	Jump(int a) {
		this.a = a;
	}
	int getA() {
		return a;
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
				registers[lc.getR()] = lc.getC();
			}
			else if (instructions[pc] instanceof Decrement dc) {
				registers[dc.getR()] -= 1;
			}
			else if (instructions[pc] instanceof Multiply mul) {
				registers[mul.getR1()] = registers[mul.getR1()] * registers[mul.getR2()];
			}
			else if (instructions[pc] instanceof JumpIfZero beqz) {
				if (registers[beqz.getR()] == 0)	
					pc = beqz.getA() - 1;
			}
			else if (instructions[pc] instanceof Jump j) {
				pc = j.getA() - 1;
			}
			else if (instructions[pc] instanceof Halt nop) {
				System.out.print("Program finished");
			}
			pc += 1;
		}
	}
}
