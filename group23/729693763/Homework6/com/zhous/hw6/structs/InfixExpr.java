package com.zhous.hw6.structs;

import java.util.List;

public class InfixExpr {
	String expr = null;
	
	public InfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {		

		//解析Token
		List<Token> tokens = ParserToken.parse(this.expr);

		Stack opStack = new Stack();	//Token
		Stack numStack = new Stack();	//Float

		for(Token token: tokens){
			if(token.isNumber()){
				numStack.push(new Float(token.getIntValue()) );
			} else if(token.isOperator()){

				while(!opStack.isEmpty() &&
						!token.HasHigherPriority( (Token)opStack.peek()) ){//堆栈下面的优先级高，需要提出来先计算他
					Token op = (Token)opStack.pop();
					Float f1 = (Float) numStack.pop();
					Float f2 = (Float) numStack.pop();
					Float result = calculate(op.toString(),f2,f1);
					numStack.push(result);
				}
				opStack.push(token);
			}
		}

		//剩下的都是优先级相同的，所有直接拉出来计算就可以
		while(!opStack.isEmpty()){
			Token token = (Token) opStack.pop();
			Float f2 = (Float) numStack.pop();
			Float f1 = (Float) numStack.pop();
			numStack.push(calculate(token.toString(), f1,f2));
		}
		return ((Float)numStack.pop()).floatValue();
	}

	private Float calculate(String op, Float f1, Float f2) {
		if (op.equals("+")) {
			return f1 + f2;
		}
		if (op.equals("-")) {
			return f1 - f2;
		}
		if (op.equals("*")) {
			return f1 * f2;
		}
		if (op.equals("/")) {
			return f1 / f2;
		}
		throw new RuntimeException(op + " is not supported");

	}
}
